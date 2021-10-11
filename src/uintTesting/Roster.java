package uintTesting;

public class Roster {

    private Student[] roster;
    private int size;

    /**
     * Find will linearly search through the roster and return the index if found. If not -1 is returned.
     *
     * @param student takes in an Student to check if it's in our collections
     * @return index that's index of Student if found, -1 otherwise
     */
    private int find(Student student) {
        int index;
        for (index = 0; index < size; index++) {
            if (student.getProfile().equals(roster[index].getProfile())) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Grow will initiate the size by 4 and will increase roster's capacity by 4 after initialization.
     */
    private void grow() {
        size += 4;
        Student[] plus4 = new Student[size];

        for (int i = 0; i < size; i++) {
            plus4[i] = roster[i];
        }

        roster = plus4;
    }


    /**
     * Add will look for an open spot and put the new Student in. If no spot is found, grow() and add the Student
     * to the array
     *
     * @param student that we're trying to add to the roster
     * @return boolean true if added, false if duplicate
     */
    public boolean add(Student student) {
        if (null == roster) {
            size = 4;
            roster = new Student[size];
        }

        //Return false if the Student
        // is a duplicate
        for (Student a : roster) {
            if (a == null) {
                break;
            }
            if (a.equals(student)) {
                return false;
            }
        }

        // If the array isn't full search for an open spot
        if (null == roster[size - 1]) {
            //Go through it backwards until find the place to put it
            for (int i = size - 1; i >= 0; i--) {

                if (!(null == roster[i])) {
                    roster[i + 1] = student;
                    return true;
                }
            }
            //Completely empty
            roster[0] = student;
            return true;
        } else { //If it's full, grow it and then add at the 1st new spot
            grow();
            roster[size - 4] = student;
            return true;
        }
    }

    /**
     * Remove will shift the array forward from the index of the Student that will be removed.
     *
     * @param student that we're trying to remove from the roster
     * @return boolean true if removes, false if can't find the Student
     */
    public boolean remove(Student student) {

        int i = find(student), len = size;

        //returns false if not there
        if (i == -1)
            return false;

        // shifts the whole array forward by one starting from the index of the Student

        for (int j = i; j < size - 1; j++) {
            roster[j] = roster[j + 1];
        }

        // set the last index to null since the whole thing was shifted
        roster[size - 1] = null;

        return true;
    }


    public Student findStudent(Student s) {
        return roster[find(s)];
    }

    /**
     * Calculated tuition for the whole roster
     */
    public void calculateTuition() {
        for (int i = 0; i < size; i++) {
            if (roster[i] == null) {
                return;
            }
            roster[i].tuitionDue();
        }
    }

    public boolean pay(Student student, double amount, Date date) {
        int i = find(student);
        return roster[i].pay(amount, date);
    }

    public boolean setFinancialAid(Student student, double amount) {
        int i = find(student);
        return roster[i].setFinancialAid(amount);
    }


    /**
     * Set the abroad status to the inputed value if the student is international
     *
     * @param student the international student we want to flip the
     * @return false if the student was not found or if the credits were more than 12
     */
    public boolean setStudyAbroad(Student student, boolean abroadState) {
        int i = find(student);
        if (i == -1)
            return false;

        if (roster[i] instanceof International) { // should return true if the student has more than 12 credits
            return ((International) roster[i]).setStudyAbroad(abroadState);
        }

        return false;
    }

    /**
     * print, prints the whole roster sorted by index.
     */
    public void print() {
        if (roster == null) {
            System.out.println("The collection is empty.");
            return;
        }

        System.out.println("* list of students in the roster **");

        //Print the stuff here or coll empty
        for (int i = 0; i < size; i++) {
            if (null == roster[i]) {
                continue;
            }
        }

        System.out.println("* end of roster **");
    }

    /**
     * printByName will sort roster alphabetically and then print
     */
    public void printByName() {
        if (roster == null) {
            System.out.println("The collection is empty.");
            return;
        }
        System.out.println("* list of students ordered by name **");

        int total = size;
        Student[] sort = new Student[size];

        for (int i = 0; i < total; i++) { // just sets sort = to roster
            sort[i] = roster[i];
        }

        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < total - 1; i++) {
                if (sort[i].toString().compareTo(sort[i + 1].toString()) > 0) {
                    Student temp = sort[i];
                    sort[i] = sort[i + 1];
                    sort[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        for (int i = size - 1; i >= 0; i--) {                  // prints the roster
            System.out.println(sort[i].toString());
        }
        System.out.println("* end of roster **");
    }

    /**
     * printByTuition will find all the students that paid and sort them by last payment date. Prints the resulting list
     */
    public void printByTuition() {
        if (roster == null) {
            System.out.println("The collection is empty.");
            return;
        }
        System.out.println("* list of students made payments ordered by payment date **");

        Student[] sort = new Student[size];
        int sortIndex = 0;

        for (int i = 0; i < size; i++) { // just sets sort = to roster
            if (roster[i].hadPaid()) {
                sort[sortIndex] = roster[i];
                sortIndex++;
            }
        }

        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < size - 1; i++) {
                if (sort[i].getLastPaid().compareTo(sort[i + 1].getLastPaid()) > 0) {
                    Student temp = sort[i];
                    sort[i] = sort[i + 1];
                    sort[i + 1] = temp;
                    sorted = false;
                }
            }
        }

        for (int i = size - 1; i >= 0; i--) {                  // prints the roster
            System.out.println(sort[i].toString());
        }
        System.out.println("* end of roster **");
    }
}
