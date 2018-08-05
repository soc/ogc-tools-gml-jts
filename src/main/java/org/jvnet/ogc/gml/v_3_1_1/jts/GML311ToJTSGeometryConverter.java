package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.text.MessageFormat;

import net.opengis.gml.v_3_1_1.AbstractGeometricAggregateType;
import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.GeometryPropertyType;
import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.LinearRingPropertyType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiGeometryType;
import net.opengis.gml.v_3_1_1.MultiLineStringPropertyType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;
import net.opengis.gml.v_3_1_1.MultiPointPropertyType;
import net.opengis.gml.v_3_1_1.MultiPointType;
import net.opengis.gml.v_3_1_1.MultiPolygonPropertyType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class GML311ToJTSGeometryConverter implements
    GML311ToJTSConverterInterface<AbstractGeometryType, Object, Geometry> {

  // + Geometry
  // + Point
  // + Polygon
  // + LineString
  // + LinearRing
  // + GeometryCollection
  // + MultiPoint
  // + MultiLineString
  // + MultiPolygon

  private final GML311ToJTSCoordinateConverter coordinateConverter;
  private final GML311ToJTSConverterInterface<PointType, PointPropertyType, Point> pointConverter;
  private final GML311ToJTSConverterInterface<LineStringType, LineStringPropertyType, LineString> lineStringConverter;
  private final GML311ToJTSConverterInterface<LinearRingType, LinearRingPropertyType, LinearRing> linearRingConverter;
  private final GML311ToJTSConverterInterface<PolygonType, PolygonPropertyType, Polygon> polygonConverter;
  private final GML311ToJTSConverterInterface<MultiPointType, MultiPointPropertyType, MultiPoint> multiPointConverter;
  private final GML311ToJTSConverterInterface<MultiLineStringType, MultiLineStringPropertyType, MultiLineString> multiLineStringConverter;
  private final GML311ToJTSConverterInterface<MultiPolygonType, MultiPolygonPropertyType, MultiPolygon> multiPolygonConverter;
  private final GML311ToJTSConverterInterface<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> multiGeometryConverter;
  private final GML311ToJTSConverterInterface<AbstractGeometricAggregateType, MultiGeometryPropertyType, GeometryCollection> geometryCollectionConverter;

  public GML311ToJTSGeometryConverter(GeometryFactory geometryFactory, GML311ToJTSSRIDConverterInterface sridConverter) {
    this.coordinateConverter =
        new GML311ToJTSCoordinateConverter();
    this.pointConverter =
        new GML311ToJTSPointConverter(geometryFactory, sridConverter, this.coordinateConverter);
    this.lineStringConverter =
        new GML311ToJTSLineStringConverter(geometryFactory, sridConverter, this.coordinateConverter, this.pointConverter);
    this.linearRingConverter =
        new GML311ToJTSLinearRingConverter(geometryFactory, sridConverter, this.coordinateConverter, this.pointConverter);
    this.polygonConverter =
        new GML311ToJTSPolygonConverter(geometryFactory, sridConverter, this.linearRingConverter);
    this.multiPointConverter =
        new GML311ToJTSMultiPointConverter(geometryFactory, sridConverter, this.pointConverter);
    this.multiLineStringConverter =
        new GML311ToJTSMultiLineStringConverter(geometryFactory, sridConverter, this.lineStringConverter);
    this.multiPolygonConverter =
        new GML311ToJTSMultiPolygonConverter(geometryFactory, sridConverter, this.polygonConverter);
    this.multiGeometryConverter =
        new GML311ToJTSMultiGeometryConverter(geometryFactory, sridConverter, this);
    this.geometryCollectionConverter =
        new GML311ToJTSGeometryCollectionConverter(this.multiPointConverter, this.multiLineStringConverter, this.multiPolygonConverter, this.multiGeometryConverter);
  }

  public GML311ToJTSGeometryConverter() {
    this(GML311ToJTSConstants.DEFAULT_GEOMETRY_FACTORY, GML311ToJTSConstants.DEFAULT_SRID_CONVERTER);
  }

  public Geometry createGeometry(ObjectLocator locator, AbstractGeometryType abstractGeometryType)
      throws ConversionFailedException {
    if (abstractGeometryType instanceof PointType) {
      return pointConverter.createGeometry(locator, (PointType) abstractGeometryType);
    } else if (abstractGeometryType instanceof PolygonType) {
      return polygonConverter.createGeometry(locator, (PolygonType) abstractGeometryType);
    } else if (abstractGeometryType instanceof LineStringType) {
      return lineStringConverter.createGeometry(locator, (LineStringType) abstractGeometryType);
    } else if (abstractGeometryType instanceof LinearRingType) {
      return linearRingConverter.createGeometry(locator, (LinearRingType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiPointType) {
      return multiPointConverter.createGeometry(locator, (MultiPointType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiLineStringType) {
      return multiLineStringConverter.createGeometry(locator, (MultiLineStringType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiPolygonType) {
      return multiPolygonConverter.createGeometry(locator, (MultiPolygonType) abstractGeometryType);
    } else if (abstractGeometryType instanceof AbstractGeometricAggregateType) {
      return geometryCollectionConverter.createGeometry(locator, (AbstractGeometricAggregateType) abstractGeometryType);
    } else {
      throw new ConversionFailedException(locator, "Unexpected type."); //$NON-NLS-1$
    }
  }

  public Geometry createGeometry(ObjectLocator locator, Object abstractGeometryType) throws ConversionFailedException {
    if (abstractGeometryType == null) {
      return null;
    } else if (abstractGeometryType instanceof PointType) {
      return pointConverter.createGeometry(locator, (PointType) abstractGeometryType);
    } else if (abstractGeometryType instanceof PointPropertyType) {
      return pointConverter.createGeometry(locator, (PointPropertyType) abstractGeometryType);
    } else if (abstractGeometryType instanceof PolygonType) {
      return polygonConverter.createGeometry(locator, (PolygonType) abstractGeometryType);
    } else if (abstractGeometryType instanceof PolygonPropertyType) {
      return polygonConverter.createGeometry(locator, (PolygonPropertyType) abstractGeometryType);
    } else if (abstractGeometryType instanceof LineStringType) {
      return lineStringConverter.createGeometry(locator, (LineStringType) abstractGeometryType);
    } else if (abstractGeometryType instanceof LineStringPropertyType) {
      return lineStringConverter.createGeometry(locator, (LineStringPropertyType) abstractGeometryType);
    } else if (abstractGeometryType instanceof LinearRingType) {
      return linearRingConverter.createGeometry(locator, (LinearRingType) abstractGeometryType);
    } else if (abstractGeometryType instanceof LinearRingPropertyType) {
      return linearRingConverter.createGeometry(locator, (LinearRingPropertyType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiPointType) {
      return multiPointConverter.createGeometry(locator, (MultiPointType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiPointPropertyType) {
      return multiPointConverter.createGeometry(locator, (MultiPointPropertyType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiLineStringType) {
      return multiLineStringConverter.createGeometry(locator, (MultiLineStringType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiLineStringPropertyType) {
      return multiLineStringConverter.createGeometry(locator, (MultiLineStringPropertyType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiPolygonType) {
      return multiPolygonConverter.createGeometry(locator, (MultiPolygonType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiPolygonPropertyType) {
      return multiPolygonConverter.createGeometry(locator, (MultiPolygonPropertyType) abstractGeometryType);
    } else if (abstractGeometryType instanceof AbstractGeometricAggregateType) {
      return geometryCollectionConverter.createGeometry(locator, (AbstractGeometricAggregateType) abstractGeometryType);
    } else if (abstractGeometryType instanceof MultiGeometryPropertyType) {
      return geometryCollectionConverter.createGeometry(locator, (MultiGeometryPropertyType) abstractGeometryType);
    } else if (abstractGeometryType instanceof GeometryPropertyType) {
      final GeometryPropertyType geometryPropertyType = (GeometryPropertyType) abstractGeometryType;
      if (geometryPropertyType.isSetGeometry()) {
        return createGeometry(
            locator
                .property("geometry", geometryPropertyType.getGeometry())
                .property("value", geometryPropertyType.getGeometry().getValue()), geometryPropertyType.getGeometry().getValue()); //$NON-NLS-1$
      } else {
        throw new ConversionFailedException(locator, "Expected [Polygon] element."); //$NON-NLS-1$
      }

    } else {
      throw new ConversionFailedException(
          locator, MessageFormat.format("Unexpected type [{0}].", abstractGeometryType.getClass().getName())); //$NON-NLS-1$
    }
  }
}
