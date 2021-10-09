package uintTesting;

public class Resident extends Student {

    private final int UNIVERSITY_FEE = 3268;    // University Fee

    private Profile student;
    private int creditHours, tuitionFee, financialAid;

    public Resident() {
    }

    public Resident(Profile student, int creditHours) {
        this.student = student;
        this.creditHours = creditHours;
        tuitionFee = 0;
        financialAid = 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void tuitionDue() {
        if (!student.getFullTime()) {
            tuitionFee = (int) (UNIVERSITY_FEE * 0.8); // 80% of the university fee
            tuitionFee += 404 * creditHours;
        } else {
            tuitionFee = UNIVERSITY_FEE;
            tuitionFee += 12536;
        }


    }

    //payment
    //save the date
    //upadate the payment date
    //

    public void setFinancialAid(int financialAid){
        if((this.financialAid + financialAid) >= 10000){
            this.financialAid = 10000;
        } else {
            this.financialAid += financialAid;
        }
        //check the amount that financialAid takes away from tuition. if negative balance out the equations.
        if(tuitionFee - financialAid < 0) {
            //
        }
    }
}
