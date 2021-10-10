package uintTesting;

public class NonResident extends Student {

    private Profile student;
    private Date lastPaid;
    private int creditHours, tuitionFee, financialAid, totalPaid;

    public NonResident(){

    }

    public NonResident(Profile student, int creditHours) {
        this.student = student;
        this.creditHours = creditHours;
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
            tuitionFee += 966 * creditHours;
        } else {
            tuitionFee = UNIVERSITY_FEE;
            tuitionFee += 29737;
        }

    }
}
