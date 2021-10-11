package uintTesting;

import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.Scanner;

//Comments: 159, 145, 184, gotta check also once I set study abroad that the credit amounts are okay.
//Need a set payment, among other things. assumed we have finaid as a field.
//Need class diagram.
//

public class TuitionManager {
    public static void main(String[] args) {
    }

    //Takes in the input and sends it off to check its validity,
    // then calls to do the command
    public static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("TuitionManager Started");
        Roster rost = new Roster();
        //Read in the inputs
        while (sc.hasNextLine()) {
            String inputLine = sc.nextLine();
            StringTokenizer st = new StringTokenizer(inputLine, ",");
            //Deal with empty lines
            if (!(st.hasMoreTokens())) {
                continue;
            }
            String letters = st.nextToken();
            //cmdType is index from: {"AI","AT","AN","AR","R","C","S","F","T","Q"}
            int cmdType = checkCmdValidity(letters, st);
            //If it's invalid command
            if (cmdType == -1) {
                continue;
            }
            //If it's a Q
            if (cmdType == 9) {
                break;
            }
            doCommand(letters, st, rost);
        }
        System.out.println("TuitionManager Terminated.");
        sc.close();
    }


    //Checks validity of command
    public static int checkCmdValidity(String letters, StringTokenizer st) {
        //If not capitalized, invalid
        if (!(letters.toUpperCase().equals(letters))) {
            System.out.println("Invalid (command isn't fully capitalized).");
            return -1;
        }
        //If not in list of good commands, invalid.
        String[] validCommands = {"AI", "AT", "AN", "AR", "R", "C", "S", "F", "T", "Q", "P", "PT", "PN"};
        int cmdIndex = -1;
        for (int i = 0; i < validCommands.length; i++) {
            if (validCommands[i].equals(letters)) {
                cmdIndex = i;
            }
        }
        if (cmdIndex == -1) {
            System.out.println("Not a valid command!");
            return -1;
        }
        //Check that there's the right amount of tokens
        int totalTokens = st.countTokens() + 1;

        //Check that of C,P,PT,PN there's 1 total token.
        if (letters.equals("C") || letters.equals("P") || letters.equals("PT") || letters.equals("PN")) {
            if (totalTokens != 1) {
                System.out.println("Invalid (incorrect number of tokens for C, P, PT, or PN).");
                cmdIndex = -1;
            }
        }
        //Check that for remove, there's 3 total tokens.
        if (letters.equals("R")) {
            if (totalTokens != 3) {
                System.out.println("Invalid (incorrect number of tokens for R).");
                cmdIndex = -1;
            }
        }
        //Check that for S, F, AR, AN there's 4 total tokens.
        if (letters.equals("S") || letters.equals("F") || letters.equals("AR") || letters.equals("AN")) {
            if (totalTokens != 4) {
                System.out.println("Invalid (incorrect number of tokens for S, F, AR, or AN).");
                cmdIndex = -1;
            }
        }
        //Check that for AI, AT, T, there's 5 total tokens.
        if (letters.equals("AI") || letters.equals("AT") || letters.equals("T")) {
            if (totalTokens != 5) {
                System.out.println("Invalid (incorrect number of tokens for AI, AT or T).");
                cmdIndex = -1;
            }
        }
        return cmdIndex;
    }

    //Adds a student to the roster by calling the addStudent method.
    //An independent method in tuition manager that deals with output of that method
    //and prints subsequent result
    public static void addStudentWithPrinting(Roster roster, Student newStudent) {
        boolean added = roster.add(newStudent);
        if (!added) {
            System.out.println("Didn't add.");
        } else {
            System.out.println("Added student.");
        }
    }

    public static boolean isNumber(String test) {
        try {
            double testDouble = Double.parseDouble(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isString(String test) {
        try {
            int testInt = Integer.parseInt(test);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }


    public static void addStudentCommand(String cmd, String name, Major major, StringTokenizer st, Roster roster) {
        //Now can go onto adds, finding credits

        String creditsString = st.nextToken();
        boolean isNumber = isNumber(creditsString);
        if (!isNumber) {
            System.out.println("Number of credits isn't a number!");
            return;
        }
        int credits = Integer.parseInt(creditsString);
        if (credits < 3 || credits > 24) {
            System.out.println("Invalid credit amount");
            return;
        }
        if (cmd.equals("AN") || cmd.equals("AR")) {
            if (cmd.equals("AN")) {
                System.out.println("We're adding non resident.");

                NonResident newStudent = new NonResident(name, major, credits);
                addStudentWithPrinting(roster, newStudent);


                return;
            } else if (cmd.equals("AR")) {
                System.out.println("We're adding resident.");
                int financialAid = 0;

                Resident newStudent = new Resident(name, major, credits);
                addStudentWithPrinting(roster, newStudent);

            }
        }

        //Last comes AI, AT with 5 tokens. For both, we need to use the next token.
        // Now just need state or studyabroad
        if (cmd.equals("AT")) {
            System.out.println("We're adding tristate.");
            String state = st.nextToken();
            state = state.toUpperCase();
            if (!(state.equals("NY") || state.equals("CT"))) {
                System.out.println("Trying to add tristate but not from NY or CT. Invalid!");
                return;
            }

            TriState newStudent = new TriState(name, major, credits, state);
            addStudentWithPrinting(roster, newStudent);


            return;
        }
        if (cmd.equals("AI")) {
            if (credits < 12) {
                System.out.println("Invalid. Trying to make parttime international.");
                return;
            }
            System.out.println("We're adding international.");
            String studyAbroadTest = st.nextToken();
            if (!(studyAbroadTest.equals("true") || studyAbroadTest.equals("false"))) {
                System.out.println("You need to give a boolean for studyabroad, not a string");
                return;
            }
            boolean studyAbroad = Boolean.parseBoolean(studyAbroadTest);

            Profile intStudentProfile = new Profile(name, major);
            International newStudent = new International(intStudentProfile, credits, studyAbroad);
            addStudentWithPrinting(roster, newStudent);

            return;
        }
    }


    //Checks validity of line then calls the functions
    public static void doCommand(String cmd, StringTokenizer st, Roster roster) {
        if (cmd.equals("P")) {
            roster.print();
            return;
        }
        if (cmd.equals("PT")) {
            roster.printByTuition();
            return;
        }
        if (cmd.equals("PN")) {
            roster.printByName();
            return;
        }
        if (cmd.equals("C")) {
            roster.calculateTuition();
            System.out.println("Calculation complete.");
            return;
        }

        //Now need to quickly check that the rest of the line is valid.

        //Check the name and major are strings:
        String name = st.nextToken();
        boolean isNameString = isString(name);

        if (!isNameString) {
            System.out.println("You put a number for name. Invalid.");
            return;
        }

        String major = st.nextToken();
        boolean isMajorString = isString(major);

        if (!isMajorString) {
            System.out.println("You put a number for name. Invalid.");
            return;
        }

        //Check that it's a valid major
        int validMajor = -1;
        String[] validMajors = {"CS", "IT", "BA", "EE", "ME"};
        major = major.toUpperCase();                            // added the toUpperCase to get rid of redundant code

        for (int i = 0; i < validMajors.length; i++) {
            if (validMajors[i].equals(major)) {
                validMajor = i;
            }
        }
        if (validMajor == -1) {
            System.out.println("Invalid major name");
            return;
        }   // after this point we know that major is valid so we convert it to the Major enum

        Major majorM = Major.valueOf(major);

        //We have name, major. Since remove requires no more tokens, deal with it first.
        if (cmd.equals("R")) {
            System.out.println("We're in remove.");

            Student newStudent = new Student(name, majorM, 0);
            boolean removed = roster.remove(newStudent);
            if (removed) {
                System.out.println("Student removed from the roster student.");
            } else {
                System.out.println("Student is not in the roster.");
            }

            return;
        }

        //Next comes S,F, AT, AR, AN. Gotta check the next token for them.
        //To make it simpler, I put the AT, AR, and AN all with AI in addStudentCommand()
        if (cmd.equals("S")) {
            System.out.println("We're in set study abroad");
            //Check the last token is "true" or "false"
            String studyAbroadTest = st.nextToken();
            if (!(studyAbroadTest.equals("true") || studyAbroadTest.equals("false"))) {
                System.out.println("You need to give a boolean for studyabroad, not a string");
                return;
            }
            boolean studyAbroad = Boolean.parseBoolean(studyAbroadTest);

            Profile intProfile = new Profile(name, majorM);
            International newStudent = new International(intProfile, 0, false);
            boolean updated = false;// Roster.setStudyAbroad(newStudent, studyAbroad);
            if (updated) {
                System.out.println("Tuition updated.");
            } else {
                System.out.println("Couldn't find international student.");
            }


            return;
        }

        if (cmd.equals("F")) {
            System.out.println("We're in give financial aid.");
            String finAid = st.nextToken();
            boolean validDouble = isNumber(finAid);
            if (!validDouble) {
                System.out.println("Invalid financial aid amount - not a number");
                return;
            }
            double financialAid = Double.parseDouble(finAid);
            //Check it's 0<x<=10k
            if (financialAid <= 0 || financialAid > 10000) {
                System.out.println("FinAid exceeds 10k or is negative! No way you're getting that much money with a GPA like that bruh.");
                return;
            }

            //NEED CONSTRUCTOR FOR NAME,MAJOR

            Student newStudent = new Student(name, majorM, 0);

            //Check if student is in roster
            Student s = roster.findStudent(newStudent);
            if (null == s) {
                System.out.println("Student not in roster.");
                return;
            }

            //Check if student is Resident
            boolean isResident = (s instanceof Resident);
            if (!isResident) {
                System.out.println("Not a resident.");
                return;
            }

            //Check if student is fulltime
            boolean fullTime = s.getCredit() >= 12;
            if (!fullTime) {
                System.out.println("Parttime student doesn't qualify for the award.");
                return;
            }
            boolean addedAid = s.setFinancialAid(financialAid);
            if (addedAid) {
                System.out.println("Tuition updated.");
            } else {
                System.out.println("Already given.");
            }

            return;
        }

        //Gotta check their credits for Adding any type! So before I do that, I'll deal with my last command, T
        if (cmd.equals("T")) {
            //Check it's a valid payment amount
            String paymentAmountString = st.nextToken();
            boolean isNumber = isNumber(paymentAmountString);
            if (!isNumber) {
                System.out.println("Amount of payment isn't a number!");
                return;
            }
            double paymentAmount = Double.parseDouble(paymentAmountString);

            //Check the date is valid
            String dateOfPaymentString = st.nextToken();
            Date dateOfPayment = new Date(dateOfPaymentString);
            if (!dateOfPayment.isValid()) {
                System.out.println("Invalid payment date!");
                return;
            }

            System.out.println("We're in pay tuition");

            //Send the payment
            //Check that the student is in the roster
            Student newStudent = new Student(name, majorM, 0);
            Student s = roster.findStudent(newStudent);
            if (null == s) {
                System.out.println("Student not in roster.");
                return;
            }
            boolean paidDone = roster.pay(s, paymentAmount, dateOfPayment);
            //Check if payment <= tuition due
            if (paidDone) {
                System.out.println("Added a payment.");
            } else {
                System.out.println("Invalid, greater than tuition due.");
            }

            return;
        }
        //We're adding a student as those are the only commands left -> Go deal with that
        addStudentCommand(cmd, name, majorM, st, roster);
    }
}
