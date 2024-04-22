package com.example.oopandroidapi;

import java.math.BigDecimal;

public class WorkEfficiencyData {
    public Double selfEfficiency;

    public WorkEfficiencyData(Double selfEfficency) {
        this.selfEfficiency = selfEfficency;
    }

    public void setSelfEfficiency(Double selfEfficiency){
        this.selfEfficiency = selfEfficiency;

    }
    public Double getSelfEfficiency(){
        return selfEfficiency;
    }

}
