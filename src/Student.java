public class Student {   // create the student class
    private final String studentID;   // set final variable to store student ID
    private String studentName;   // create variable to store name
    private final int[] moduleMarks = new int[3];    // create an array to store marks of 3 modules

    // constructor to initialize student ID
    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

        // getter method to return student ID
    public String getStudentID() {
        return studentID;
    }
    // getter to return student name
    public String getStudentName() {
        return studentName;
    }

    //setter method to set student name
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    // getter method to set student marks
    public int getModuleMarks(int index) {
        return moduleMarks[index];
    }

    // getter method to set student marks
    public void setModuleMarks(int index, int marks) {
        moduleMarks[index] = marks;
    }

    // Method to calculate and return the average marks
    public double getAverageMarks() {
        int total = 0;     // Variable to store the sum of module marks
        for (int marks : moduleMarks) {
            total += marks;
        }                // Add each module mark to the total
        return total / 3.0;     // average mark return
    }

    // create a method to return marks
    public String getGrade() {
        double average = getAverageMarks();
        if (average >= 80) {
            return "Distinction";
        } else if (average >= 70) {
            return "Merit";
        } else if (average >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }
}
