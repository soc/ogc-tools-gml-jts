package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.locationtech.jts.geom.Geometry;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

public interface GML311ToJTSSRIDConverterInterface {

  void convert(ObjectLocator locator, SRSReferenceGroup source, Geometry target) throws ConversionFailedException;
}
