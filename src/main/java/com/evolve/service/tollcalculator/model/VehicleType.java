package com.evolve.service.tollcalculator.model;

public enum VehicleType {

    CAR("Car"),
    MOTORBIKE("Motorbike"),
    MILITARY("Military"),
    TRACTOR("Tractor"),
    EMERGENCY("Emergency"),
    FOREIGN("Foreign"),
    DIPLOMAT("Diplomat");

    private final String type;

    VehicleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
