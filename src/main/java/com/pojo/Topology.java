package com.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Topology {
    private String id;
    private List<Components> components =new ArrayList<>();

    public Topology(String id, List<Components> components) {
        this.id = id;
        this.components = components;
    }

    public Topology(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Components> getComponents() {
        return components;
    }

    public void setComponets(List<Components> components) {
        this.components = components;
    }


    public List<Components> queryDevicesWithNetlistNode(final String netlistID){
        final List<Components> components = new ArrayList<>();
        for (final Components component : this.components) {
            for (final String nodeID : component.getNetlist().keySet()) {
                if (nodeID.equals(netlistID)) {
                    components.add(component);
                }
            }
        }
        if (components.isEmpty()) {
            throw new NoSuchElementException("Not Found!");
        }
        return components;
    }

}
