import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteToFile {

    public WriteToFile(ArrayList<MenuItem> order){
        try {
            FileWriter fw = new FileWriter("salesData.txt", true);
            PrintWriter salesWriter = new PrintWriter(fw);

            for(MenuItem item : order){
                salesWriter.println(item.getItemName());
            }
            System.out.println("Successfully wrote to the file");
            salesWriter.close();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
