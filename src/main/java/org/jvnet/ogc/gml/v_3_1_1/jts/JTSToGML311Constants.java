package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.jvnet.ogc.gml.v_3_1_1.ObjectFactory;
import org.jvnet.ogc.gml.v_3_1_1.ObjectFactoryInterface;

public class JTSToGML311Constants {

	private JTSToGML311Constants() {
	}

	public static final ObjectFactoryInterface DEFAULT_OBJECT_FACTORY = new ObjectFactory();

	public static final JTSToGML311SRSReferenceGroupConverterInterface DEFAULT_SRS_REFERENCE_GROUP_CONVERTER = new JTSToGML311SRSReferenceGroupConverter();

	public static final String DEFAULT_SRID_FORMAT_PATTERN = "urn:ogc:def:crs:EPSG::{0,number,#}"; //$NON-NLS-1$
}
