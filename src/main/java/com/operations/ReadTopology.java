package com.operations;

import com.pojo.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.naming.CannotProceedException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadTopology {

    /** -> 1 Read a topology from a given JSON file and store it in the memory. **/
    public Topology readJSON(String filename) throws  Exception {
        final FileReader jsonFile = new FileReader(filename);
        final JSONParser jsonParser = new JSONParser();
        final JSONObject jsonTopology = (JSONObject) jsonParser.parse(jsonFile);

        jsonFile.close();

        /** -> Read a whole topology fragments. **/
        final String idTopology = (String) jsonTopology.get("id");
        final JSONArray ComponentsArrayList = (JSONArray) jsonTopology.get("components");


        /** -> Read a component fragments. **/
        final ArrayList<Components> components = new ArrayList<>();
        for (final Object object : ComponentsArrayList) {
            final JSONObject currentComponent = (JSONObject) object;
            final String idComponent = (String) currentComponent.get("id");
            final String type = (String) currentComponent.get("type");

            /** -> Read a netlist fragments. **/
            final JSONObject jsonNetlist = (JSONObject) currentComponent.get("netlist");
            final Map<String, String> netlist = new HashMap<>();
            jsonNetlist.keySet().forEach(key ->
            {
                final String value = (String) jsonNetlist.get(key);
                netlist.put((String) key, value);
            });

            /** -> Read a PhysicalProperty fragments. **/
            JSONObject jsonPhysical;
            PhysicalProperty physicalproperty;
            switch (type) {
                case "resistor":
                    jsonPhysical = (JSONObject) currentComponent.get("resistance");
                    physicalproperty = new ResistorResistance("resistance");
                    break;
                case "nmos":
                    jsonPhysical = (JSONObject) currentComponent.get("m(l)");
                    physicalproperty = new NmosTransistor("m(l)");
                    break;
                default:
                    throw new CannotProceedException(type + " Not Found!");
            }
            final double defaultValue = ((Number) jsonPhysical.get("default")).doubleValue();
            physicalproperty.setDefault(defaultValue);
            final double min = ((Number) jsonPhysical.get("max")).doubleValue();
            physicalproperty.setMin(min);
            final double max = ((Number) jsonPhysical.get("max")).doubleValue();
            physicalproperty.setMax(max);


            final Components component = new Components(idComponent, type, physicalproperty, netlist);
            components.add(component);
        }
        return new Topology(idTopology, components);
    }
}
