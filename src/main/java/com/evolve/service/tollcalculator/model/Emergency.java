package com.evolve.service.tollcalculator.model;

public class Emergency implements Vehicle{
    @Override
    public VehicleType getType() {
        return VehicleType.EMERGENCY;
    }
}
