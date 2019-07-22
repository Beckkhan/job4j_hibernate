package ru.job4j.carwarehouse.models;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 22.07.2019
 */
public class Car {

    private int id;
    private String brand;
    private Bodytype bodytype;
    private Engine engine;
    private Transmission transmission;

    public Car() {
    }

    public Car(String brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Bodytype getBodytype() {
        return bodytype;
    }

    public void setBodytype(Bodytype bodytype) {
        this.bodytype = bodytype;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id
                + ", brand='" + brand
                + ", bodytype=" + bodytype
                + ", engine=" + engine
                + ", transmission=" + transmission
                + '}';
    }
}