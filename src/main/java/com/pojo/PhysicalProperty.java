package com.pojo;

public class PhysicalProperty {
    protected  String pname;
    private double Default;
    private double min;
    private double max;

    public PhysicalProperty(String pname) {
        pname = pname;
    }

    public PhysicalProperty() {
    }


    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        pname = pname;
    }

    public double getDefault() {
        return Default;
    }

    public void setDefault(double aDefault) {
        Default = aDefault;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
