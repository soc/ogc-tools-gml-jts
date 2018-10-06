package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Validator;

import org.locationtech.jts.geom.Geometry;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.GeometryPropertyType;

@SuppressWarnings("deprecation")
public class JAXBContextImpl extends javax.xml.bind.JAXBContext {

  private final javax.xml.bind.JAXBContext context;
  private final JTSToGML311ConverterInterface<AbstractGeometryType, GeometryPropertyType, Geometry> marshallerConverter;
  private final GML311ToJTSConverterInterface<AbstractGeometryType, Object, Geometry> unmarshallerConverter;

  public JAXBContextImpl(
      javax.xml.bind.JAXBContext context,
      JTSToGML311ConverterInterface<AbstractGeometryType, GeometryPropertyType, Geometry> marshallerConverter,
      GML311ToJTSConverterInterface<AbstractGeometryType, Object, Geometry> unmarshallerConverter) {
    super();
    this.context = context;
    this.marshallerConverter = marshallerConverter;
    this.unmarshallerConverter = unmarshallerConverter;
  }

  @Override
  public Unmarshaller createUnmarshaller() throws JAXBException {
    return new UnmarshallerImpl(context.createUnmarshaller(), unmarshallerConverter);
  }

  @Override
  public Marshaller createMarshaller() throws JAXBException {
    return new MarshallerImpl(context.createMarshaller(), marshallerConverter);
  }

  @Override
  public Validator createValidator() {
    throw new UnsupportedOperationException();
  }

}
