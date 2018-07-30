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

	public CoordType createCoordType();

	public CoordinatesType createCoordinatesType();

	public DirectPositionType createDirectPositionType();

	public DirectPositionListType createDirectPositionListType();

	public JAXBElement<DirectPositionType> createPos(DirectPositionType value);

	public PointType createPointType();

	public JAXBElement<PointType> createPoint(PointType value);

	public PointPropertyType createPointPropertyType();

	public JAXBElement<PointPropertyType> createPointProperty(
			PointPropertyType value);

	public LineStringType createLineStringType();

	public JAXBElement<LineStringType> createLineString(LineStringType value);

	public LineStringPropertyType createLineStringPropertyType();

	public JAXBElement<LineStringPropertyType> createLineStringProperty(
			LineStringPropertyType value);

	public LinearRingType createLinearRingType();

	public JAXBElement<LinearRingType> createLinearRing(LinearRingType value);

	public LinearRingPropertyType createLinearRingPropertyType();
	
	public JAXBElement<LinearRingPropertyType> createLinearRingProperty(
			LinearRingPropertyType value);
	
	public PolygonType createPolygonType();

	public PolygonPropertyType createPolygonPropertyType();

	public JAXBElement<PolygonType> createPolygon(PolygonType value);

	public JAXBElement<PolygonPropertyType> createPolygonProperty(
			PolygonPropertyType value);

	public MultiPointType createMultiPointType();

	public JAXBElement<MultiPointType> createMultiPoint(MultiPointType value);

	public MultiPointPropertyType createMultiPointPropertyType();

	public JAXBElement<MultiPointPropertyType> createMultiPointProperty(
			MultiPointPropertyType value);

	public MultiLineStringType createMultiLineStringType();

	public JAXBElement<MultiLineStringType> createMultiLineString(
			MultiLineStringType value);

	public MultiLineStringPropertyType createMultiLineStringPropertyType();

	public JAXBElement<MultiLineStringPropertyType> createMultiLineStringProperty(
			MultiLineStringPropertyType value);

	public AbstractRingPropertyType createAbstractRingPropertyType();

	public JAXBElement<AbstractRingPropertyType> createExterior(
			AbstractRingPropertyType value);

	public JAXBElement<AbstractRingPropertyType> createInterior(
			AbstractRingPropertyType value);

	public MultiPolygonType createMultiPolygonType();

	public MultiPolygonPropertyType createMultiPolygonPropertyType();

	public JAXBElement<MultiPolygonType> createMultiPolygon(
			MultiPolygonType value);

	public JAXBElement<MultiPolygonPropertyType> createMultiPolygonProperty(
			MultiPolygonPropertyType value);

	public MultiGeometryType createMultiGeometryType();

	public JAXBElement<MultiGeometryType> createMultiGeometry(
			MultiGeometryType value);

	public MultiGeometryPropertyType createMultiGeometryPropertyType();

	public JAXBElement<MultiGeometryPropertyType> createMultiGeometryProperty(
			MultiGeometryPropertyType value);

	public GeometryPropertyType createGeometryPropertyType();

	public JAXBElement<AbstractGeometryType> createGeometry(
			AbstractGeometryType value);
}
