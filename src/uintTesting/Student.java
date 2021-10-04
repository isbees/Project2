package uintTesting;

public class Student {

    private final int UNIVERSITY_FEE = 3268;    // University Fee

    private Profile student;
    private int creditHours, tuitionFee;

    /**
     * Empty constructor
     */
    public Student() {

    }

    /**
     * Constructor creates a profile after finding the fullTime status, sets all the private variables, and calculates tuitionDue
     *
     * @param name of the student
     * @param major the student is studying
     * @param creditHours that the student is taking
     */
    public Student(String name, String major, int creditHours) {
        boolean fullTime = (creditHours - 12) >= 0;

        this.creditHours = creditHours;
        this.student = new Profile(name, major, fullTime);
        tuitionDue();
    }

    /**
     * Constructor passes the inputs to the private variables and calculates the tuition due
     *
     * @param student profile with name, major and full time status
     * @param creditHours that the student is taking
     */
    public Student(Profile student, int creditHours) {
        this.creditHours = creditHours;
        this.student = student;
        tuitionDue();
    }

    /**
     * toString will return the student Profile toString
     *
     * @return a string of the name and major
     */
    @Override
    public String toString() {
        return student.toString();  // will add more to this to string later. + ":" +
    }

    /**
     * tuitionDue will calculate the university fee for a student based on full time status.
     */
    public void tuitionDue() {
        tuitionFee = UNIVERSITY_FEE;

        if (!student.getFullTime())
            tuitionFee = (int) (UNIVERSITY_FEE * 0.8); // 80% of the university fee
    }

} //end Student
