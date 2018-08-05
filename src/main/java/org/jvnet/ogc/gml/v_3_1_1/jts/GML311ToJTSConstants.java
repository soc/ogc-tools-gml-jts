package org.jvnet.ogc.gml.v_3_1_1.jts;

import com.vividsolutions.jts.geom.GeometryFactory;

public class GML311ToJTSConstants {

  private GML311ToJTSConstants() {}

  public static final String[] DEFAULT_SRID_FORMAT_PATTERNS = {
      "EPSG:{0,number,integer}",
      "urn:ogc:def:crs:EPSG::{0,number,#}",
      "urn:ogc:def:crs:EPSG:{1}:{0,number,#}",
      "urn:x-ogc:def:crs:EPSG::{0,number,#}",
      "urn:x-ogc:def:crs:EPSG:{1}:{0,number,#}",
      "http://www.opengis.net/gml/srs/epsg.xml#{0,number,#}"};

  public static final GeometryFactory DEFAULT_GEOMETRY_FACTORY = new GeometryFactory();

  public static final GML311ToJTSSRIDConverterInterface DEFAULT_SRID_CONVERTER =
      new GML311ToJTSSRIDConverter(DEFAULT_SRID_FORMAT_PATTERNS);

}
