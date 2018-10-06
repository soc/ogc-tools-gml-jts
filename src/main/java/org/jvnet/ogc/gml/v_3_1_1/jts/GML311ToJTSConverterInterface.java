package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.locationtech.jts.geom.Geometry;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;

public interface GML311ToJTSConverterInterface<G extends AbstractGeometryType, P, J extends Geometry> {
  J createGeometry(ObjectLocator locator, G geometryType) throws ConversionFailedException;

  J createGeometry(ObjectLocator locator, P propertyType) throws ConversionFailedException;
}
