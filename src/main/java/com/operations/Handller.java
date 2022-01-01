package com.operations;

import com.pojo.Topology;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Handller {
    private List<Topology> topologies = new ArrayList<>();


    public Topology TopologyById(String id) {

        return topologies.get(getIndex(id));

    }

    private int getIndex(String id) {
        for (int i = 0; i < topologies.size(); i++) {
            if (topologies.get(i).getId().equals(id)) {
                return i;
            }
        }
        throw new NoSuchElementException("Not found!");
    }

    public void setTopologies(Topology topology)
    {
        topologies.add(topology);
    }

    public List<Topology> getTopologies()
    {
        return topologies;
    }

    public void deleteTopology(final String id){
        topologies.remove(getIndex(id));
    }
}


