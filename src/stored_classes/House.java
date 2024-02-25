package stored_classes;

public class House {
    private String name; //Поле может быть null
    private long year; //Значение поля должно быть больше 0
    private long numberOfFloors; //Значение поля должно быть больше 0
    private Integer numberOfLifts; //Значение поля должно быть больше 0

    public House(String name, long year, long numberOfFloors, Integer numberOfLifts) {
        this.name = name;
        this.year = year;
        this.numberOfFloors = numberOfFloors;
        this.numberOfLifts = numberOfLifts;
    }
    public String getName() {
        return name;
    }
    public long getYear() {
        return year;
    }
    public long getNumberOfFloors() {
        return numberOfFloors;
    }
    public Integer getNumberOfLifts() {
        return numberOfLifts;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setYear(long year) {
        this.year = year;
    }
    public void setNumberOfFloors(long numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
    public void setNumberOfLifts(Integer numberOfLifts) {
        this.numberOfLifts = numberOfLifts;
    }

    @Override
    public String toString() {
        return "name = " + name + ", year = " + year + ", number_of_floors = " + numberOfFloors + ", number_of_lifts = " + numberOfLifts;
    }
}
