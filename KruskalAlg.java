package GraphFramework;
import java.util.*;
import PhoneNetworkApp.*;


public class KruskalAlg extends MSTAlgorithm {

    public KruskalAlg() {
    }

    public KruskalAlg(Graph graph) {
        super(graph);
    }

    // --------------------- findParent ---------------------
    public int findParent(Vertex vertex) {
        if (vertex.parent == -1) {
            return Integer.parseInt(vertex.label);
        } // END IF

        while (vertex.parent != -1) {
            vertex = graph.Vertices.get(vertex.parent);
        }

        return Integer.parseInt(vertex.label);
    }// END FIND PARENT METHOD

    // --------------------- isCycle ---------------------
    public boolean isCycle(Vertex A, Vertex B) {
        return findParent(A) == findParent(B);

    }// END IS CYCLE METHOD

    // --------------------- union ---------------------
    public void union(Vertex A, Vertex B) {
        int parentA = findParent(A);
        int parentB = findParent(B);

        if (A.rank == B.rank) {
            graph.Vertices.get(parentB).parent = parentA;
            A.rank++;
        } else if (A.rank > B.rank) {
            graph.Vertices.get(parentB).parent = parentA;
        } else {
            graph.Vertices.get(parentA).parent = parentB;
        }
    }

    // ------------------------------------------- Kruskal ---------------------------------------------------
    public void Kruskal() {
        // START TIME
        long start = System.currentTimeMillis();

        int eCounter = 0; // EDGE COUNTER
        int k = 0; // LOOP COUNTER

        // SORT EDGES
        Collections.sort(Line.totalEdges);

        while (eCounter < graph.Vertices.size()) {

            // REACH LAST EDGE IN ARRAY
            if (k == Line.totalEdges.size()) {
                break;
            }// END IF

            Vertex src = Line.totalEdges.get(k).source;
            Vertex dest = Line.totalEdges.get(k).target;

            if (isCycle(src, dest)) {
                k++;
                continue;
            }// END IF

            union(src, dest);
            MSTResultList.add(Line.totalEdges.get(k));
            k++;
            eCounter++;
        }// END WHILE LOOP

        // FINISH TIME
        long finish = System.currentTimeMillis();
        totalTime = finish - start;
    }
    @Override
    public void displayResultingMST() {

        int minimumCost = 0;

        System.out.println("\nKruskal's Algorithm: ");

        for (int i = 0; i < MSTResultList.size(); i++) {
            //minimumCost += MSTResultList.get(i).weight;
            if (Integer.parseInt(MSTResultList.get(i).parent.label) == -1) {
                MSTResultList.get(i).source.displayInfo();
                System.out.print(" - ");
                MSTResultList.get(i).target.displayInfo();
                System.out.print(" : ");
                MSTResultList.get(i).displayInfo();
                minimumCost += MSTResultList.get(i).weight;
            }
        }

        System.out.println("The cost of designed phone network: " + minimumCost);
        //System.out.println("Total runtime: " + totalTime + " msec");

        MSTResultList.clear();
    }

}