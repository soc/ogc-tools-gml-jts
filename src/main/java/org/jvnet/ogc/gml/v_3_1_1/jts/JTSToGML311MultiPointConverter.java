package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import org.jvnet.ogc.gml.v_3_1_1.ObjectFactoryInterface;

import net.opengis.gml.v_3_1_1.MultiPointPropertyType;
import net.opengis.gml.v_3_1_1.MultiPointType;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;

import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;

public class JTSToGML311MultiPointConverter
		extends
		AbstractJTSToGML311Converter<MultiPointType, MultiPointPropertyType, MultiPoint> {
	private final JTSToGML311ConverterInterface<PointType, PointPropertyType, Point> pointConverter;

	public JTSToGML311MultiPointConverter(
			ObjectFactoryInterface objectFactory,
			JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter,
			JTSToGML311ConverterInterface<PointType, PointPropertyType, Point> pointConverter) {
		super(objectFactory, srsReferenceGroupConverter);
		this.pointConverter = pointConverter;
	}

	@Override
	protected MultiPointType doCreateGeometryType(MultiPoint multiPoint) {
		final MultiPointType multiPointType = getObjectFactory()
				.createMultiPointType();

		for (int index = 0; index < multiPoint.getNumGeometries(); index++) {
			final Point point = (Point) multiPoint.getGeometryN(index);
			multiPointType.getPointMember().add(
					pointConverter.createPropertyType(point));
		}
		return multiPointType;
	}

	public MultiPointPropertyType createPropertyType(MultiPoint multiPoint) {
		final MultiPointPropertyType multiPointPropertyType = getObjectFactory()
				.createMultiPointPropertyType();
		multiPointPropertyType.setMultiPoint(createGeometryType(multiPoint));
		return multiPointPropertyType;
	}

	public JAXBElement<MultiPointType> createElement(MultiPoint multiPoint) {
		return getObjectFactory().createMultiPoint(
				createGeometryType(multiPoint));
	}

}
