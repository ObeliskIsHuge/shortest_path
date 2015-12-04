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
        String currentLine = inputFile.readLine();
        String[] pieces = currentLine.split("\\s+");
        int numOfNodes = Integer.parseInt(pieces[3]);


        currentLine = inputFile.readLine();
        pieces = currentLine.split("\\s+");
        int startingIndex = Integer.parseInt(pieces[2]);
        outputFile.printLine("Node  | Out-neighbors");
        outputFile.printLine("----------------------------------------------------------------------");

        inputFile.readLine();

        currentLine = inputFile.readLine();
        int nodeIndex;
        int listIndex = 0;
        Edge nodeEdge;
        LinkedList<Edge> edgeList;

        // Runs through the rest of the file and fills the adjacency adjList
        while(currentLine != null){

            nodeIndex = -1;
            edgeList = new LinkedList<>();
            pieces = currentLine.split("\\s+");

            // Iterates over all the weights
            for(String weight : pieces){

                if(!weight.equals("0") && !weight.equals("")){
                    nodeEdge = new Edge(nodeIndex,  Integer.parseInt(weight));
                    edgeList.add(nodeEdge);
                }
                nodeIndex++;
            }

            adjList.addNodeList(listIndex, edgeList);
            currentLine = inputFile.readLine();
            listIndex++;
        }

        dijkstrasAlgorithm(startingIndex , numOfNodes);

    }


    /***
     * Performs Dijkstras algorithm
     * @param startIndex start node
     * @param numOfNodes number of nodes that was given
     */
    private void dijkstrasAlgorithm(int startIndex, int numOfNodes){

        Node[] fringeList = new Node[numOfNodes];
        LinkedList<Edge> adjNodes = adjList.getNode(startIndex);
        Node currentNode;

        // Populates the fringeList of all adj nodes
        for(Edge currentEdge : adjNodes){

            currentNode = new Node(currentEdge.getName() , currentEdge.getWeight() , null);
            fringeList[currentNode.getName()] = currentNode;
        }

//        System.out.println("Hello");
    }
}