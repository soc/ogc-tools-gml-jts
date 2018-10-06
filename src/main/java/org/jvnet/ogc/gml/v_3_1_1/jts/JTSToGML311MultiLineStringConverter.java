package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import org.jvnet.ogc.gml.v_3_1_1.ObjectFactoryInterface;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.MultiLineStringPropertyType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;

public class JTSToGML311MultiLineStringConverter
    extends AbstractJTSToGML311Converter<MultiLineStringType, MultiLineStringPropertyType, MultiLineString> {
  private final JTSToGML311ConverterInterface<LineStringType, LineStringPropertyType, LineString> lineStringConverter;

  public JTSToGML311MultiLineStringConverter(
      ObjectFactoryInterface objectFactory,
      JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter,
      JTSToGML311ConverterInterface<LineStringType, LineStringPropertyType, LineString> lineStringConverter) {
    super(objectFactory, srsReferenceGroupConverter);
    this.lineStringConverter = lineStringConverter;
  }

  protected MultiLineStringType doCreateGeometryType(
      MultiLineString multiLineString) {
    final MultiLineStringType multiLineStringType = getObjectFactory().createMultiLineStringType();
    for (int index = 0; index < multiLineString.getNumGeometries(); index++) {
      final LineString lineString = (LineString) multiLineString.getGeometryN(index);
      multiLineStringType.getLineStringMember().add(lineStringConverter.createPropertyType(lineString));
    }
    return multiLineStringType;
  }

  public MultiLineStringPropertyType createPropertyType(
      MultiLineString multiLineString) {
    final MultiLineStringPropertyType multiLineStringPropertyType = getObjectFactory().createMultiLineStringPropertyType();
    multiLineStringPropertyType.setMultiLineString(createGeometryType(multiLineString));
    return multiLineStringPropertyType;
  }

  public JAXBElement<MultiLineStringType> createElement(
      MultiLineString multiLineString) {
    return getObjectFactory().createMultiLineString(createGeometryType(multiLineString));
  }
}
