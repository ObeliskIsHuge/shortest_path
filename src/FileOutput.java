import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

/**
 *
 * File that handles the file output
 *
 * @author Brandon Potts
 * @version December 02, 2015
 */
public class FileOutput {

    // Object that handles the output writing of the file
    private PrintWriter writer;
//    // Keeps track of how many commands have been printed
//    private int commandCount;

    /***
     * Class constructor
     * @param fileName name of the file
     */
    public FileOutput(String fileName){
        // Creates new file
        File file = new File(fileName);
        try {
            this.writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        commandCount = 1;
    }


    /**
     * Prints the header for the results file
     */
    public void printHeader(){

        String header = "GIS data file contains the following records:";
        printLine(header);
        printNewLine();
    }


    /**
     * Prints a line to the output file
     *
     * @param line String that will be printed
     */
    public void printLine(String line){
        writer.println(line);
    }

    /***
     * Prints a line without the extra new line
     * @param line that will be printed
     */
    public void print(String line){ writer.print(line);}

    /***
     * Prints a new line
     */
    public void printNewLine(){
        writer.print("\n");
    }


    /***
     * Closes the writer file
     */
    public void closeFile(){
        writer.close();
    }
}
