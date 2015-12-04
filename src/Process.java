import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
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
//        int visitedNodes = 1;
        Node currentNode;

        // Populates the fringeList of all adj nodes
        for(Edge currentEdge : adjNodes){

            currentNode = new Node(currentEdge.getName() , currentEdge.getWeight() , null);
            fringeList[currentNode.getName()] = currentNode;
        }

//        int closestNode = 1000000;
        currentNode = null;
//        Node newNode;
//        int baseWeight;
//        ArrayList<Integer> previousPath;
        // Processes all nodes that are reachable
        while(areNodesLeft(fringeList)){

            // Goes through the entire list looking for the closest node
            for(Node tempNode : fringeList){

                // Will be true the current node is null and temp isn't
                if(currentNode == null && tempNode != null && !tempNode.isNodeVisited()){
                    currentNode = tempNode;
                } else if(tempNode != null && !tempNode.isNodeVisited()){

                    if(currentNode.getTotalWeight() == tempNode.getTotalWeight()){
                        if(tempNode.getName() < currentNode.getName()){
                            currentNode = tempNode;
                        }
                    } else if(tempNode.getTotalWeight() < currentNode.getTotalWeight()){
                        currentNode = tempNode;
                    }
                }
            }

            //Gets all adjacentNodes
            adjNodes = adjList.getNode(currentNode.getName());
//            baseWeight = currentNode.getTotalWeight();
//            previousPath = currentNode.getPath();

            // Adds the adjEdges to the fringeList and updates nodes
            for(Edge adjEdge : adjNodes){

                Node newNode = new Node(adjEdge.getName(), currentNode.getTotalWeight() + adjEdge.getWeight(), currentNode.getPath());

                // Checks to see if this new node exists and hasn't been visited before
                if(fringeList[newNode.getName()] != null && !fringeList[newNode.getName()].isNodeVisited()){

                    Node oldNode = fringeList[newNode.getName()];
                    // Checks to see if the new node has a lower weight than the old node
                    if(newNode.getTotalWeight() < oldNode.getTotalWeight()){
                        fringeList[newNode.getName()] = newNode;
                    }

                } else if(fringeList[newNode.getName()] == null){
                    fringeList[newNode.getName()] = newNode;
                }
            }

            // mark node as visited
            currentNode.visitNode();
            fringeList[currentNode.getName()] = currentNode;
            currentNode = null;
        }

        printFringeList(fringeList);

    }

    /***
     *
     * Determines if nodes can be visited
     * @param fringeList list of nodes
     * @return true if there are nodes left to process
     *         false otherwise
     */
    private boolean areNodesLeft(Node[] fringeList){

        // Iterates over the entire fringe list
        for(Node tempNode : fringeList){
            //Will be true a node can still be reached
            if(tempNode != null && !tempNode.isNodeVisited()){
                return  true;
            }
        }
        return false;
    }

    /***
     * Prints the fringeList
     * @param fringeList list that will be printed
     */
    private void printFringeList(Node[] fringeList){

    }
}