package com.operations;

import com.pojo.Components;
import com.pojo.Topology;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WriteTopology {

    /** -> 2 Write a given topology from the memory to a JSON file. **/
    public void writeJSON(final Topology topology) throws Exception {

        final JSONObject jsonTopology = new JSONObject();

        /** -> Write a whole topology fragments. **/
        jsonTopology.put("id", topology.getId());

        /** -> Write a a component fragment. **/
        final JSONArray componentArrayList = new JSONArray();
        final List<Components> currentComponent = topology.getComponents();

        Map<String, Object> Head = new LinkedHashMap<>();
        for (final Components component : currentComponent) {
            Head.clear();
            Head.put("id", component.getId());
            Head.put("type", component.getType());

            /** -> Write a netlist fragments. **/
            final Map<String, String> netlist = new LinkedHashMap<>(component.getNetlist());
            Head.put("netlist", netlist);

            /** -> Write a PhysicalProperty fragment. **/
            final Map<String, java.io.Serializable> physicalProperty = new LinkedHashMap<>();
            physicalProperty.put("default", component.getPhysicalproperty().getDefault());
            physicalProperty.put("min", component.getPhysicalproperty().getMin());
            physicalProperty.put("max", component.getPhysicalproperty().getMax());
            Head.put(component.getPhysicalproperty().getPname(), physicalProperty);

            componentArrayList.add(Head);
        }


        jsonTopology.put("components", componentArrayList);
        final FileWriter jsonfile = new FileWriter("jsonFiles\\newTopology.json");

        jsonfile.write(jsonTopology.toJSONString());
        jsonfile.flush();
        jsonfile.close();

    }
}
