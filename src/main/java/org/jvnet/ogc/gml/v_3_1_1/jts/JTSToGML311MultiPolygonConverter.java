package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import org.jvnet.ogc.gml.v_3_1_1.ObjectFactoryInterface;

import net.opengis.gml.v_3_1_1.MultiPolygonPropertyType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

public class JTSToGML311MultiPolygonConverter
		extends
		AbstractJTSToGML311Converter<MultiPolygonType, MultiPolygonPropertyType, MultiPolygon> {
	private final JTSToGML311ConverterInterface<PolygonType, PolygonPropertyType, Polygon> polygonConverter;

	public JTSToGML311MultiPolygonConverter(
			ObjectFactoryInterface objectFactory,
			JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter,
			JTSToGML311ConverterInterface<PolygonType, PolygonPropertyType, Polygon> polygonConverter) {
		super(objectFactory, srsReferenceGroupConverter);
		this.polygonConverter = polygonConverter;
	}

	@Override
	protected MultiPolygonType doCreateGeometryType(MultiPolygon multiPolygon) {
		final MultiPolygonType multiPolygonType = getObjectFactory()
				.createMultiPolygonType();
		for (int index = 0; index < multiPolygon.getNumGeometries(); index++) {
			final Polygon polygon = (Polygon) multiPolygon.getGeometryN(index);
			multiPolygonType.getPolygonMember().add(
					polygonConverter.createPropertyType(polygon));
		}

		return multiPolygonType;
	}

	public MultiPolygonPropertyType createPropertyType(MultiPolygon multiPolygon) {
		final MultiPolygonPropertyType multiPolygonPropertyType = getObjectFactory()
				.createMultiPolygonPropertyType();
		multiPolygonPropertyType
				.setMultiPolygon(createGeometryType(multiPolygon));
		return multiPolygonPropertyType;
	}

	public JAXBElement<MultiPolygonType> createElement(MultiPolygon multiPolygon) {
		return getObjectFactory().createMultiPolygon(
				createGeometryType(multiPolygon));
	}

}
