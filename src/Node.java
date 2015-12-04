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

        // Will be true for the starting index
        if(path == null){
            this.path = new ArrayList<>();
        } else {
//            this.path = (ArrayList<Integer>)path.clone();
            this.path = new ArrayList<>(path);
        }
        this.path.add(this.name);

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
     * Gets the path of a node
     * @return path of the node
     */
    public ArrayList<Integer> getPath() {
        return path;
    }

    /***
     * Returns the path plus the current node
     * @return string that contains the data
     */
    @Override
    public String toString(){
        String returnString = "";
        StringBuilder stringBuilder = new StringBuilder(returnString);

        // prints entire path
        for(int location : path){
            stringBuilder.append(location + "  ");
        }

        return stringBuilder.toString();
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
    public boolean isNodeVisited(){
        return this.visited;
    }
}
