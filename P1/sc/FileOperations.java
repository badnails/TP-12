import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileOperations {

    private static final String INPUT_FILE_NAME = "sc/players.txt";
    private static final String OUTPUT_FILE_NAME = "sc/out.txt";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;      
            bw.write(line);
            bw.write(System.lineSeparator());      
        }
        br.close();
        bw.close();
    }
}