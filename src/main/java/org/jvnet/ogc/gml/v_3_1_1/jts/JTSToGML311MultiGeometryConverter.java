package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import org.jvnet.ogc.gml.v_3_1_1.ObjectFactoryInterface;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.GeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiGeometryType;

public class JTSToGML311MultiGeometryConverter
    extends AbstractJTSToGML311Converter<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> {
  private final JTSToGML311ConverterInterface<AbstractGeometryType, GeometryPropertyType, Geometry> geometryConverter;

  public JTSToGML311MultiGeometryConverter(
      ObjectFactoryInterface objectFactory,
      JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter,
      JTSToGML311ConverterInterface<AbstractGeometryType, GeometryPropertyType, Geometry> geometryConverter) {
    super(objectFactory, srsReferenceGroupConverter);
    this.geometryConverter = geometryConverter;
  }

  @Override
  protected MultiGeometryType doCreateGeometryType(
      GeometryCollection multiGeometry) {
    final MultiGeometryType multiGeometryType = getObjectFactory().createMultiGeometryType();

    for (int index = 0; index < multiGeometry.getNumGeometries(); index++) {
      final Geometry geometry = multiGeometry.getGeometryN(index);

      multiGeometryType.getGeometryMember().add(geometryConverter.createPropertyType(geometry));

    }
    return multiGeometryType;
  }

  public MultiGeometryPropertyType createPropertyType(
      GeometryCollection multiGeometry) {
    final MultiGeometryPropertyType multiGeometryPropertyType = getObjectFactory().createMultiGeometryPropertyType();
    multiGeometryPropertyType.setGeometricAggregate(createElement(multiGeometry));
    return multiGeometryPropertyType;
  }

  public JAXBElement<MultiGeometryType> createElement(
      GeometryCollection geometryCollection) {
    return getObjectFactory().createMultiGeometry(createGeometryType(geometryCollection));
  }
}
