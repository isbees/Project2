package uintTesting;

public class NonResident extends Student {

    private Profile student;
    private Date lastPaid;
    private int creditHours;
    private double tuitionFee, financialAid, totalPaid;
    private boolean calculated = false;

    public NonResident() {

    }

    public NonResident(Profile student, int creditHours) {
        lastPaid = new Date("00/00/00");
        this.student = student;
        this.creditHours = creditHours;
        tuitionFee = 0;
        financialAid = 0;
        totalPaid = 0;
    }

    public NonResident(String name, Major major, int creditHours) {
        boolean fullTime = (creditHours - 12) >= 0;

        lastPaid = new Date("00/00/00");
        this.student = new Profile(name, major, fullTime);
        this.creditHours = creditHours;
        tuitionFee = 0;
        financialAid = 0;
        totalPaid = 0;
    }

    /**
     * will return a string with the variables and ends in non resident
     *
     * @return String the formatted output
     */
    @Override
    public String toString() {
        return String.format("%s:%d credit hours:tuition due:%1.2f:total payment:%1.2f:last payment date: %s:non-resident"
                , student.toString(), creditHours, tuitionFee, totalPaid, lastPaid.getDate());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NonResident) {
            NonResident newStudent = (NonResident) obj;
            return (newStudent.getProfileP()).equals(this.getProfileP());
        }
        return false;
    }

    public String getProfile() {
        return student.toString();
    }

    public Profile getProfileP() {
        return student;
    }

    @Override
    public void tuitionDue() {
        if (!calculated) {
            if (!student.getFullTime()) {
                tuitionFee = (UNIVERSITY_FEE * 0.8); // 80% of the university fee
                tuitionFee += 966 * creditHours;
            } else {
                tuitionFee = UNIVERSITY_FEE;
                tuitionFee += 29737;
                if (creditHours > 16) {
                    tuitionFee += (creditHours - 16) * 966;
                }
            }
            calculated = true;
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

        if (payment <= tuitionFee) {   // Check that the payment is less than the tuition fee
            totalPaid += payment;
            tuitionFee -= payment;
            lastPaid = datePaid;
            return true;
        }

        return false;
    }

    /**
     * returns false since non-residential students get no aid.
     *
     * @param financialAid the amount given
     * @return false if the financial aid was already given
     */
    @Override
    public boolean setFinancialAid(double financialAid) {
        return false;
    }

    /**
     * Getter for lastPaid
     *
     * @return Date the date that the student last paid
     */
    public Date getLastPaid() {
        return lastPaid;
    }

    /**
     * finds if the account has paid once
     *
     * @return true if the total paid is more than 0
     */
    @Override
    public boolean hadPaid() {
        return totalPaid > 0;
    }
}
