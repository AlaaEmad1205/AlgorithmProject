package GraphFramework;

import PhoneNetworkApp.Line;
import java.util.ArrayList;
import java.util.LinkedList;


public class Vertex implements Comparable<Vertex>{

    public String label;

    public boolean isVisited;

    public int parent = -1;
   public int rank = 0;
    public int key = Integer.MAX_VALUE;

    static public ArrayList<LinkedList<Line>> adjList = new ArrayList<>(); // ADJACENCY LIST

    public Vertex(){

    }

    public Vertex(String label){
        this.label = label;
    }

    public void displayInfo(){
        System.out.println("Vertex: " + label);
    }

    @Override
    public int compareTo(Vertex o) {
        return (this.key - o.key);
    }

}
