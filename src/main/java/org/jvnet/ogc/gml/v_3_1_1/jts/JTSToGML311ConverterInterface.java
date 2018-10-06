package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import org.locationtech.jts.geom.Geometry;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;

public interface JTSToGML311ConverterInterface<G extends AbstractGeometryType, P, J extends Geometry> {
  G createGeometryType(J geometry);

  P createPropertyType(J geometry);

  JAXBElement<? extends G> createElement(J geometry);
}
