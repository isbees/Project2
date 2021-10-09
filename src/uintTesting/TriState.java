package uintTesting;

import java.util.Locale;

public class TriState extends NonResident {

    private final int UNIVERSITY_FEE = 3268;    // University Fee

    private Profile student;
    private int creditHours, tuitionFee;
    private String state;

    public TriState() {
    }

    public TriState(Profile student, int creditHours, String state) {
        this.student = student;
        this.creditHours = creditHours;
        this.state = state;
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public void tuitionDue() {
        if (!student.getFullTime()) {
            tuitionFee = (int) (UNIVERSITY_FEE * 0.8); // 80% of the university fee
            tuitionFee += 966 * creditHours;
        } else {
            tuitionFee = UNIVERSITY_FEE;
            tuitionFee += 29737;
        }
        if(state.equals("NY")) {
            tuitionFee -= 4000;
        } else if (state.equals("CN")) {
            tuitionFee -= 5000;
        }
    }
}
