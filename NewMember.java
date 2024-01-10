import java.time.LocalDate;
import java.util.Random;

//this object represents an new member
public class NewMember extends Member {
  //basic data about the user
  private String userId;
  private LocalDate dateJoined;

  
  public NewMember(String firstName, String lastName) {
    super(firstName, lastName);
    
    //generate User ID
    Random random = new Random();
    int min = 100;
    int max = 999;
    int ranNum = random.nextInt(max - min) + min;
    this.userId = "" + firstName.charAt(0) + lastName.charAt(0) + ranNum;
    
    //set today as join date for new member
    this.dateJoined = LocalDate.now();
  }

  //getters and setters for basic data
  public String getUserId() {
    return userId;
  }
  public LocalDate getDateJoined() {
    return dateJoined;
  }

  //function to save new member to file
  public void saveMember(){
    FileManager.saveMember(this);
  }
}