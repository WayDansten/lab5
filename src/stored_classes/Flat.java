package stored_classes;

import stored_classes.enums.*;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class Flat implements Comparable<Flat> {
    private static int idCounter = 1;
    private static TreeSet<Integer> availableIds = new TreeSet<>();
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double area; //Значение поля должно быть больше 0
    private Integer numberOfRooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле не может быть null
    private View view; //Поле не может быть null
    private Transport transport; //Поле может быть null
    private House house; //Поле не может быть null

    public Flat(String name, Coordinates coordinates, double area, Integer numberOfRooms, Furnish furnish, View view, Transport transport, House house) {
        this.name = name;
        this.coordinates = coordinates;
        creationDate = new Date();
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;
        if (availableIds.isEmpty()) {
            id = idCounter;
            idCounter++;
        } else {
            id = availableIds.first();
            idCounter = Math.max(idCounter, availableIds.last());
            availableIds.remove(availableIds.first());
        }
    }

    public Flat(int id, String name, Coordinates coordinates, java.util.Date creationDate, double area, Integer numberOfRooms, Furnish furnish, View view, Transport transport, House house) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;
        idCounter = Math.max(idCounter, id);
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getArea() {
        return area;
    }
    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }
    public Furnish getFurnish() {
        return furnish;
    }
    public View getView() {
        return view;
    }
    public Transport getTransport() {
        return transport;
    }
    public House getHouse() {
        return house;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public int compareTo(Flat flat) {
        return (int) (area - flat.getArea());
    }
    public static void addId(int id) {
        availableIds.add(id);
    }
    @Override
    public String toString() {
        return "id = " + id + ", name = " + name + ", creation_date = " + creationDate + ", area = " + area + ", number_of_rooms = " + numberOfRooms +
                ", furnish = " + furnish + ", view = " + view + ", transport = " + transport + ";\ncoordinates: " + coordinates +
                ";\nhouse: " + house;
    }
}
