package uintTesting;

public class International extends NonResident{

    private Profile student;
    private Date lastPaid;
    private int creditHours, tuitionFee, financialAid, totalPaid;
    private boolean studyAbroad;

    public International() {
    }

    public International(Profile student, int creditHours, boolean studyAbroad) {
        this.student = student;
        this.creditHours = creditHours;
        this.studyAbroad = studyAbroad;
    }

    //setstudyabroad
        // change the tuitionFee again

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public void tuitionDue() {
        if (studyAbroad) {
            if (!student.getFullTime()) {
                tuitionFee = (int) (UNIVERSITY_FEE * 0.8); // 80% of the university fee
            } else {
                tuitionFee = UNIVERSITY_FEE;
                tuitionFee += 2650;
            }
        } else {
            if (!student.getFullTime()) {
                tuitionFee = (int) (UNIVERSITY_FEE * 0.8); // 80% of the university fee
                tuitionFee += 966 * creditHours;
            } else {
                tuitionFee = UNIVERSITY_FEE;
                tuitionFee += 29737;
                tuitionFee += 2650;
            }
        }
    }
}
