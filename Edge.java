package GraphFramework;

import PhoneNetworkApp.Line;
import java.util.*;

public class Edge implements Comparable<Edge> {

    public Vertex source;
    public Vertex target;
    public Vertex parent;
    public int weight;
    public static ArrayList<Line> totalEdges = new ArrayList<>();

    public Edge(Vertex source, Vertex destVer, int weight) {
        this.source = source;
        this.target = destVer;
        this.weight = weight;
    }

    
    public void displayInfo() {
        System.out.println(target.label + "(" + source.label +"," + weight+")" );

    }

    @Override
    public int compareTo(Edge o) {
        return (this.weight - o.weight);
    }

}
