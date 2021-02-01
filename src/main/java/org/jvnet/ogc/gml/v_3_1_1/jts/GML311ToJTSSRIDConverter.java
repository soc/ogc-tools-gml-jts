package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.text.MessageFormat;
import java.text.ParseException;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.locationtech.jts.geom.Geometry;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;

public class GML311ToJTSSRIDConverter implements GML311ToJTSSRIDConverterInterface {

  private final String[] patterns;

  public GML311ToJTSSRIDConverter(String[] patterns) {
    this.patterns = patterns;
  }

  public void convert(ObjectLocator locator, AbstractGeometryType source, Geometry target) throws ConversionFailedException {
    if (source == null)
      throw new IllegalArgumentException("The validated object is null");
    if (target == null)
      throw new IllegalArgumentException("The validated object is null");

    String srsName = source.getSrsName();

    if (srsName != null) {
      for (String pattern : patterns) {
        try {
          final MessageFormat format = new MessageFormat(pattern);
          Object[] codearray = format.parse(srsName);
          if (codearray.length > 0) {
            target.setSRID(((Number) codearray[0]).intValue());
            if (target.getUserData() == null) {
              target.setUserData(srsName);
              return;
            }
          }
        } catch (ParseException e) {
          // this pattern was not correct
          continue;
        }
      }

      if (target.getUserData() != null) {
        throw new ConversionFailedException(locator, MessageFormat.format("Could not parse SRS name [{0}].", srsName));
      } else {
        target.setUserData(srsName);
      }
    }
  }

}
