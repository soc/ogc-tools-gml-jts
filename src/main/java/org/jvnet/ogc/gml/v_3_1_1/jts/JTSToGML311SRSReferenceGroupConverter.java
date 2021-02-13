package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.text.MessageFormat;
import java.util.Objects;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

import com.vividsolutions.jts.geom.Geometry;

public class JTSToGML311SRSReferenceGroupConverter
    implements
    JTSToGML311SRSReferenceGroupConverterInterface {

  private final String sridPattern = JTSToGML311Constants.DEFAULT_SRID_FORMAT_PATTERN;

  public void convert(Geometry source, SRSReferenceGroup target) {
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);

    if (source.getUserData() instanceof String) {
      target.setSrsName((String) source.getUserData());
    }
    else if (source.getSRID() != 0) {
      target.setSrsName(MessageFormat.format(this.sridPattern, source.getSRID()));
    }
  }

}
