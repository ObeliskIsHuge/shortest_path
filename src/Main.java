import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Process process = new Process();
        try {
            process.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
