import java.util.ArrayList;

/**
 *
 * Class represents a graph node
 *
 * @author Brandon Potts
 * @version December 03, 2015
 */
public class Node {

    // Total weight of the edges to get to this node
    private int totalWeight;
    // Name of this node
    private int name;
    // List of all the nodes that it took to get here
    private ArrayList<Integer> path;
    // Boolean that holds if the node was visited before or not
    private boolean visited;


    /****
     *
     * Instantiates the class
     * @param name name of the current node
     * @param totalWeight total weight
     * @param path list of nodes that it took to get to this node
     */
    public Node(int name, int totalWeight, ArrayList<Integer> path) {
        this.totalWeight = totalWeight;
        this.name = name;
        this.path = path;
        this.visited = false;
    }


    /***
     * Gets the weight of the path that lead to this node
     * @return total weight
     */
    public int getTotalWeight() {
        return totalWeight;
    }

    /***
     * Gets the name of the node
     * @return name of the node
     */
    public int getName() {
        return name;
    }

    /***
     * Returns the path plus the current node
     * @return string that contains the data
     */
    @Override
    public String toString(){
        //TODO
        return null;
    }


    /***
     * Adds a new location to the path
     * @param newLocation new location
     */
    public void addToPath(int newLocation){
        this.path.add(newLocation);
    }

    /***
     * Visit the node
     */
    public void visitNode(){
        this.visited = true;
    }

    /***
     * Determines if a node was visited or not
     * @return true if the node was visited
     *         false otherwise
     */
    public boolean nodeVisited(){
        return this.visited;
    }
}
