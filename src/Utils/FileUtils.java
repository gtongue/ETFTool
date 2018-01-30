package Utils;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {
    public static PrintWriter MakeNewFile(String filename) {
        PrintWriter pw = null;
        try {
            File file = new File(filename);
            file.getParentFile().mkdirs();
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            System.out.println("problem opening file " + filename);
        }
        return pw;
    }

    public static void Delete(String filename){
        File file = new File(filename);
        if(file.delete()){
            System.out.println("DELETED: " + filename);
        }else{
            System.out.println("FAILED DELETING: " + filename);
        }
    }

    public static ArrayList<String> readFileToStringList(String filename) {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Problem reading " + filename);
            e.printStackTrace();
        }
        return lines;
    }
}
