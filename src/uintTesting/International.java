package uintTesting;

public class International extends NonResident {

    private Profile student;
    private Date lastPaid;
    private int creditHours;
    private double tuitionFee, financialAid, totalPaid;
    private boolean studyAbroad;

    public International() {
    }

    public International(Profile student, int creditHours, boolean studyAbroad) {
        lastPaid = new Date("00/00/00");
        this.student = student;
        this.creditHours = creditHours;
        this.studyAbroad = studyAbroad;
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
        String s = String.format("%s:%d credit hours:tuition due:%1.2f:total payment:%1.2f:last payment date: %s:non-resident:international:"
                                 , student.toString(), creditHours, tuitionFee, totalPaid, lastPaid.getDate());
        if(studyAbroad){
            s += "study abroad";
        }
        return s;
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
                if(creditHours > 16){
                    tuitionFee += (creditHours - 16) * 966;
                }
            }
        }
    }

    /**
     * will make a payment so long as the payment isn't greater than the tuitionfee.
     *
     * @param payment the amount to be paid
     * @return false if the payment is too large.
     */
    @Override
    public boolean pay(double payment, Date datePaid) {

        if ( payment <= tuitionFee ) {   // Check that the payment is less than the tuition fee
            totalPaid += payment;
            tuitionFee -= payment;
            lastPaid = datePaid;
            return true;
        }

        return false;
    }

    /**
     * sets the finanical aid of the student.
     *
     * @param financialAid the amount given
     * @return false if the financial aid was already given
     */
    @Override
    public boolean setFinancialAid(double financialAid) {
        return super.setFinancialAid(financialAid);
    }

    public boolean setStudyAbroad(boolean abroadState){
        if(creditHours <= 12){
            studyAbroad = abroadState;
            tuitionDue();
            return true;
        }

        return false;
    }
}
