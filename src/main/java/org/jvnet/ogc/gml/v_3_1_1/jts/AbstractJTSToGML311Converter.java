package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.jvnet.ogc.gml.v_3_1_1.ObjectFactoryInterface;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;

import com.vividsolutions.jts.geom.Geometry;

public abstract class AbstractJTSToGML311Converter<G extends AbstractGeometryType, P, J extends Geometry>
    implements JTSToGML311ConverterInterface<G, P, J> {

  private final ObjectFactoryInterface objectFactory;
  private final JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter;

  public AbstractJTSToGML311Converter(
      ObjectFactoryInterface objectFactory,
      JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter) {
    this.objectFactory = objectFactory;
    this.srsReferenceGroupConverter = srsReferenceGroupConverter;
  }

  public ObjectFactoryInterface getObjectFactory() {
    return objectFactory;
  }

  public JTSToGML311SRSReferenceGroupConverterInterface getSrsReferenceGroupConverter() {
    return srsReferenceGroupConverter;
  }

  public final G createGeometryType(J source) {
    final G target = doCreateGeometryType(source);

    getSrsReferenceGroupConverter().convert(source, target);
    return target;

  }

  protected abstract G doCreateGeometryType(J geometry);

}
