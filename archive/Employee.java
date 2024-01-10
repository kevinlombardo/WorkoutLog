import java.time.LocalDate;

public class Employee {
  private String firstName;
  private String lastName;
  private int emplId;
  private String jobTitle;
  private double salary;
  private LocalDate hireDate;

  public Employee(String firstName, String lastName, int emplId, String jobTitle, double salary){
    this.firstName = firstName;
    this.lastName = lastName;
    this.emplId = emplId;
    this.jobTitle = jobTitle;
    this.salary = salary;
    this.hireDate = LocalDate.now();
  }

  //returns first name
  public String getFirstName(){
    return firstName;
  }

  //sets first name
  public void setFirstName(String firstName){
    this.firstName = firstName;
  }

  //returns last name
  public String getLastName(){
    return lastName;
  }

  //sets last name
  public void setLastName(String lastName){
    this.lastName = lastName;
  }
  //gets job title
  public String getJobTitle(){
    return jobTitle;
  }
  
  //sets job title
  public void setJobTitle(String jobTitle){
    this.jobTitle = jobTitle;
  }

  //get employee id
  public int getEmplId(){
    return emplId;
  }

  //get salary
  public double getSalary(){
    return salary;
  }

  //set salary
  public void setSalary(double salary){
    if(salary < 0){
      this.salary = salary;
    }
  }
}

  