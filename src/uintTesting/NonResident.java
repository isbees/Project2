package uintTesting;

public class NonResident extends Student {

    private final int UNIVERSITY_FEE = 3268;    // University Fee

    private Profile student;
    private int creditHours, tuitionFee;

    public NonResident(){

    }

    public NonResident(Profile student, int creditHours) {
        this.student = student;
        this.creditHours = creditHours;
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
