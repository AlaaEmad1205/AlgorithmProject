package PhoneNetworkApp;

import GraphFramework.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhoneNWDesignApp {

    private BluePrintsGraph blueprintGraph;

    static long totalTime;
    
    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        int verticesNo = 0; //number of vertices
        int edgesNo = 0;    //number of edges
        int userChoice = 0; //choice of algorithms
        int caseNumber =0;  //choice of casses


        File file = new File("graph.txt");

        PhoneNWDesignApp PhLNetwork = new PhoneNWDesignApp();
        PhLNetwork.blueprintGraph = new BluePrintsGraph(); // Instantiate the BluePrintsGraph
        PhLNetwork.blueprintGraph.readGraphFromFile(file);

        // Requirment 1
        System.out.println("-----------------Test and compare diffrent Minimum Spanning Tree Algorithms---------------");
        System.out.println("1-Kruskal's Algorithm & Prim's Algorithm (based on minheap).");
        System.out.println("Requirement 1:");

        MSTAlgorithm Phone = new KruskalAlg(PhLNetwork.blueprintGraph);
        ((KruskalAlg) Phone).Kruskal();
        Phone.displayResultingMST();

        Phone = new MHPrimAlg(PhLNetwork.blueprintGraph);
        ((MHPrimAlg) Phone).PrimMH();
        Phone.displayResultingMST();

        // Requirment 2
        System.out.println("---------------------------------------");
        System.out.println("Requirement 2:");

        //printthe menue of casses
        System.out.println("\tCases (where n represnt # of vertices and m represent # of edges):");
        System.out.println("1-  n =  1,000  , m =   10,000");
        System.out.println("2-  n =  1,000  , m =   15,000");
        System.out.println("3-  n =  1,000  , m =   25,000");
        System.out.println("4-  n =  5,000  , m =   15,000");
        System.out.println("5-  n =  5,000  , m =   25,000");
        System.out.println("6-  n = 10,000  , m =   15,000");
        System.out.println("7-  n = 10,000  , m =   25,000");


        //let user choose the case
        //ensure that user enter a number between 0 and 10
        while(caseNumber<1 || caseNumber>7){
            System.out.print(">Enter your choice: ");//aske user to enter a number
            caseNumber=input.nextInt();//read user input
        }//end of while loop

        switch(caseNumber){
            case 1:
                verticesNo = 1000;
                edgesNo = 10000;
                break;
            case 2:
                verticesNo = 1000;
                edgesNo = 15000;
                break;
            case 3:
                verticesNo  = 1000;
                edgesNo = 25000;
                break;
            case 4:
                verticesNo  = 5000;
                edgesNo = 15000;
                break;
            case 5:
                verticesNo  = 5000;
                edgesNo = 25000;
                break;
            case 6:
                verticesNo  = 10000;
                edgesNo = 15000;
                break;
            case 7:
                verticesNo  = 10000;
                edgesNo = 25000;
                break;
        }//end



        // START TIME
        
        PhoneNWDesignApp app2 = new PhoneNWDesignApp();
        PhLNetwork.blueprintGraph = new BluePrintsGraph(); // Instantiate the BluePrintsGraph
        PhLNetwork.blueprintGraph.makeGraph(verticesNo, edgesNo);

        MSTAlgorithm Phone2 = new KruskalAlg(PhLNetwork.blueprintGraph);
        
        long start = System.currentTimeMillis();
        ((KruskalAlg) Phone2).Kruskal();
        //Phone2.displayResultingMST();
        long finish = System.currentTimeMillis();
        totalTime = finish - start;
         
         System.out.println("\nKruskal's Algorithm: ");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Total runtime: " + totalTime + " msec");
        System.out.println("");
//
       Phone2 = new MHPrimAlg(PhLNetwork.blueprintGraph);
       long start2= System.currentTimeMillis();
       
        ((MHPrimAlg) Phone2).PrimMH();
        //Phone2.displayResultingMST();
        long finish2 = System.currentTimeMillis();
         totalTime = finish2 - start2;
         System.out.println("\nPrim's Algorithm: ");
         System.out.println("---------------------------------------------------------------");
          System.out.println("Total runtime: " + totalTime + " msec");

        
    }
}