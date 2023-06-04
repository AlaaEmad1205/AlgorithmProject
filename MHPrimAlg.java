package GraphFramework;

import PhoneNetworkApp.Line;
import PhoneNetworkApp.Office;
import java.util.ArrayList;
import java.util.LinkedList;


public class MHPrimAlg extends MSTAlgorithm {


//capacity of the heap maxSize
    int capacity;
//current  size of the heap
    int currentSize;
// minHeap Array
    ArrayList<Line> minHeapArr;
// indices -position- of heap element in the Heap
    int[] indexes;

    //---------------------------------------------------------------------------
//                     constructors
    public MHPrimAlg() {
    }

    public MHPrimAlg(Graph graph) {
        super(graph);

    }
//----------------------------------------------------------------------------
public void PrimMH() {

    MSTResultList = new ArrayList<>();

    for (int i = 0; i < graph.Vertices.size(); i++) {
        Edge edge = new Line(new Vertex(), new Vertex(), 0);
        edge.source.label = String.valueOf(i);
        edge.parent = new Office();
        edge.parent.label =  String.valueOf(-1);
        edge.weight = Integer.MAX_VALUE;
        MSTResultList.add(Line.totalEdges.get(i));
        //MSTResultList.get(0).weight = 0;
    }

    capacity = graph.Vertices.size();

    minHeapArr = new ArrayList<>();
    indexes = new int[capacity];
    minHeapArr.add(new Line(new Vertex(), new Vertex(), 0));
    minHeapArr.get(0).weight = Integer.MIN_VALUE;
    minHeapArr.get(0).source.label = "-1";
    currentSize = 0;



    for (int i = 0; i < graph.Vertices.size(); i++) {
        insert(MSTResultList.get(i));
    }

    while (!isEmpty()) {
        Line extractedMinNode = extractMin();
        //cost += extractedMinNode.weight; // Increment with extractedMinNode.weight
        int extractedVertex = Integer.parseInt(extractedMinNode.source.label);
        MSTResultList.get(extractedVertex).source.isVisited = true;
        LinkedList<Line> list = Vertex.adjList.get(extractedVertex);

        for (int i = 0; i < list.size(); i++) {
            Line edge = list.get(i);
            int destination = Integer.parseInt(edge.target.label);
            if (!MSTResultList.get(destination).source.isVisited) {
                if (MSTResultList.get(destination).weight > edge.weight) {
                    decreaseKey(MSTResultList.get(destination), edge.weight);
                    MSTResultList.get(destination).parent = MSTResultList.get(extractedVertex).source;
                }
            }
        }
    }
}

    public void insert(Line edge) {
        minHeapArr.add(edge);
        currentSize++;
        int index = currentSize;
        indexes[Integer.parseInt(edge.source.label)] = currentSize;
        bubbleUp(index);
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public Line extractMin() {
        Line min = minHeapArr.get(1);
        Line lastNode = minHeapArr.get(currentSize);
        indexes[Integer.parseInt(lastNode.source.label)] = 1;
        minHeapArr.set(1, lastNode);
        minHeapArr.remove(currentSize);
        currentSize--;
        bubbleDown(1);
        return min;
    }

    public void decreaseKey(Line edge, int newWeight) {
        int index = indexes[Integer.parseInt(edge.source.label)];
        edge.weight = newWeight;
        bubbleUp(index);
    }

    public void bubbleUp(int pos) {
        int parent = pos / 2;
        int current = pos;
        while (current > 0 && minHeapArr.get(parent).weight > minHeapArr.get(current).weight) {
            Line currentNode = minHeapArr.get(current);
            Line parentNode = minHeapArr.get(parent);

            indexes[Integer.parseInt(currentNode.source.label)] = parent;
            indexes[Integer.parseInt(parentNode.source.label)] = current;
            swap(current, parent);
            current = parent;
            parent = parent / 2;
        }
    }

    public void bubbleDown(int pos) {
        int smallestChild;
        int current = pos;
        while (true) {
            int leftChild = 2 * current;
            int rightChild = leftChild + 1;
            if (leftChild > currentSize)
                break;
            if (rightChild > currentSize)
                smallestChild = leftChild;
            else
                smallestChild =
                        minHeapArr.get(leftChild).weight < minHeapArr.get(rightChild).weight ? leftChild : rightChild;

            if (minHeapArr.get(current).weight <= minHeapArr.get(smallestChild).weight)
                break;

            Line currentNode = minHeapArr.get(current);
            Line smallestChildNode = minHeapArr.get(smallestChild);

            indexes[Integer.parseInt(currentNode.source.label)] = smallestChild;
            indexes[Integer.parseInt(smallestChildNode.source.label)] = current;
            swap(current, smallestChild);
            current = smallestChild;
        }
    }
    public void swap(int i, int j) {
        Line temp = minHeapArr.get(i);
        minHeapArr.set(i, minHeapArr.get(j));
        minHeapArr.set(j, temp);
    }

    @Override
    public void displayResultingMST() {
        int minimumCost = 0;

        System.out.println("\nPrim's Algorithm: ");

        for (int i = 0; i < MSTResultList.size(); i++) {

            if (MSTResultList.get(i).parent.label.equals("-1")) {
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
// 0 1  3 7 12 18
        MSTResultList.clear();
    }


}
