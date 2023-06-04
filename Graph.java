package GraphFramework;

import PhoneNetworkApp.*;
import java.io.*;
import java.util.*;

public abstract class Graph {
    public int verticesNo;
    public int edgeNo;
    public boolean isDigraph = false;
    public ArrayList<Vertex> Vertices = new ArrayList<>();

    public Graph() {
    }

    public Graph(int verticesNo, int edgeNo, boolean isDigraph) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph = isDigraph;
        Vertices = new ArrayList<>(verticesNo);
    }

    public Graph readGraphFromFile(File filename) {

        try (Scanner in = new Scanner(filename)) {

            in.next();
            isDigraph = (in.nextInt() == 1);

            verticesNo = in.nextInt();
            edgeNo = in.nextInt();

            for (int i = 0, ver = 0; i < edgeNo; i++) {

                char SourceName = in.next().charAt(0);
                char DestinationName = in.next().charAt(0);
                int Weight = in.nextInt();
                
                Vertex source = searchVertex(SourceName);

                Vertex destination = searchVertex(DestinationName);

                if (source == null) {
                    source = new Office(ver++, SourceName);
                    Vertex.adjList.add(new LinkedList<>());
                    Vertices.add(source);
                }

                if (destination == null) {

                    destination = new Office(ver++, DestinationName);
                    Vertex.adjList.add(new LinkedList<>());
                    Vertices.add(destination);
                }

                createEdge(source, destination, Weight);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        return this;
    }
    public Graph makeGraph(int verticesNo, int edgeNo) {
        Random random = new Random();

        for (int i = 0; i < verticesNo; i++) {
            Vertex newVertex = createVertex(Integer.toString(i));
            Vertex.adjList.add(new LinkedList<>());
            Vertices.add(newVertex);
        }

        for (int i = 0; i < verticesNo - 1; i++) {
            int weight = random.nextInt(50) + 1;

            if ((i + 1) > verticesNo - 1) {
                addEdge(Vertices.get(i), Vertices.get(0), weight);
                edgeNo++;
                continue;
            }

            addEdge(Vertices.get(i), Vertices.get(i + 1), weight);
            edgeNo++;

            if (!isDigraph) {
                addEdge(Vertices.get(i + 1), Vertices.get(i), weight);
                edgeNo++;
            }
        }

        int remEdge = edgeNo - (verticesNo - 1);

        for (int i = 0; i < remEdge; i++) {
            int source = random.nextInt(verticesNo);
            int destination = random.nextInt(verticesNo);

            if (destination == source || checkEdge(source, destination)) {
                i--;
                continue;
            }

            int weight = random.nextInt(50) + 1;
            addEdge(Vertices.get(source), Vertices.get(destination), weight);
            edgeNo++;

            if (!isDigraph) {
                addEdge(Vertices.get(destination), Vertices.get(source), weight);
                edgeNo++;
            }
        }
        return this;
    }


    public boolean checkEdge(int source, int target) {
        List<Line> edges = Vertex.adjList.get(source);
        for (Line edge : edges) {
            if (edge.target.label.equals(String.valueOf(target))) {
                return true;
            }
        }
        return false;
    }


    public Vertex searchVertex(char name) {

        for (int i = 0; i < Vertices.size(); i++) {
            if (((Office) Vertices.get(i)).getOfficeNo() == name) {
                return Vertices.get(i);
            }
        }
        return null;
    }
    public abstract Edge createEdge(Vertex source, Vertex destVer, int weight);

    public Edge addEdge(Vertex source, Vertex destVer, int weight) {
        Edge edge =createEdge(source, destVer, weight);
        return edge;
    }
    
    
    public abstract Vertex createVertex(String label);
    
    
    public Vertex addVertex(Vertex vertex) {
        Vertices.add(vertex);
        return vertex;
    }




}