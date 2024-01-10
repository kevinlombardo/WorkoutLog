import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//this is the main program
public class WorkoutLog {
  //entry point
  public static void main(String[] args) {
    //initialize input scanner
    Scanner scanner = new Scanner(System.in);
    //this determines if the main loop should be running
    boolean running = true;
    //this is the date for entries, defaulting to today
    LocalDate dateEntered = LocalDate.now(); 
    //these 3 vars are used for the date entry and checking loop
    String dateEnteredInput;
    boolean enterDate = true;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    //the member object is used to store the user's data
    MemberInterface member = null;
    
    while(running) {
      //check if we've already created the member object, if so go to menu
      if (member == null) {
        System.out.println("Please enter User ID: ");
        String userId = scanner.nextLine();
        //check if member exists, return member object if so
        member = FileManager.checkMemberExists(userId);
        //if member comes back null, user does not exist
        if (member == null) {
          System.out.println("User ID does not exist. Add user? (y/n)");
          String addUser = scanner.nextLine();
          if(addUser.equals("y")) {
            //gather all new member data, save to file, and use member object for the rest of the program
            System.out.println("Please enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Please enter last name: ");
            String lastName = scanner.nextLine();
            NewMember newMember = new NewMember(firstName, lastName);
            member = newMember;      
            member.saveMember();   
            System.out.println("User added successfully. UserID is " + member.getUserId());
          } else {
            System.out.println("Exiting...");
            running = false;
            break;
          }
        }
      }

      //main menu
      System.out.println("Select from the following options:");
      System.out.println("1. Add a workout");
      System.out.println("2. View a workout");
      System.out.println("3. View all workouts");
      System.out.println("4. Exit");

      int choice = scanner.nextInt();
      scanner.nextLine();

      //logic to determine what to do based on user input
      switch(choice) {
        case 1:
          //add workout data for the specific member
          addWorkout(scanner, member);
          break;
        case 2:
          //get a workout report for a specific date for the member
          while(enterDate){
            System.out.println("Enter workout date (MM/DD/YYYY):");
            dateEnteredInput = scanner.nextLine();
            try {
              dateEntered = LocalDate.parse(dateEnteredInput, formatter);
              enterDate = false;
            } catch (Exception e) {
              System.out.println("Invalid date entered. Please try again.");
            }
          }
          FileManager.getWorkout(member, dateEntered);
          break;
        case 3:
          //get all workout data for a specific member
          FileManager.getAllWorkouts(member);
          break;
        case 4:
          //exit the program
          System.out.println("Exiting...");
          running = false;
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }

    scanner.close();    
  }

  //this function assists in gathering the workout data
  private static void addWorkout(Scanner scanner, MemberInterface member) {
    //variables local to this function
    int weight = 0;
    int reps = 0;
    String invalid;
    char run = 'y';
    Workout workout = new Workout();
    
    while(run == 'y') {
      //prompt for workout data
      workout.setUserId(member.getUserId());
      System.out.println("Enter the exercise name:");
      workout.setName(scanner.nextLine());

      //loop to ensure valid input for weight
      do {
        System.out.println("Enter the amount of weight: ");
        while (!scanner.hasNextInt()) {
          invalid = scanner.next();
          System.out.println("Invalid input. Please enter a number for weight: ");
        }
        weight = scanner.nextInt();
      } while (weight <= 0);
      workout.setWeight(weight);
      //loop to ensure valid input for reps
      do {
        System.out.println("Enter the number of reps: ");
        while (!scanner.hasNextInt()) {
          invalid = scanner.next();
          System.out.println("Invalid input. Please enter a number of reps: ");
        }
        reps = scanner.nextInt();
      } while (weight <= 0);
      workout.setReps(reps);

      //save the workout, which persists to file
      workout.saveWorkout();
      System.out.println("Add another workout? (y/n)");
      run = scanner.next().charAt(0);
      scanner.nextLine();
    }
  }  


}