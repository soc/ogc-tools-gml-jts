package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import org.jvnet.ogc.gml.v_3_1_1.ObjectFactoryInterface;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.GeometryPropertyType;
import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
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

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class JTSToGML311GeometryConverter
		extends
		AbstractJTSToGML311Converter<AbstractGeometryType, GeometryPropertyType, Geometry> {

	private final JTSToGML311CoordinateConverter coordinateConverter;
	private final JTSToGML311ConverterInterface<PointType, PointPropertyType, Point> pointConverter;
	private final JTSToGML311ConverterInterface<LinearRingType, AbstractRingPropertyType, LinearRing> linearRingConverter;
	private final JTSToGML311ConverterInterface<LineStringType, LineStringPropertyType, LineString> lineStringConverter;
	private final JTSToGML311ConverterInterface<PolygonType, PolygonPropertyType, Polygon> polygonConverter;
	private final JTSToGML311ConverterInterface<MultiPointType, MultiPointPropertyType, MultiPoint> multiPointConverter;
	private final JTSToGML311ConverterInterface<MultiLineStringType, MultiLineStringPropertyType, MultiLineString> multiLineStringConverter;
	private final JTSToGML311ConverterInterface<MultiPolygonType, MultiPolygonPropertyType, MultiPolygon> multiPolygonConverter;
	private final JTSToGML311ConverterInterface<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> multiGeometryConverter;

	public JTSToGML311GeometryConverter() {
		this(JTSToGML311Constants.DEFAULT_OBJECT_FACTORY,
				JTSToGML311Constants.DEFAULT_SRS_REFERENCE_GROUP_CONVERTER);
	}

	public JTSToGML311GeometryConverter(
			ObjectFactoryInterface objectFactory,
			JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter) {
		super(objectFactory, srsReferenceGroupConverter);
		this.coordinateConverter = new JTSToGML311CoordinateConverter(
				objectFactory, srsReferenceGroupConverter);
		this.pointConverter = new JTSToGML311PointConverter(objectFactory,
				srsReferenceGroupConverter, this.coordinateConverter);
		this.linearRingConverter = new JTSToGML311LinearRingConverter(
				objectFactory, srsReferenceGroupConverter,
				this.coordinateConverter);
		this.lineStringConverter = new JTSToGML311LineStringConverter(
				objectFactory, srsReferenceGroupConverter,
				this.coordinateConverter);
		this.polygonConverter = new JTSToGML311PolygonConverter(objectFactory,
				srsReferenceGroupConverter, this.linearRingConverter);
		this.multiPointConverter = new JTSToGML311MultiPointConverter(
				objectFactory, srsReferenceGroupConverter, this.pointConverter);
		this.multiLineStringConverter = new JTSToGML311MultiLineStringConverter(
				objectFactory, srsReferenceGroupConverter,
				this.lineStringConverter);
		this.multiPolygonConverter = new JTSToGML311MultiPolygonConverter(
				objectFactory, srsReferenceGroupConverter,
				this.polygonConverter);
		this.multiGeometryConverter = new JTSToGML311MultiGeometryConverter(
				objectFactory, srsReferenceGroupConverter, this);
	}

	@Override
	protected AbstractGeometryType doCreateGeometryType(Geometry geometry) {
		if (geometry instanceof Point) {
			return pointConverter.createGeometryType((Point) geometry);
		} else if (geometry instanceof LineString) {
			return lineStringConverter
					.createGeometryType((LineString) geometry);
		} else if (geometry instanceof LinearRing) {
			return linearRingConverter
					.createGeometryType((LinearRing) geometry);
		} else if (geometry instanceof Polygon) {
			return polygonConverter.createGeometryType((Polygon) geometry);
		} else if (geometry instanceof MultiPoint) {
			return multiPointConverter
					.createGeometryType((MultiPoint) geometry);
		} else if (geometry instanceof MultiLineString) {
			return multiLineStringConverter
					.createGeometryType((MultiLineString) geometry);
		} else if (geometry instanceof MultiPolygon) {
			return multiPolygonConverter
					.createGeometryType((MultiPolygon) geometry);
		} else if (geometry instanceof GeometryCollection) {
			return multiGeometryConverter
					.createGeometryType((GeometryCollection) geometry);
		} else {
			// TODO
			throw new IllegalArgumentException();
		}

	}

	public GeometryPropertyType createPropertyType(Geometry geometry) {
		final GeometryPropertyType geometryPropertyType = getObjectFactory()
				.createGeometryPropertyType();
		geometryPropertyType.setGeometry(createElement(geometry));
		return geometryPropertyType;
	}

	public JAXBElement<? extends AbstractGeometryType> createElement(
			Geometry geometry) {
		if (geometry instanceof Point) {
			return pointConverter.createElement((Point) geometry);
		} else if (geometry instanceof LineString) {
			return lineStringConverter.createElement((LineString) geometry);
		} else if (geometry instanceof LinearRing) {
			return linearRingConverter.createElement((LinearRing) geometry);
		} else if (geometry instanceof Polygon) {
			return polygonConverter.createElement((Polygon) geometry);
		} else if (geometry instanceof MultiPoint) {
			return multiPointConverter.createElement((MultiPoint) geometry);
		} else if (geometry instanceof MultiLineString) {
			return multiLineStringConverter
					.createElement((MultiLineString) geometry);
		} else if (geometry instanceof MultiPolygon) {
			return multiPolygonConverter.createElement((MultiPolygon) geometry);
		} else if (geometry instanceof GeometryCollection) {
			return multiGeometryConverter
					.createElement((GeometryCollection) geometry);
		} else {
			// TODo
			throw new IllegalArgumentException();
		}

	}

}
