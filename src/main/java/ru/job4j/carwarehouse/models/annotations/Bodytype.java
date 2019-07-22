package ru.job4j.carwarehouse.models.annotations;

import javax.persistence.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 22.07.2019
 */

@Entity
@Table(name = "bodytype")
public class Bodytype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Bodytype() {
    }

    public Bodytype(String name) {
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
        return "Bodytype{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}