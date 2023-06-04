package PhoneNetworkApp;

import GraphFramework.*;
import java.util.LinkedList;

public class BluePrintsGraph extends Graph {
    @Override
    public Vertex createVertex(String label) {
        return new Office(label);
    }
    @Override
    public Line createEdge(Vertex source, Vertex destination, int weight) {
        //Create object from Line
        Line edge = new Line(source, destination, weight);
        
        edge.parent = new Office(Integer.toString(-1));
        
        int index = Integer.parseInt(source.label);
        Vertex.adjList.get(index).add(edge);       
        Edge.totalEdges.add(edge);
        return edge;

    }

}