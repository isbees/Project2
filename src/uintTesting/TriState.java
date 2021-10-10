package uintTesting;

import java.util.Locale;

public class TriState extends NonResident {

    private Profile student;
    private Date lastPaid;
    private int creditHours;
    private double tuitionFee, financialAid, totalPaid;
    private String state;

    public TriState() {
    }

    public TriState(Profile student, int creditHours, String state) {
        this.student = student;
        this.creditHours = creditHours;
        this.state = state;
        tuitionFee = 0;
        financialAid = 0;
        totalPaid = 0;
    }

    /**
     * will return a string with the variables and ends in non resident(tri-state):<state>
     *
     * @return String the formatted output
     */
    @Override
    public String toString() {
        return String.format("%s:%d credit hours:tuition due:%1.2f:total payment:%1.2f:last payment date: %s:non-resident(tri-state):%s"
                , student.toString(), creditHours, tuitionFee, totalPaid, lastPaid.getDate(), state);
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
        if (state.equals("NY")) {
            tuitionFee -= 4000;
        } else if (state.equals("CN")) {
            tuitionFee -= 5000;
        }
    }

    /**
     * will make a payment so long as the payment isn't greater than the tuition.
     *
     * @param payment the amount to be paid
     * @return false if the totalPaid is equal to tutionFee or if the payment size is too large.
     */
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
        return super.setFinancialAid(financialAid);
    }
}
