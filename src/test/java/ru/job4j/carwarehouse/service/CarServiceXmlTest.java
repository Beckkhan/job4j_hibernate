package ru.job4j.carwarehouse.service;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 27.07.2019
 */
public class CarServiceXmlTest {

    private CarServiceXml carServiceXml = CarServiceXml.getInstance();

    @Ignore
    @Test
    public void whenAddThenDeleteCar() {
        int newCarId = carServiceXml.addNewCarWithoutParts("BMW");
        int deletedCarId = carServiceXml.removeCar(newCarId);
        assertTrue(newCarId == deletedCarId);
    }

    @Ignore
    @Test
    public void whenAddThenDeleteBodytype() {
        int newBodytypeId = carServiceXml.addNewBodytype("Shooting Brake");
        int deletedBodytypeId = carServiceXml.removeBodytype(newBodytypeId);
        assertTrue(newBodytypeId == deletedBodytypeId);
    }

    @Ignore
    @Test
    public void whenAddThenDeleteEngine() {
        int newEngineId = carServiceXml.addNewEngine("Hybrid");
        int deletedEngineId = carServiceXml.removeEngine(newEngineId);
        assertTrue(newEngineId == deletedEngineId);
    }

    @Ignore
    @Test
    public void whenAddThenDeleteTransmission() {
        int newTransmissionId = carServiceXml.addNewTransmission("Robotronic");
        int deletedTransmissionId = carServiceXml.removeTransmission(newTransmissionId);
        assertTrue(newTransmissionId == deletedTransmissionId);
    }

    @Ignore
    @Test
    public void whenUpdateParts() {
        int newBodytypeId = carServiceXml.addNewBodytype("Shooting Brake");
        int newEngineId = carServiceXml.addNewEngine("Hybrid");
        int newTransmissionId = carServiceXml.addNewTransmission("Robotronic");
        int updatedBodytypeId = carServiceXml.updateBodytype(newBodytypeId, "Shooting Brake New");
        int updatedEngineId = carServiceXml.updateEngine(newEngineId, "Hybrid New");
        int updatedTransmissionId = carServiceXml.updateTransmission(newTransmissionId, "Robotronic New");
        assertTrue(
                newBodytypeId == updatedBodytypeId
                        && newEngineId == updatedEngineId
                        && newTransmissionId == updatedTransmissionId
        );

        int newCarId = carServiceXml.addNewCar("Mercedes-Benz", updatedBodytypeId, updatedEngineId, updatedTransmissionId);
        assertTrue(carServiceXml.getAllCars().size() == 6);

        int otherBodytypeId = carServiceXml.addNewBodytype("Crossover");
        int otherEngineId = carServiceXml.addNewEngine("Gas/Diesel");
        int otherTransmissionId = carServiceXml.addNewTransmission("Multitronic");
        int updatedCarBodytypeId = carServiceXml.updateCarBodytype(newCarId, otherBodytypeId);
        int updatedCarEngineId = carServiceXml.updateCarEngine(newCarId, otherEngineId);
        int updatedCarTrasmissionId = carServiceXml.updateCarTransmission(newCarId, otherTransmissionId);
        assertTrue(
                updatedCarBodytypeId == otherBodytypeId
                        && updatedCarEngineId == otherEngineId
                        && updatedCarTrasmissionId == otherTransmissionId
        );

        int deletedCarId = carServiceXml.removeCar(newCarId);
        assertTrue(deletedCarId == newCarId
                && carServiceXml.getAllCars().size() == 5
        );

        carServiceXml.removeBodytype(updatedBodytypeId);
        carServiceXml.removeBodytype(updatedCarBodytypeId);
        carServiceXml.removeEngine(updatedEngineId);
        carServiceXml.removeEngine(updatedCarEngineId);
        carServiceXml.removeTransmission(updatedTransmissionId);
        carServiceXml.removeTransmission(updatedCarTrasmissionId);
    }
}