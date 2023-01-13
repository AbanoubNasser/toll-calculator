package com.evolve.service.tollcalculator.model;

public class Motorbike implements Vehicle{
    @Override
    public VehicleType getType() {
        return VehicleType.MOTORBIKE;
    }
}
