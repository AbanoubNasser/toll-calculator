package com.evolve.service.tollcalculator.model;

public class Diplomat implements Vehicle{

    @Override
    public VehicleType getType() {
        return VehicleType.DIPLOMAT;
    }
}
