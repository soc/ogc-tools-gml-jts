package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.ArrayList;
import java.util.List;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import net.opengis.gml.v_3_1_1.MultiPolygonPropertyType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

public class GML311ToJTSMultiPolygonConverter
    extends AbstractGML311ToJTSConverter<MultiPolygonType, MultiPolygonPropertyType, MultiPolygon> {

  // + MultiPolygon

  private final GML311ToJTSConverterInterface<PolygonType, PolygonPropertyType, Polygon> polygonConverter;

  public GML311ToJTSMultiPolygonConverter(
      GeometryFactory geometryFactory,
      GML311ToJTSSRIDConverterInterface sridConverter,
      GML311ToJTSConverterInterface<PolygonType, PolygonPropertyType, Polygon> polygonConverter) {
    super(geometryFactory, sridConverter);
    this.polygonConverter = polygonConverter;
  }

  @Override
  protected MultiPolygon doCreateGeometry(ObjectLocator locator,
                                          MultiPolygonType multiPolygonType) throws ConversionFailedException {
    final List<PolygonPropertyType> polygonMembers = multiPolygonType.getPolygonMember();
    final List<Polygon> polygons = new ArrayList<Polygon>(polygonMembers.size());
    for (int index = 0; index < polygonMembers.size(); index++) {
      final PolygonPropertyType polygonPropertyType = polygonMembers.get(index);
      final PolygonType polygonType = polygonPropertyType.getPolygon();
      polygons.add(polygonConverter.createGeometry(
          locator.property("polygonMember", polygonMembers).item(index, polygonPropertyType).property("polygon", polygonType), polygonType)); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return getGeometryFactory().createMultiPolygon(polygons.toArray(new Polygon[0]));
  }

  public MultiPolygon createGeometry(ObjectLocator locator, MultiPolygonPropertyType multiPolygonPropertyType)
      throws ConversionFailedException {
    if (multiPolygonPropertyType.isSetMultiPolygon()) {
      return createGeometry(locator.property(
          "multiPolygon", multiPolygonPropertyType.getMultiPolygon()), multiPolygonPropertyType.getMultiPolygon());
    } else {
      throw new ConversionFailedException(locator, "Expected [MultiPolygon] element."); //$NON-NLS-1$
    }
  }
}
