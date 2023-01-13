package com.evolve.service.tollcalculator.model;

public class Foreign implements Vehicle{
    @Override
    public VehicleType getType() {
        return VehicleType.FOREIGN;
    }
}
