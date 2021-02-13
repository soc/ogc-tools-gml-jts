package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.AbstractRingType;
import net.opengis.gml.v_3_1_1.LinearRingPropertyType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

public class GML311ToJTSPolygonConverter extends
		AbstractGML311ToJTSConverter<PolygonType, PolygonPropertyType, Polygon> {

	// + Polygon

	private final GML311ToJTSConverterInterface<LinearRingType, LinearRingPropertyType, LinearRing> linearRingConverter;

	public GML311ToJTSPolygonConverter(
			GeometryFactory geometryFactory,
			GML311ToJTSSRIDConverterInterface sridConverter,
			GML311ToJTSConverterInterface<LinearRingType, LinearRingPropertyType, LinearRing> linearRingConverter) {
		super(geometryFactory, sridConverter);
		this.linearRingConverter = linearRingConverter;
	}

	@Override
	protected Polygon doCreateGeometry(ObjectLocator locator, PolygonType polygonType)
			throws ConversionFailedException {
		final LinearRing shell;
		if (polygonType.isSetExterior()) {
			final AbstractRingType abstractRingType = polygonType.getExterior().getValue().getRing().getValue();
			if (abstractRingType instanceof LinearRingType) {
				shell = linearRingConverter
						.createGeometry(
								locator.property("exterior",
										polygonType.getExterior())
										.property(
												"value",
												polygonType.getExterior()
														.getValue())
										.property(
												"ring",
												polygonType.getExterior()
														.getValue().getRing())
										.property("value", abstractRingType), (LinearRingType) abstractRingType); //$NON-NLS-1$
			} else {
				throw new ConversionFailedException(
						locator.property("exterior", polygonType.getExterior())
								.property("value",
										polygonType.getExterior().getValue())
								.property(
										"ring",
										polygonType.getExterior().getValue()
												.getRing()),
						"Only linear rings are supported."); //$NON-NLS-1$
			}
		} else {
			shell = null;
		}

		final LinearRing[] holes;
		if (polygonType.isSetInterior()) {
			final ObjectLocator interiorObjectLocator = locator.property(
					"interior", polygonType.getInterior()); //$NON-NLS-1$
			final List<LinearRing> holesList = new ArrayList<>(polygonType.getInterior().size());
			for (int index = 0; index < polygonType.getInterior().size(); index++) {
				final JAXBElement<AbstractRingPropertyType> ringElement = polygonType
						.getInterior().get(index);
				final ObjectLocator entryLocator = interiorObjectLocator.item(
						index, ringElement);

				final AbstractRingType abstractRingType = ringElement
						.getValue().getRing().getValue();
				if (abstractRingType instanceof LinearRingType) {
					holesList.add(linearRingConverter.createGeometry(
							entryLocator
									.property("value", ringElement.getValue())
									.property("ring",
											ringElement.getValue().getRing())
									.property(
											"value",
											ringElement.getValue().getRing()
													.getValue()),

							(LinearRingType) abstractRingType));
				} else {
					throw new ConversionFailedException(entryLocator,
							"Only linear rings are supported."); //$NON-NLS-1$
				}
			}

			holes = holesList.toArray(new LinearRing[0]);
		} else {
			holes = null;
		}
		return getGeometryFactory().createPolygon(shell, holes);
	}

	public Polygon createGeometry(ObjectLocator locator,
			PolygonPropertyType polygonPropertyType)
			throws ConversionFailedException {
		if (polygonPropertyType.isSetPolygon()) {
			return createGeometry(
					locator.property(
							"polygon", polygonPropertyType.getPolygon()), polygonPropertyType.getPolygon()); //$NON-NLS-1$
		} else {
			throw new ConversionFailedException(locator,
					"Expected [Polygon] element."); //$NON-NLS-1$
		}
	}

}
