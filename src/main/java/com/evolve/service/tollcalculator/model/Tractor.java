package com.evolve.service.tollcalculator.model;

public class Tractor implements Vehicle{
    @Override
    public VehicleType getType() {
        return VehicleType.TRACTOR;
    }
}
