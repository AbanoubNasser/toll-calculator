package com.evolve.service.tollcalculator.model;

public class Car implements Vehicle{

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }
}
