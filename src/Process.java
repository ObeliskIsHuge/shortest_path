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
            this.inputFile = new RandomAccessFile("3.Graph.txt", "r");
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

        outputFile.printLine(adjList.toString());
        outputFile.printNewLine();
        outputFile.printLine("Start vertex is: " + startingIndex);
        outputFile.printNewLine();
        outputFile.printLine("     Total");
        outputFile.printLine("Dest | Weight | Path");
        outputFile.printLine("----------------------------------------------------------------------");
        dijkstrasAlgorithm(startingIndex , numOfNodes);
        outputFile.closeFile();

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

        currentNode = null;
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

        printFringeList(fringeList, startIndex);

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
     * @param startingIndex index that the list started
     */
    private void printFringeList(Node[] fringeList, int startingIndex){
        String finalString = "";
        StringBuilder stringBuilder = new StringBuilder(finalString);
        int count = 0;

        // processes the entire fringe list
        for(Node fringeNode : fringeList){
            // handles all the non-null nodes
            if(fringeNode != null){
                stringBuilder.append("  " + fringeNode.getName());
                // Will be true when the current node was the starting node
                if(fringeNode.getName() == startingIndex){
                    stringBuilder.append("      0\n");
                } else {
                    stringBuilder.append("     " + fringeNode.getTotalWeight());
                    stringBuilder.append("     " + fringeNode.printPath() + "\n");
                }
                // executes when the node wasn't reachable
            } else {
                stringBuilder.append("  " + count);
                stringBuilder.append("     inf\n");
            }
            count++;
        }

        outputFile.print(stringBuilder.toString());
    }
}