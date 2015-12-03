/**
 * @author Brandon Potts
 * @version November 30, 2015
 */
public class Tuple {

    // Name of the Node
    private String name;
    // Weight of the edge
    private String weight;

    /****
     * Creates a new Tuple Object
     * @param name name of the Node
     * @param weight weight of the edge
     */
    public Tuple(String name, String weight){
        this.name = name;
        this.weight = weight;
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
