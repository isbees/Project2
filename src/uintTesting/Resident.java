package uintTesting;

public class Resident extends Student {

    private Profile student;
    private Date lastPaid;
    private int creditHours;
    private double tuitionFee, financialAid, totalPaid;
    private boolean calculated = false;

    public Resident() {
    }

    public Resident(Profile student, int creditHours) {
        lastPaid = new Date("00/00/00");
        this.student = student;
        this.creditHours = creditHours;
        tuitionFee = 0;
        financialAid = 0;
        totalPaid = 0;
    }

    public Resident(String name, Major major, int creditHours) {
        boolean fullTime = (creditHours - 12) >= 0;

        lastPaid = new Date("00/00/00");
        this.student = new Profile(name, major, fullTime);
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
        if(financialAid>0) {
            return String.format("%s:%d credit hours:tuition due:%1.2f:total payment:%1.2f:last payment date: %s:resident: financial aid $%1.2f"
                    , student.toString(), creditHours, tuitionFee, totalPaid, lastPaid.getDate(), financialAid);  // lastPaid
        }
        else{
            return String.format("%s:%d credit hours:tuition due:%1.2f:total payment:%1.2f:last payment date: %s:resident"
                    , student.toString(), creditHours, tuitionFee, totalPaid, lastPaid.getDate());  // lastPaid
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Resident) {
            Resident newStudent = (Resident) obj;
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
                tuitionFee += 404 * creditHours;
            } else {
                tuitionFee = UNIVERSITY_FEE;
                tuitionFee += 12536;
                if (creditHours > 16) {
                    tuitionFee += (creditHours - 16) * 404;
                }
            }
            tuitionFee-=financialAid;
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
     * sets the finanical aid of the student.
     *
     * @param financialAid the amount given
     * @return false if the financial aid was already given
     */
    @Override
    public boolean setFinancialAid(double financialAid) {

        if (this.financialAid > 0) {
            return false;
        }

        this.financialAid = financialAid;
        calculated=false;
        tuitionDue();
        return true;
    }

    @Override
    public int getCredit() {
        return creditHours;
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
