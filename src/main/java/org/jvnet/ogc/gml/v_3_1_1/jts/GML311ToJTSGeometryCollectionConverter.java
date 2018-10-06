package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.MultiLineString;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.MultiPolygon;

import net.opengis.gml.v_3_1_1.AbstractGeometricAggregateType;
import net.opengis.gml.v_3_1_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiGeometryType;
import net.opengis.gml.v_3_1_1.MultiLineStringPropertyType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;
import net.opengis.gml.v_3_1_1.MultiPointPropertyType;
import net.opengis.gml.v_3_1_1.MultiPointType;
import net.opengis.gml.v_3_1_1.MultiPolygonPropertyType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;

public class GML311ToJTSGeometryCollectionConverter
    implements GML311ToJTSConverterInterface<AbstractGeometricAggregateType, MultiGeometryPropertyType, GeometryCollection> {

  // + GeometryCollection
  // + MultiPoint
  // + MultiLineString
  // + MultiPolygon

  private final GML311ToJTSConverterInterface<MultiPointType, MultiPointPropertyType, MultiPoint> multiPointConverter;
  private final GML311ToJTSConverterInterface<MultiLineStringType, MultiLineStringPropertyType, MultiLineString> multiLineStringConverter;
  private final GML311ToJTSConverterInterface<MultiPolygonType, MultiPolygonPropertyType, MultiPolygon> multiPolygonConverter;
  private final GML311ToJTSConverterInterface<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> multiGeometryConverter;

  public GML311ToJTSGeometryCollectionConverter(
      GML311ToJTSConverterInterface<MultiPointType, MultiPointPropertyType, MultiPoint> multiPointConverter,
      GML311ToJTSConverterInterface<MultiLineStringType, MultiLineStringPropertyType, MultiLineString> multiLineStringConverter,
      GML311ToJTSConverterInterface<MultiPolygonType, MultiPolygonPropertyType, MultiPolygon> multiPolygonConverter,
      GML311ToJTSConverterInterface<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> multiGeometryConverter) {
    this.multiPointConverter = multiPointConverter;
    this.multiLineStringConverter = multiLineStringConverter;
    this.multiPolygonConverter = multiPolygonConverter;
    this.multiGeometryConverter = multiGeometryConverter;
  }

  public GeometryCollection createGeometry(ObjectLocator locator, AbstractGeometricAggregateType abstractGeometryType)
      throws ConversionFailedException {
    if (abstractGeometryType instanceof MultiPointType) {
      return multiPointConverter.createGeometry(locator, (MultiPointType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiLineStringType) {
      return multiLineStringConverter.createGeometry(locator, (MultiLineStringType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiPolygonType) {
      return multiPolygonConverter.createGeometry(locator, (MultiPolygonType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiGeometryType) {
      return multiGeometryConverter.createGeometry(locator, (MultiGeometryType) abstractGeometryType);
    } else {
      throw new ConversionFailedException(locator, "Unexpected type."); //$NON-NLS-1$
    }

  }

  public GeometryCollection createGeometry(ObjectLocator locator,
                                           MultiGeometryPropertyType multiGeometryPropertyType)
      throws ConversionFailedException {
    if (multiGeometryPropertyType.isSetGeometricAggregate()) {
      return createGeometry(
          locator
              .property("geometricAggregate", multiGeometryPropertyType.getGeometricAggregate())
              .property("value", multiGeometryPropertyType.getGeometricAggregate().getValue()), //$NON-NLS-1$ //$NON-NLS-2$
          multiGeometryPropertyType.getGeometricAggregate().getValue());
    } else {
      throw new ConversionFailedException(locator, "Expected [GeometricAggregate] element."); //$NON-NLS-1$
    }
  }
}
