import java.nio.file.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ArrayList;

public class FileManager {
  //file to contain list of members with their UserID
  public static final String MEMBERS_FILE = "members.csv";

  //function to check if member exists in the file
  public static MemberInterface checkMemberExists(String userId) {
    //open file
    File memberFile = new File(MEMBERS_FILE);

    try{
      List<String> lines = Files.readAllLines(memberFile.toPath());

      //look for UserID
      for(String line : lines) {
        String[] parts = line.split(",");
        if(parts[0].equals(userId)) {
          //found UserID, return Member object
          return new Member(parts[0], parts[1], parts[2], LocalDate.parse(parts[3]));
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }
    return null;
  }

  //function to take NewMember or Member object and persist to file
  public static boolean saveMember (MemberInterface member) {
    File memberFile = new File(MEMBERS_FILE);
    List<String> lines;
    
    try {
      //read all members
      lines = Files.readAllLines(memberFile.toPath());
    
      //delete if existing
      Iterator<String> iterator = lines.iterator();
      while (iterator.hasNext()) {
          String line = iterator.next();
          if (line.length() >= 5 && line.substring(0, 5).equals(member.getUserId())) {
              iterator.remove(); // Removes the line that matches the first 5 characters
          }
      }
    } catch (IOException e) {
      //create new empty list for future use
      lines = new ArrayList<>();
    }
    try {
      //add new member
      String line = member.getUserId() + "," + member.getFirstName() + "," + member.getLastName() + "," + member.getDateJoined();
      lines.add(line);
      //persist to file
      Files.write(memberFile.toPath(), lines, StandardOpenOption.CREATE);
      System.out.println("Member saved successfully.");
      return true;
    } catch (Exception e) {
      System.out.println("Error saving member data: " + e.getMessage());
    }
    return false;
  }

  //function to write workout data to file
  public static void saveWorkout(Workout workout) {
    //create / append file unique to user
    File workoutsFile = new File(workout.getUserId() + ".csv");
    List<String> lines = new ArrayList<>();

    //concat all data from workout object
    String line = "" + workout.getDateEntered() + "," + workout.getName() + "," + workout.getWeight() + "," + workout.getReps();
    
    try{
      lines.add(line);
      //persist to file unique to user
      Files.write(workoutsFile.toPath(), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
      System.out.println(line);
      System.out.println("Workout saved successfully.");
    } catch (IOException e) {
      System.out.println("Error saving workout data: " + e.getMessage());
    }
  }

  //function to read workout data from file
  public static void getWorkout(MemberInterface member, LocalDate dateEntered) {
    //member specfic workout file
    File workoutsFile = new File(member.getUserId() + ".csv");
    List<String> lines;

    try {
      //read all workout data
      lines = Files.readAllLines(workoutsFile.toPath());
      //loop through each line and compare to dateEntered, only output matches to that date
      for(String line : lines) {
        String[] parts = line.split(",");
        if (dateEntered.isEqual(LocalDate.parse(parts[0]))) {
          System.out.println(parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3]);
        }
      }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
  }

  //function to get all workouts for a member
  public static void getAllWorkouts (MemberInterface member) {
    //user specific workout file
    File workoutsFile = new File(member.getUserId() + ".csv");
    List<String> lines;
    
    try {
      //read all workout data
      lines = Files.readAllLines(workoutsFile.toPath());
      System.out.println("Date,Exercise,Weight,Reps");
      //loop through each line and print out
      for(String line : lines) {
        String[] parts = line.split(",");
        System.out.println(parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3]);
      }
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }
  }
    
} 
      
    
                                