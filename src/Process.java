import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

/**
 * Class represents the "Glue" between the input and
 * the output
 * @author Brandon Potts
 * @version November 30, 2015
 */
public class Process {

    // Represents the graph
    private AdjacencyList adjList;
    // Holds the file that will be read from
    private RandomAccessFile inputFile;
    // File that will be printed to
    private FileOutput outputFile;

    /***
     * Creates a new process object
     */
    public Process(){
        this.adjList = new AdjacencyList();
        try {
            this.inputFile = new RandomAccessFile("1.Graph.txt", "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.outputFile = new FileOutput("SSADResults.txt");
    }

    /***
     * Runs the program
     */
    public void execute() throws IOException {


        // Get rind of the unimportant line
        inputFile.readLine();
        String currentLine = inputFile.readLine();
        String[] pieces = currentLine.split("\\s+");
        int startingIndex = Integer.parseInt(pieces[2]);
        outputFile.printLine("Node  | Out-neighbors");
        outputFile.printLine("----------------------------------------------------------------------");

        inputFile.readLine();

        currentLine = inputFile.readLine();
        int nodeIndex;
        int listIndex = 0;
        Tuple nodeTuple;
        LinkedList<Tuple> tupleList;

        // Runs through the rest of the file and fills the adjacency adjList
        while(currentLine != null){

            nodeIndex = -1;
            tupleList = new LinkedList<>();
            pieces = currentLine.split("\\s+");

            // Iterates over all the weights
            for(String weight : pieces){

                if(!weight.equals("0") && !weight.equals("")){
                    nodeTuple = new Tuple(nodeIndex,  Integer.parseInt(weight));
                    tupleList.add(nodeTuple);
                }
                nodeIndex++;
            }

            adjList.addNodeList(listIndex, tupleList);
            currentLine = inputFile.readLine();
            listIndex++;
        }

        dijkstrasAlgorithm(startingIndex);

    }



    private void dijkstrasAlgorithm(int startIndex){
        //TODO
    }
}