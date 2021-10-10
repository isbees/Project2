package uintTesting;

public class Resident extends Student {

    private Profile student;
    private Date lastPaid;
    private int creditHours;
    private double tuitionFee, financialAid, totalPaid;

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
        return String.format("%s:%d credit hours:tuition due:%1.2f:total payment:%1.2f:last payment date: %s:resident"
                             ,student.toString(),creditHours,tuitionFee,totalPaid,lastPaid.getDate());
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


    //note Isaac. You can over pay. don't over think this you dumbass

    /**
     * will make a payment so long as the payment isn't greater than the tuition.
     *
     * @param payment the amount to be paid
     * @return false if the totalPaid is equal to tutionFee or if the payment size is too large.
     */
    @Override
    public boolean pay(int payment, Date datePaid) {

        if (tuitionFee - payment < 0) {   // Check that the payment is less than the tuition fee
            return false;
        }

        totalPaid += payment;
        lastPaid = datePaid;

        return true;

    }

    /**
     * sets the finanical aid of the student.
     *
     * @param financialAid the amount given
     * @return false if the financial aid was already given
     */
    @Override
    public boolean setFinancialAid(int financialAid) {

        if (this.financialAid > 0) {
            return false;
        }

        this.financialAid = financialAid;

        return true;
    }
}
