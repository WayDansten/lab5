package management.utility;

import stored_classes.Flat;

public class Unparser {
    public static String FlatToCSV(Flat flat) {
        return flat.getId() + "," + flat.getName() + "," + flat.getCoordinates().getX() + "," + flat.getCoordinates().getY()
                + "," + flat.getCreationDate() + "," + flat.getArea() + "," + flat.getNumberOfRooms() + "," + flat.getFurnish()
                + "," + flat.getView() + "," + flat.getTransport() + "," + flat.getHouse().getName() + "," + flat.getHouse().getYear()
                + "," + flat.getHouse().getNumberOfFloors() + "," + flat.getHouse().getNumberOfLifts() + "\n";
    }
}
