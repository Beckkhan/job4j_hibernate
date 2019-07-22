package ru.job4j.carwarehouse.models;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 22.07.2019
 */
public class Engine {

    private int id;
    private String name;

    public Engine() {
    }

    public Engine(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Engine{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}