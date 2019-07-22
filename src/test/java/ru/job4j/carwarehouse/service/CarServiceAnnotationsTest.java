package ru.job4j.carwarehouse.service;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 22.07.2019
 */
public class CarServiceAnnotationsTest {

    private CarServiceAnnotations carService = CarServiceAnnotations.getInstance();

    @Test
    public void whenAddThenDeleteCar() {
        int newCarId = carService.addNewCarWithoutParts("BMW");
        int deletedCarId = carService.removeCar(newCarId);
        assertTrue(newCarId == deletedCarId);
    }

    @Test
    public void whenAddThenDeleteBodytype() {
        int newBodytypeId = carService.addNewBodytype("Shooting Brake");
        int deletedBodytypeId = carService.removeBodytype(newBodytypeId);
        assertTrue(newBodytypeId == deletedBodytypeId);
    }

    @Test
    public void whenAddThenDeleteEngine() {
        int newEngineId = carService.addNewEngine("Hybrid");
        int deletedEngineId = carService.removeEngine(newEngineId);
        assertTrue(newEngineId == deletedEngineId);
    }

    @Test
    public void whenAddThenDeleteTransmission() {
        int newTransmissionId = carService.addNewTransmission("Robotronic");
        int deletedTransmissionId = carService.removeTransmission(newTransmissionId);
        assertTrue(newTransmissionId == deletedTransmissionId);
    }

    @Test
    public void whenUpdateParts() {
        int newBodytypeId = carService.addNewBodytype("Shooting Brake");
        int newEngineId = carService.addNewEngine("Hybrid");
        int newTransmissionId = carService.addNewTransmission("Robotronic");
        int updatedBodytypeId = carService.updateBodytype(newBodytypeId, "Shooting Brake New");
        int updatedEngineId = carService.updateEngine(newEngineId, "Hybrid New");
        int updatedTransmissionId = carService.updateTransmission(newTransmissionId, "Robotronic New");
        assertTrue(
                newBodytypeId == updatedBodytypeId
                        && newEngineId == updatedEngineId
                        && newTransmissionId == updatedTransmissionId
        );

        int newCarId = carService.addNewCar("Mercedes-Benz", updatedBodytypeId, updatedEngineId, updatedTransmissionId);
        assertTrue(carService.getAllCars().size() == 6);

        int otherBodytypeId = carService.addNewBodytype("Crossover");
        int otherEngineId = carService.addNewEngine("Gas/Diesel");
        int otherTransmissionId = carService.addNewTransmission("Multitronic");
        int updatedCarBodytypeId = carService.updateCarBodytype(newCarId, otherBodytypeId);
        int updatedCarEngineId = carService.updateCarEngine(newCarId, otherEngineId);
        int updatedCarTrasmissionId = carService.updateCarTransmission(newCarId, otherTransmissionId);
        assertTrue(
                updatedCarBodytypeId == otherBodytypeId
                        && updatedCarEngineId == otherEngineId
                        && updatedCarTrasmissionId == otherTransmissionId
        );

        int deletedCarId = carService.removeCar(newCarId);
        assertTrue(deletedCarId == newCarId
                && carService.getAllCars().size() == 5
        );

        carService.removeBodytype(updatedBodytypeId);
        carService.removeBodytype(updatedCarBodytypeId);
        carService.removeEngine(updatedEngineId);
        carService.removeEngine(updatedCarEngineId);
        carService.removeTransmission(updatedTransmissionId);
        carService.removeTransmission(updatedCarTrasmissionId);
    }
}