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
    private ArrayList<LinkedList<Tuple>> list;

    /***
     * Creates a new AdjacencyList object
     */
    public AdjacencyList(){
        list = new ArrayList<>();
    }


    /****
     * Adds a list of nodes to the AdjacencyList
     * @param index index that the list will be added
     * @param nodeList list of nodes that will be appended to list
     */
    public void addNodeList(int index , LinkedList<Tuple> nodeList){
        list.add(index , nodeList);
    }

    /***
     * Returns the contents of the
     * @return
     */
    @Override
    public String toString(){

        String returnString = "";
        StringBuilder stringBuilder = new StringBuilder(returnString);
        int index = 0;

        // iterates over the list
        for(LinkedList<Tuple> nodeList : list){
            stringBuilder.append("\t\t" + index);
            // Will be true only when the node has values
            if(nodeList != null){
                // Prints all the tuples in the current list
                for(Tuple tempTuple : nodeList){
                    stringBuilder.append("\t\t" + tempTuple.toString());
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
