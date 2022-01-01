package com.main;

import com.operations.Handller;
import com.operations.ReadTopology;
import com.operations.WriteTopology;
import com.pojo.Topology;


import java.util.Scanner;

public class Main {

    public static void main(final String[] args) throws Exception {

        Topology topology;
        final Handller handller = new Handller();
        final Scanner scanner = new Scanner(System.in);
        final ReadTopology read = new ReadTopology();
        final WriteTopology write = new WriteTopology();
        /** ----------------------------------------------- **/

        /** -> 1 Read a topology from a given JSON file and store it in the memory. **/

        topology = read.readJSON("jsonFiles\\topology.json");
        handller.setTopologies(topology);
        System.out.println("File jsonFiles/topology.json has been read !");

        /** -> 2 Write a given topology from the memory to a JSON file. **/

        topology = handller.TopologyById(topology.getId());
        write.writeJSON(topology);
        System.out.println("File jsonFiles/newTopology.json has been create !");

        /** -> 3 Query about which topologies are currently in the memory. **/

        handller.getTopologies().forEach((topologies) ->
                System.out.println("Currently topology ID in a memory: "+topologies.getId()));

        /** -> 4 Query about which devices are in a given topology. **/

        topology = handller.TopologyById(topology.getId());
        topology.getComponents().forEach((components) ->
                System.out.println("Currently topology devices in a memory: "+ components.getId()));

        /** -> 5 Query about which devices are connected to a given netlist node in a given topology. **/

        topology = handller.TopologyById(topology.getId());
        System.out.print("Enter netlist node eg., t1,drain,gate: ");
        String netListID = scanner.nextLine();
        topology.queryDevicesWithNetlistNode(netListID).forEach((components) ->
                System.out.println("Currently topology device is connected to" +
                        " a given netlist node in a memory: "+components.getId()));

        /** -> 6 Delete a given topology from memory. **/

        handller.deleteTopology(topology.getId());
        System.out.print("Topology with ID: "+topology.getId()+ " has been delete !");

    }

}
