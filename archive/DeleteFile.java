import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DeleteFile {

  public static void main(String[] args) {
    File yearsFile = new File("years.txt");
    boolean fileDeleted = false;
    try {
      fileDeleted = Files.deleteIfExists(yearsFile.toPath());
    }
    catch(IOException ex) {
      System.out.println("Error deleting file: " + ex.getMessage());
    }
    if(fileDeleted) {
      System.out.println(yearsFile.getName() + " deleted.");
    }
    else {
      System.out.println(yearsFile.getName() + " not deleted.");
    }
  }
}
