import java.time.LocalDate;

//this interface was created so that either Member or NewMember could be passed to the saveMember() function

public interface MemberInterface{
  public String getFirstName();
  public void setFirstName(String firstName);
  public String getLastName();
  public void setLastName(String lastName);
  public String getUserId();
  public LocalDate getDateJoined();
  public void saveMember();
}