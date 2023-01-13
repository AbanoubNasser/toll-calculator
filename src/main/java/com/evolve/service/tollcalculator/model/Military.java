package com.evolve.service.tollcalculator.model;

public class Military implements Vehicle{
    @Override
    public VehicleType getType() {
        return VehicleType.MILITARY;
    }
}
