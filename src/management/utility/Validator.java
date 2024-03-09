package management.utility;

import stored_classes.Flat;

import java.util.TreeSet;

public class Validator {
    public static boolean validate(Flat flat) {
        return flat.getId() > 0 && !flat.getName().isEmpty() && flat.getName() != null && flat.getCoordinates() != null && flat.getCreationDate() != null &&
                flat.getArea() > 0 && flat.getNumberOfRooms() > 0 && flat.getFurnish() != null && flat.getView() != null && flat.getTransport() != null &&
                flat.getHouse() != null && flat.getCoordinates().getX() < 600 && flat.getCoordinates().getX() != null && flat.getCoordinates().getY() != null &&
                flat.getHouse().getYear() > 0 && flat.getHouse().getNumberOfFloors() > 0 && flat.getHouse().getNumberOfLifts() > 0;
    }
    public static void validateAll(TreeSet<Flat> flats) {
        for (Flat flat : flats) {
            if (!Validator.validate(flat)) {
                flats.remove(flat);
            }
        }
    }
}
