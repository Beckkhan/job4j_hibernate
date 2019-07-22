package ru.job4j.carwarehouse.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import ru.job4j.carwarehouse.models.*;
import java.util.List;
import java.util.function.Function;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 22.07.2019
 */
public class CarServiceXml {

    private SessionFactory factory = new Configuration()
            .configure().buildSessionFactory();

    private static CarServiceXml carServiceXml = new CarServiceXml();

    public static CarServiceXml getInstance() {
        return carServiceXml;
    }

    private CarServiceXml() {
    }

    private <T> T trx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if (transaction.getStatus().equals(TransactionStatus.ACTIVE)) {
                transaction.commit();
            }
            session.close();
        }
    }

    public int addNewCarWithoutParts(String brand) {
        Car car = new Car(brand);
        int result = this.trx(
                session -> {
                    session.save(car);
                    return car.getId();
                }
        );
        return result;
    }

    public int addNewCar(String brand, int bodytypeId, int engineId, int transmissionId) {
        Car car = new Car(brand);
        int result = this.trx(
                session -> {
                    Bodytype bodytype = session.get(Bodytype.class, bodytypeId);
                    Engine engine = session.get(Engine.class, engineId);
                    Transmission transmission = session.get(Transmission.class, transmissionId);
                    car.setBodytype(bodytype);
                    car.setEngine(engine);
                    car.setTransmission(transmission);
                    session.save(car);
                    return car.getId();
                }
        );
        return result;
    }

    public List<Car> getAllCars() {
        return this.trx(
                session -> session.createQuery("from Car", Car.class).list()
        );
    }

    public int updateCarBrand(int carId, String newBrand) {
        return this.trx(
                session -> {
                    Car car = session.get(Car.class, carId);
                    car.setBrand(newBrand);
                    session.update(car);
                    return car.getId();
                }
        );
    }

    public int updateCarBodytype(int carId, int bodytypeId) {
        return this.trx(
                session -> {
                    Car car = session.get(Car.class, carId);
                    Bodytype bodytype = session.get(Bodytype.class, bodytypeId);
                    car.setBodytype(bodytype);
                    session.update(car);
                    return car.getBodytype().getId();
                }
        );
    }

    public int updateCarEngine(int carId, int engineId) {
        return this.trx(
                session -> {
                    Car car = session.get(Car.class, carId);
                    Engine engine = session.get(Engine.class, engineId);
                    car.setEngine(engine);
                    session.update(car);
                    return car.getEngine().getId();
                }
        );
    }

    public int updateCarTransmission(int carId, int transmissionId) {
        return this.trx(
                session -> {
                    Car car = session.get(Car.class, carId);
                    Transmission transmission = session.get(Transmission.class, transmissionId);
                    car.setTransmission(transmission);
                    session.update(car);
                    return car.getTransmission().getId();
                }
        );
    }

    public int removeCar(int carId) {
        return this.trx(
                session -> {
                    Car car = session.get(Car.class, carId);
                    session.delete(car);
                    return car.getId();
                }
        );
    }

    public List<Bodytype> getAllBodytypes() {
        return this.trx(
                session -> session.createQuery("from Bodytype", Bodytype.class).list()
        );
    }

    public List<Engine> getAllEngines() {
        return this.trx(
                session -> session.createQuery("from Engine", Engine.class).list()
        );
    }

    public List<Transmission> getAllTransmissions() {
        return this.trx(
                session -> session.createQuery("from Transmission", Transmission.class).list()
        );
    }

    public int addNewBodytype(String name) {
        Bodytype bodytype = new Bodytype(name);
        return this.trx(
                session -> {
                    session.save(bodytype);
                    return bodytype.getId();
                }
        );
    }

    public int updateBodytype(int bodytypeId, String newName) {
        return this.trx(
                session -> {
                    Bodytype bodytype = session.get(Bodytype.class, bodytypeId);
                    bodytype.setName(newName);
                    session.update(bodytype);
                    return bodytype.getId();
                }
        );
    }

    public int removeBodytype(int bodytypeId) {
        return this.trx(
                session -> {
                    Bodytype bodytype = session.get(Bodytype.class, bodytypeId);
                    session.delete(bodytype);
                    return bodytype.getId();
                }
        );
    }

    public int addNewEngine(String name) {
        Engine engine = new Engine(name);
        return this.trx(
                session -> {
                    session.save(engine);
                    return engine.getId();
                }
        );
    }

    public int updateEngine(int engineId, String newName) {
        return this.trx(
                session -> {
                    Engine engine = session.get(Engine.class, engineId);
                    engine.setName(newName);
                    session.update(engine);
                    return engine.getId();
                }
        );
    }

    public int removeEngine(int engineId) {
        return this.trx(
                session -> {
                    Engine engine = session.get(Engine.class, engineId);
                    session.delete(engine);
                    return engine.getId();
                }
        );
    }

    public int addNewTransmission(String name) {
        Transmission transmission = new Transmission(name);
        return this.trx(
                session -> {
                    session.save(transmission);
                    return transmission.getId();
                }
        );
    }

    public int updateTransmission(int transmissionId, String newName) {
        return this.trx(
                session -> {
                    Transmission transmission = session.get(Transmission.class, transmissionId);
                    transmission.setName(newName);
                    session.update(transmission);
                    return transmission.getId();
                }
        );
    }

    public int removeTransmission(int transmissionId) {
        return this.trx(
                session -> {
                    Transmission transmission = session.get(Transmission.class, transmissionId);
                    session.delete(transmission);
                    return transmission.getId();
                }
        );
    }
}