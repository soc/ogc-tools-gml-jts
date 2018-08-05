package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.CoordType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;

import org.jvnet.jaxb2_commons.locator.ItemObjectLocator;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

public class GML311ToJTSLineStringConverter
    extends
    AbstractGML311ToJTSConverter<LineStringType, LineStringPropertyType, LineString> {

  // + LineString

  private final GML311ToJTSCoordinateConverter coordinateConverter;
  private final GML311ToJTSConverterInterface<PointType, PointPropertyType, Point> pointConverter;

  public GML311ToJTSLineStringConverter(
      GeometryFactory geometryFactory,
      GML311ToJTSSRIDConverterInterface sridConverter,
      GML311ToJTSCoordinateConverter coordinateConverter,
      GML311ToJTSConverterInterface<PointType, PointPropertyType, Point> pointConverter) {
    super(geometryFactory, sridConverter);
    this.coordinateConverter = coordinateConverter;
    this.pointConverter = new GML311ToJTSPointConverter(geometryFactory, sridConverter, coordinateConverter);
  }

  @Override
  protected LineString doCreateGeometry(ObjectLocator locator, LineStringType lineStringType)
      throws ConversionFailedException {
    if (lineStringType.isSetPosOrPointPropertyOrPointRep()) {
      final List<Coordinate> coordinates = new LinkedList<>();
      final ObjectLocator fieldLocator = locator
          .property("PosOrPointPropertyOrPointRep", lineStringType.getPosOrPointPropertyOrPointRep()); //$NON-NLS-1$
      for (int index = 0; index < lineStringType.getPosOrPointPropertyOrPointRep().size(); index++) {
        final JAXBElement<?> item = lineStringType.getPosOrPointPropertyOrPointRep().get(index);
        final ItemObjectLocator itemLocator = fieldLocator.item(index, item);
        final Object value = item.getValue();
        final ObjectLocator itemValueLocator = itemLocator.property("value", value); //$NON-NLS-1$

        if (value instanceof PointPropertyType) {
          coordinates.add(pointConverter.createGeometry(itemValueLocator, (PointPropertyType) value).getCoordinate());
        } else if (value instanceof CoordType) {
          coordinates.add(coordinateConverter.createCoordinate(itemValueLocator, (CoordType) value));
        } else if (value instanceof DirectPositionType) {
          coordinates.add(coordinateConverter.createCoordinate(itemValueLocator, (DirectPositionType) value));
        } else {
          throw new ConversionFailedException(itemLocator, "Unexpected type."); //$NON-NLS-1$
        }
      }
      final Coordinate[] coordinatesArray = coordinates.toArray(new Coordinate[0]);
      return getGeometryFactory().createLineString(coordinatesArray);
    } else if (lineStringType.isSetPosList()) {
      final Coordinate[] coordinates = coordinateConverter.createCoordinates(
          locator.property("posList", lineStringType.getPosList()), lineStringType.getPosList()); //$NON-NLS-1$
      return getGeometryFactory().createLineString(coordinates);
    } else if (lineStringType.isSetCoordinates()) {
      final Coordinate[] coordinates = coordinateConverter
          .createCoordinates(locator.property(
              "coordinates", lineStringType.getCoordinates()), lineStringType.getCoordinates()); //$NON-NLS-1$

      return getGeometryFactory().createLineString(coordinates);
    } else {
      throw new ConversionFailedException(locator);
    }
  }

  public LineString createGeometry(ObjectLocator locator, LineStringPropertyType lineStringPropertyType)
      throws ConversionFailedException {
    if (lineStringPropertyType.isSetLineString()) {
      return createGeometry(
          locator.property("lineString", lineStringPropertyType.getLineString()), lineStringPropertyType.getLineString()); //$NON-NLS-1$
    } else {
      throw new ConversionFailedException(locator, "Expected [LineString] element."); //$NON-NLS-1$
    }
  }

}
