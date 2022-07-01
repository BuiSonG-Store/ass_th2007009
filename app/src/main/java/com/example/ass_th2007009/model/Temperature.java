package com.example.ass_th2007009.model;

import android.renderscript.Sampler;

public class Temperature {
    private Double Value;
    private String Unit;

    public Double getValue() {
        return Value;
    }

    public void setValue(Double value){
        Value=value;
    }

    public String getUnit(){return Unit;}

    public void setUnit(String unit){
        Unit = unit;
    }
}
