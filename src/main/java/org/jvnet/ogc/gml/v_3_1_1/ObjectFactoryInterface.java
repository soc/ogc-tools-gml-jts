package org.jvnet.ogc.gml.v_3_1_1;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.CoordType;
import net.opengis.gml.v_3_1_1.CoordinatesType;
import net.opengis.gml.v_3_1_1.DirectPositionListType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
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

public interface ObjectFactoryInterface {

  CoordType createCoordType();

  CoordinatesType createCoordinatesType();

  DirectPositionType createDirectPositionType();

  DirectPositionListType createDirectPositionListType();

  JAXBElement<DirectPositionType> createPos(DirectPositionType value);

  PointType createPointType();

  JAXBElement<PointType> createPoint(PointType value);

  PointPropertyType createPointPropertyType();

  JAXBElement<PointPropertyType> createPointProperty(PointPropertyType value);

  LineStringType createLineStringType();

  JAXBElement<LineStringType> createLineString(LineStringType value);

  LineStringPropertyType createLineStringPropertyType();

  JAXBElement<LineStringPropertyType> createLineStringProperty(LineStringPropertyType value);

  LinearRingType createLinearRingType();

  JAXBElement<LinearRingType> createLinearRing(LinearRingType value);

  LinearRingPropertyType createLinearRingPropertyType();

  JAXBElement<LinearRingPropertyType> createLinearRingProperty(LinearRingPropertyType value);

  PolygonType createPolygonType();

  PolygonPropertyType createPolygonPropertyType();

  JAXBElement<PolygonType> createPolygon(PolygonType value);

  JAXBElement<PolygonPropertyType> createPolygonProperty(PolygonPropertyType value);

  MultiPointType createMultiPointType();

  JAXBElement<MultiPointType> createMultiPoint(MultiPointType value);

  MultiPointPropertyType createMultiPointPropertyType();

  JAXBElement<MultiPointPropertyType> createMultiPointProperty(MultiPointPropertyType value);

  MultiLineStringType createMultiLineStringType();

  JAXBElement<MultiLineStringType> createMultiLineString(
      MultiLineStringType value);

  MultiLineStringPropertyType createMultiLineStringPropertyType();

  JAXBElement<MultiLineStringPropertyType> createMultiLineStringProperty(MultiLineStringPropertyType value);

  AbstractRingPropertyType createAbstractRingPropertyType();

  JAXBElement<AbstractRingPropertyType> createExterior(AbstractRingPropertyType value);

  JAXBElement<AbstractRingPropertyType> createInterior(AbstractRingPropertyType value);

  MultiPolygonType createMultiPolygonType();

  MultiPolygonPropertyType createMultiPolygonPropertyType();

  JAXBElement<MultiPolygonType> createMultiPolygon(MultiPolygonType value);

  JAXBElement<MultiPolygonPropertyType> createMultiPolygonProperty(MultiPolygonPropertyType value);

  MultiGeometryType createMultiGeometryType();

  JAXBElement<MultiGeometryType> createMultiGeometry(MultiGeometryType value);

  MultiGeometryPropertyType createMultiGeometryPropertyType();

  JAXBElement<MultiGeometryPropertyType> createMultiGeometryProperty(MultiGeometryPropertyType value);

  GeometryPropertyType createGeometryPropertyType();

  JAXBElement<AbstractGeometryType> createGeometry(AbstractGeometryType value);
}
