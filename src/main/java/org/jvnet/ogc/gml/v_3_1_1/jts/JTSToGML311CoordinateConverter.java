package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.jvnet.ogc.gml.v_3_1_1.ObjectFactoryInterface;
import org.locationtech.jts.geom.Coordinate;

import net.opengis.gml.v_3_1_1.DirectPositionType;

public class JTSToGML311CoordinateConverter {
  private final ObjectFactoryInterface objectFactory;

  @SuppressWarnings("unused")
  private final JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter;

  public JTSToGML311CoordinateConverter(
      ObjectFactoryInterface objectFactory,
      JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter) {
    super();
    this.objectFactory = objectFactory;
    this.srsReferenceGroupConverter = srsReferenceGroupConverter;
  }

  public DirectPositionType convertCoordinate(Coordinate coordinate) {
    final DirectPositionType directPosition = objectFactory.createDirectPositionType();

    directPosition.getValue().add(coordinate.x);
    directPosition.getValue().add(coordinate.y);
    if (!Double.isNaN(coordinate.z)) {
      directPosition.getValue().add(coordinate.z);
    }
    return directPosition;

  }

  public DirectPositionType[] convertCoordinates(Coordinate[] coordinates) {
    if (coordinates == null) {
      return null;
    } else {
      final DirectPositionType[] directPositions = new DirectPositionType[coordinates.length];
      for (int index = 0; index < coordinates.length; index++) {
        directPositions[index] = convertCoordinate(coordinates[index]);
      }
      return directPositions;
    }
  }
}
