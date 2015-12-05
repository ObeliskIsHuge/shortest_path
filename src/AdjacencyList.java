import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class represents the AdjacencyList
 *
 * @author Brandon Potts
 * @version November 30, 2015
 */
public class AdjacencyList {

    // List that will hold the records
    private ArrayList<LinkedList<Edge>> list;

    /***
     * Creates a new AdjacencyList object
     */
    public AdjacencyList(){
        list = new ArrayList<>();
    }


    /***
     * Retrieves a node from a location
     * @param index index of the node
     * @return node at index
     */
    public LinkedList<Edge> getNode(int index){
        return list.get(index);
    }


    /****
     * Adds a list of nodes to the AdjacencyList
     * @param index index that the list will be added
     * @param nodeList list of nodes that will be appended to list
     */
    public void addNodeList(int index , LinkedList<Edge> nodeList){
        list.add(index , nodeList);
    }

    /***
     * Prints the contents of the Adjacency list
     * @return string that contains the data
     */
    @Override
    public String toString(){

        String returnString = "";
        StringBuilder stringBuilder = new StringBuilder(returnString);
        int index = 0;

        // iterates over the list
        for(LinkedList<Edge> nodeList : list){
            stringBuilder.append("\t\t" + index);
            // Will be true only when the node has values
            if(nodeList != null){
                // Prints all the tuples in the current list
                for(Edge tempEdge : nodeList){
                    stringBuilder.append("\t\t" + tempEdge.toString());
                }
            }
            stringBuilder.append("\n");
            index++;
        }
        return stringBuilder.toString();
    }
}
