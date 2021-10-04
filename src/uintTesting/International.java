package uintTesting;

public class International extends NonResident{

    private final int UNIVERSITY_FEE = 3268;    // University Fee

    private Profile student;
    private int creditHours, tuitionFee;

    public International() {
    }

    public International(Profile student, int creditHours) {
        this.student = student;
        this.creditHours = creditHours;
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public void tuitionDue() {
        // does nothing
    }
}
