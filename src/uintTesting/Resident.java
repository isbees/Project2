package uintTesting;

public class Resident extends Student {

    private Profile student;
    private Date lastPaid;
    private int creditHours, tuitionFee, financialAid, totalPaid;

    public Resident() {
    }

    public Resident(Profile student, int creditHours) {
        this.student = student;
        this.creditHours = creditHours;
        tuitionFee = 0;
        financialAid = 0;
        totalPaid = 0;
    }

    /**
     * will return a string with the variables and ends in resident
     *
     * @return String the formatted output
     */
    @Override
    public String toString() {
        return student.toString() + ":" + creditHours + "credit hours:tuition due:" + tuitionFee
                                + ":total payment:" + totalPaid + ":last payment date: " + lastPaid.getDate() + ":resident";
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

    /**
     * will make sure that a payment and financial aid used the
     *
     * @param payment the amount to be paid
     * @return
     */
    public boolean pay(int payment){

        return true;
    }
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
