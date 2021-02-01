package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.locationtech.jts.geom.Geometry;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;

public class NullGML311ToJTSSRIDConverter implements GML311ToJTSSRIDConverterInterface {

  public void convert(ObjectLocator locator, AbstractGeometryType source, Geometry target) {}

}
