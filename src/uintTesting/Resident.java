package uintTesting;

public class Resident extends Student {

    private final int UNIVERSITY_FEE = 3268;    // University Fee

    private Profile student;
    private int creditHours, tuitionFee;

    public Resident() {
    }

    public Resident(Profile student, int creditHours) {
        this.student = student;
        this.creditHours = creditHours;
        tuitionDue();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void tuitionDue() {
        tuitionFee = UNIVERSITY_FEE;

        if (!student.getFullTime()) {
            tuitionFee = (int) (UNIVERSITY_FEE * 0.8); // 80% of the university fee
            tuitionFee += 404 * creditHours;
        }

        tuitionFee += 12536;
    }
}
