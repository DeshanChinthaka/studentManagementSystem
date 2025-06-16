import java.util.*;    //import all classes from the java.util package. including the Scanner, Arrays
import java.io.*;    // import all classes from the java.io package.including classes for input and output operations

public class Main {
    private static final int TOTAL_SEATS = 100;    // maximum seats for registration
    private static final String STUDENT_FILE = "students.txt";     // create a file to store students details
    private static final Student[] students = new Student[TOTAL_SEATS];  // create an array to stock student details
    private static int countOfStudent = 0;   // set a counter for registered students

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);    // create an object by using Scanner to get inputs

        while (true) {       // create an iteration using while loop to display main menu
            printMainMenu();      // prompt the main menu to the user
            if (input.hasNextInt()) {
                int choice = input.nextInt();    // create reader for user choice input
                input.nextLine(); // create newline

                switch (choice) {    //  create a switch, case block for run main loop
                    case 1:
                        seatsCheck();      // to check if there empty seats for registration
                        break;
                    case 2:
                        studentRegistration(input);       // register new students in to the file
                        break;
                    case 3:
                        deleteStudent(input);          //  delete a detail related to a student
                        break;
                    case 4:
                        findStudent(input);    // to display student details
                        break;
                    case 5:
                        storeStudentDetails();      //store the details related to
                        break;
                    case 6:
                        loadStudentDetails();      //load student details to main menu from the file
                        break;
                    case 7:
                        viewStudentDetails();       //   view student details which load from text file
                        break;
                    case 8:
                        subMenu(input);    //  create a sub menu for option 8
                        break;
                    case 9:
                        input.close();     // close the scanner
                        System.out.println("Exiting program!");     //   prompting a message  when close the program
                        return; // Exit the method and terminate the main program
                    default:
                        System.out.println("Invalid choice. Enter a number between 1 to 9.");
                        break;          // for invalid inputs
                }
            } else {
                System.out.println("Invalid input. Enter a number between 1 to 9.");
                input.next(); // considering invalid input
            }
        }
    }

    // create a method to print the main menu
    private static void printMainMenu() {
        // prompting the options to the user to select
        System.out.println("\n-------------------------------------");
        System.out.println("  *** STUDENT MANAGEMENT SYSTEM ***");
        System.out.println("-------------------------------------");
        System.out.println("1. Check available seats");
        System.out.println("2. Register student (with ID)");
        System.out.println("3. Delete student");
        System.out.println("4. Find student (with student ID)");
        System.out.println("5. Store student details into a file");
        System.out.println("6. Load student details from the file to the system");
        System.out.println("7. View the list of students");
        System.out.println("8. Enter student results");
        System.out.println("9. Exit program");
        System.out.print("Please enter your choice (1-9): ");
    }
    //create a method to print the main menu
    private static void printSubMenu() {
        //   prompting the options in the option 8
        System.out.println("\n----------------------------------------");
        System.out.println("  *** Student Results Submenu ***");
        System.out.println("----------------------------------------");
        System.out.println("a. Enter student details");
        System.out.println("b. Individual report");
        System.out.println("c. Overall summary");
        System.out.println("d. Exit to main menu");
        System.out.print("Please enter your choice (a to d): ");
    }

    // create a method to sub menu in option 8
    private static void subMenu(Scanner subInput) {
        while (true) {      // create a while loop for additional options related option 8
            printSubMenu();      // printing sub menu to user
            char choice = subInput.next().charAt(0);
            subInput.nextLine(); // consider newline

            switch (choice) {      // create a switch case block get user inputs for option 8
                case 'a':
                    enterResults(subInput);
                    break;
                case 'b':
                    allStudentReport();
                    break;
                case 'c':
                    individualSummary();
                    break;
                case 'd':
                    return; // Exit submenu and return to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void seatsCheck() {      // to create a method for check available seats
        int availableSeats = TOTAL_SEATS - countOfStudent;    //  when registering students the total seats decrease by one
        System.out.println("Available seats: " + availableSeats);     //  prompting the available seats related to the registration
    }

    private static void studentRegistration(Scanner registerInput) {     // create a module for register students
        if (countOfStudent >= TOTAL_SEATS) {
            System.out.println("No available seats.");
            return;
        }

        String studentID = null;     // declare a variable to student ID
        boolean validID = false;      // set a default value of valid ID as false

        // Give the user three chances to enter a valid student ID
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter student ID: ");
            studentID = registerInput.nextLine();
            if (isValidStudentID(studentID)) {       // validation for student ID  entry
                validID = true;             // validate of student ID
                break;
            } else {
                System.out.println("Invalid student ID. It should be 8 characters long, start with 'w'");
            }
        }

        if (!validID) {
            System.out.println("Failed to enter a valid student ID");
            System.exit(1);  // Terminate the main program
        }
        System.out.print("Enter student name: ");
        String studentName = registerInput.nextLine(); // Get the student name

        students[countOfStudent] = new Student(studentID, studentName);    // passing an empty argument
        countOfStudent++;
        System.out.println("Student registered successfully.");
    }

    private static boolean isValidStudentID(String studentID) {      // validation for student ID
        return studentID.matches("w\\d{7}");      //   student ID should contain with 'w' letter and seven numbers
    }

    private static void deleteStudent(Scanner deleteInput) {          // create a module for delete a student details from main file
        System.out.print("Enter student ID to delete: ");          // prompting a massage after delete a student
        String id = deleteInput.nextLine();

        for (int i = 0; i < countOfStudent; i++) {         // create a for loop to search a specific student
            if (students[i].getStudentID().equals(id)) {     // conformation for ID is correct or not
                for (int j = i; j < countOfStudent - 1; j++) {    // give conditions for the for loop
                    students[j] = students[j + 1];
                }
                students[--countOfStudent] = null;
                System.out.println("Student deleted successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private static void findStudent(Scanner findStudent) {          // create a module to find a student
        System.out.print("Enter student ID to find: ");
        String id = findStudent.nextLine();      //  create an object to Student ID

        for (int i = 0; i < countOfStudent; i++) {     // increase the student count while running the for loop
            if (students[i].getStudentID().equals(id)) {
                System.out.println("Student ID: " + students[i].getStudentID() + ", Name: " + students[i].getStudentName());
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private static void storeStudentDetails() {     // create a module for store student details
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE))) {      // write the details to the main text file
            for (int i = 0; i < countOfStudent; i++) {
                writer.print(students[i].getStudentID() + "," + students[i].getStudentName());
                for (int j = 0; j < 3; j++) {
                    writer.print("," + students[i].getModuleMarks(j));
                }
                writer.println();      // write the details in to file
            }
            System.out.println("Student details saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving student details: " + e.getMessage());     // handeling the error related to invalid registrations
        }
    }

    private static void loadStudentDetails() {     // create a module for load student data
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            countOfStudent = 0;     // set default value to student count
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                students[countOfStudent] = new Student(details[0], details[1]);
                for (int i = 0; i < 3; i++) {
                    students[countOfStudent].setModuleMarks(i, Integer.parseInt(details[i + 2]));
                }
                countOfStudent++;    // increase the student count
            }
            System.out.println("Student details loaded successfully.");
        } catch (IOException e) {     // handle exception for unregistered students
            System.out.println("Error loading student details: " + e.getMessage());
        }
    }

    private static void viewStudentDetails() {    // create a module for view student details
        // Create a copy of the student array
        Student[] sortedStudents = new Student[countOfStudent];
        System.arraycopy(students, 0, sortedStudents, 0, countOfStudent);

        // Sort students by name using bubble sort
        bubbleSortStudentsByName(sortedStudents, countOfStudent);

        System.out.println("Students sorted by name:");
        for (int i = 0; i < countOfStudent; i++) {
            System.out.println("Student ID: " + sortedStudents[i].getStudentID() + ", Name: " + sortedStudents[i].getStudentName());
        }
    }

    private static void bubbleSortStudentsByName(Student[] students, int count) {
        // Sort students names by using bubble sort method
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (students[j].getStudentName().compareTo(students[j + 1].getStudentName()) > 0) {
                    // Swap students[j] and students[j + 1]
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }


    private static void enterResults(Scanner enterResults) {     // create a module for results entry
        System.out.print("Enter student ID: ");
        String id = enterResults.nextLine();   // create an object to result entry

        Student student = null;
        for (int i = 0; i < countOfStudent; i++) {
            if (students[i].getStudentID().equals(id)) {
                student = students[i];
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter student name: ");     // create an object for student name entry
        String studentName = enterResults.nextLine();
        student.setStudentName(studentName);    // call the student class student name

        boolean failed = true;
        for (int moduleIndex = 0; moduleIndex < 3; moduleIndex++) {
            System.out.print("Enter marks for module " + (moduleIndex + 1) + ": ");
            int marks = enterResults.nextInt();
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Marks should be between 0 and 100.");
                moduleIndex--;  // Retry the same module index
                continue;
            }
            if (marks < 40) {
                failed = false;
            }
            student.setModuleMarks(moduleIndex, marks);
        }

        if (failed) {

        } else {
            System.out.println("Module marks updated successfully.");
        }
    }

    private static void individualSummary() {    // create a module for individual grade summery
        System.out.println("\n--------------------------------------");
        System.out.println("    *** Summary Report ***");
        System.out.println("--------------------------------------");

        bubbleSortStudentsByName(students, countOfStudent);

        int passCount = 0;
        int failCount = 0;
        for (int i = 0; i < countOfStudent; i++) {    // create a for loop to find individual student marks summary
            if (students[i].getAverageMarks() >= 40) {
                passCount++;
            } else {
                failCount++;
            }
        }
        System.out.println("Total Registered students: " + countOfStudent);
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
    }

    private static void allStudentReport() {    // create a module to all student report
        System.out.println("\n-------------------------------------");
        System.out.println("    *** Complete Report***");
        System.out.println("---------------------------------------");

        bubbleSortStudentsByName(students, countOfStudent);
        for (int i = 0; i < countOfStudent; i++) {   // create a for loop to student count
            System.out.println("Student ID: " + students[i].getStudentID() + ", Name: " + students[i].getStudentName());
            for (int j = 0; j < 3; j++) {
                System.out.println("Module " + (j + 1) + " Marks: " + students[i].getModuleMarks(j));
            }
            System.out.println("Average Marks: " + students[i].getAverageMarks());
            System.out.println("Grade: " + students[i].getGrade());
            System.out.println(); // Add a blank line for better readability
        }
    }
}
