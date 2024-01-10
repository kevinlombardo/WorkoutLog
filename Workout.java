import java.time.LocalDate;

//this object represents an workout entry
public class Workout {
  //basic data about the workout
  private String userId;
  private String name;
  private int weight;
  private int reps;
  private LocalDate dateEntered = LocalDate.now();

  //getters and setters for basic data
  public String getUserId(){
    return userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getWeight() {
    return weight;
  }
  
  public void setWeight(int weight) {
    this.weight = weight;
  }
  
  public int getReps() {
    return reps;
  }
  
  public void setReps(int reps) {
    this.reps = reps;
  }

  public LocalDate getDateEntered() {
    return dateEntered;
  }

  //function to save workout to file  
  public boolean saveWorkout() {
    //ensure data was input correctly
    if (userId == null || name == null || weight == 0 || reps == 0) {
      System.out.println("Invalid data. Workout not saved.");
      return false;
    } else {
      try {
        //call FileManager for the actual persistence to file
        FileManager.saveWorkout(this);
      } catch (Exception e) {
        System.out.println("Error saving workout data: " + e.getMessage());
        return false;
      }
    }
    return true;
  }
}