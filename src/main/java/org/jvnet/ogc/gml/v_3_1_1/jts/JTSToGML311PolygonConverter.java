package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.lang.reflect.Method;

import javax.xml.bind.JAXBElement;

import org.jvnet.ogc.gml.v_3_1_1.ObjectFactoryInterface;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

public class JTSToGML311PolygonConverter extends AbstractJTSToGML311Converter<PolygonType, PolygonPropertyType, Polygon> {
  private final JTSToGML311ConverterInterface<LinearRingType, AbstractRingPropertyType, LinearRing> linearRingConverter;

  public JTSToGML311PolygonConverter(
      ObjectFactoryInterface objectFactory,
      JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter,
      JTSToGML311ConverterInterface<LinearRingType, AbstractRingPropertyType, LinearRing> linearRingConverter) {
    super(objectFactory, srsReferenceGroupConverter);
    this.linearRingConverter = linearRingConverter;
  }

  @Override
  protected PolygonType doCreateGeometryType(Polygon polygon) {
    final PolygonType resultPolygon = getObjectFactory().createPolygonType();
    {
      final LinearRing exteriorRing = callPolygonGetExteriorRing(polygon);
      final AbstractRingPropertyType abstractRingProperty = linearRingConverter.createPropertyType(exteriorRing);
      resultPolygon.setExterior(getObjectFactory().createExterior(abstractRingProperty));
    }
    for (int index = 0; index < polygon.getNumInteriorRing(); index++) {
      final LinearRing interiorRing = callPolygonGetInteriorRingN(polygon, index);
      resultPolygon.getInterior().add(getObjectFactory().createInterior(linearRingConverter.createPropertyType(interiorRing)));
    }
    return resultPolygon;
  }

  public PolygonPropertyType createPropertyType(Polygon polygon) {
    final PolygonPropertyType polygonPropertyType = getObjectFactory().createPolygonPropertyType();
    polygonPropertyType.setPolygon(createGeometryType(polygon));
    return polygonPropertyType;
  }

  public JAXBElement<PolygonType> createElement(Polygon polygon) {
    return getObjectFactory().createPolygon(createGeometryType(polygon));
  }

  // isolate us from binary incompatible change in JTS 1.17.0,
  // see https://github.com/locationtech/jts/releases/tag/1.17.0
  private static LinearRing callPolygonGetExteriorRing(Polygon polygon) {
    try {
      return (LinearRing) polygonGetExteriorMethod.invoke(polygon);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  private static LinearRing callPolygonGetInteriorRingN(Polygon polygon, int index) {
    try {
      return (LinearRing) polygonGetInteriorNMethod.invoke(polygon, index);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  private static final Method polygonGetExteriorMethod;
  private static final Method polygonGetInteriorNMethod;

  static {
    try {
      polygonGetExteriorMethod = Polygon.class.getMethod("getExteriorRing");
      polygonGetInteriorNMethod = Polygon.class.getMethod("getInteriorRingN", int.class);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e); // should not happen
    }
  }

}
