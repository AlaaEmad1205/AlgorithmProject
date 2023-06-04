package GraphFramework;
import PhoneNetworkApp.Line;
import java.util.ArrayList;

public abstract class MSTAlgorithm {
    
    static ArrayList<Line> MSTResultList = new ArrayList<>();
    static long totalTime;
    public Graph graph;

    public MSTAlgorithm() {

    }

    public MSTAlgorithm(Graph graph){

        this.graph = graph;
    }
    
    public abstract void displayResultingMST();
}
