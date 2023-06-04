package PhoneNetworkApp;

import GraphFramework.*;

public class Line extends Edge {
    
    private int lLength;

    public Line(Vertex source, Vertex destination, int weight) {
        super(source, destination, weight);
        this.lLength = 5*weight;

    }

    @Override
    public void displayInfo(){
        System.out.println(" line length: " + lLength);

   }

}
