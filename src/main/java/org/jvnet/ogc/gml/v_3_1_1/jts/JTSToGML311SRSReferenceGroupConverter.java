package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.text.MessageFormat;

import org.locationtech.jts.geom.Geometry;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

public class JTSToGML311SRSReferenceGroupConverter implements JTSToGML311SRSReferenceGroupConverterInterface {

  private final String sridPattern = JTSToGML311Constants.DEFAULT_SRID_FORMAT_PATTERN;

  public void convert(Geometry source, SRSReferenceGroup target) {
    if (source == null)
      throw new IllegalArgumentException("The validated object is null");
    if (target == null)
      throw new IllegalArgumentException("The validated object is null");

    if (source.getUserData() instanceof String) {
      target.setSrsName((String) source.getUserData());
    } else if (source.getSRID() != 0) {
      target.setSrsName(MessageFormat.format(this.sridPattern, source.getSRID()));
    }
  }
}
