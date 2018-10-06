package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.locationtech.jts.geom.Geometry;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

public interface JTSToGML311SRSReferenceGroupConverterInterface {

  void convert(Geometry source, SRSReferenceGroup target);
}
