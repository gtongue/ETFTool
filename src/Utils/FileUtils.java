package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
}
