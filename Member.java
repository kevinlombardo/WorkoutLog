import java.time.LocalDate;

//this object represents an existing member
public class Member implements MemberInterface {
  //basic data about the user
  private String userId;
  private String firstName;
  private String lastName;
  private LocalDate dateJoined;

  //constructor for existing member
  public Member(String userId, String firstName, String lastName, LocalDate dateJoined){
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;    
    this.dateJoined = dateJoined;
  }

  //constructor when creating new member, used by NewMember object
  public Member(String firstName, String lastName){
    this.firstName = firstName;
    this.lastName = lastName;
  }

  //getters and setters for basic data
  public String getFirstName(){
    return firstName;
  }

  public void setFirstName(String firstName){
    this.firstName = firstName;
  }
  
  public String getLastName(){
    return lastName;
  }
  
  public void setLastName(String lastName){
    this.lastName = lastName;
  }

  public String getUserId(){
    return userId;
  }

  public void setUserId(String userId){
    this.userId = userId;
  }
  
  public LocalDate getDateJoined(){
    return dateJoined;
  }

  public void setDateJoined(LocalDate dateJoined){
    this.dateJoined = dateJoined;
  }

  //function to save member to file, this can be used to save new members or existing members
  public void saveMember(){
    FileManager.saveMember(this);
  }
}