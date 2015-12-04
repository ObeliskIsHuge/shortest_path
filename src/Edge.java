/**
 * @author Brandon Potts
 * @version November 30, 2015
 */
public class Edge {

    // Name of the Node
    private int name;
    // Weight of the edge
    private int weight;

    /****
     * Creates a new Edge Object
     * @param name name of the Node
     * @param weight weight of the edge
     */
    public Edge(int name, int weight){
        this.name = name;
        this.weight = weight;
    }

    /***
     * Gets the name of the edge
     * @return name of the edge
     */
    public int getName() {
        return name;
    }

    /**
     * Gets the weight of an edge
     * @return weight of an edge
     */
    public int getWeight() {
        return weight;
    }

    /****
     * Prints the tuple in a properly formatted string
     * @return String that contains the formatted string
     */
    @Override
    public String toString(){
        return name + ": " + weight;
    }
}
