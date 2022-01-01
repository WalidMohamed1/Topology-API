package com.pojo;

import java.util.Map;

public class Components {
    private String type;
    private String id;
    private PhysicalProperty physicalproperty;
    private Map<String,String> netlist;

    public Components() {
    }

    public Components(String type, String id, PhysicalProperty physicalproperty, Map<String, String> netlist) {
        this.type = type;
        this.id = id;
        this.physicalproperty = physicalproperty;
        this.netlist = netlist;
    }

    public Components(Map<String, String> netlist) {
        this.netlist = netlist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PhysicalProperty getPhysicalproperty() {
        return physicalproperty;
    }

    public void setPhysicalproperty(PhysicalProperty physicalproperty) {
        this.physicalproperty = physicalproperty;
    }

    public Map<String, String> getNetlist() {
        return netlist;
    }

    public void setNetlist(Map<String, String> netlist) {
        this.netlist = netlist;
    }
}
