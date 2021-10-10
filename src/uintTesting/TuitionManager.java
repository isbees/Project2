package uintTesting;

import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.Scanner;
//Comments: 159, 145, 184, gotta check also once I set study abroad that the credit amounts are okay.
//Need a set payment, among other things.


public class TuitionManager {
    //Takes in the input and sends it off to check its validity,
    // then calls to do the command
    public static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("TuitionManager Started");
        Roster rost = new Roster();
        int success = 0;
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
            System.out.println("Command isn't fully capitalized.");
            return -1;
        }
        //If not in list of good commands, invalid.
        String[] validCommands = {"AI", "AT", "AN", "AR", "R", "C", "S", "F", "T", "Q","P","PT","PN"};
        int cmdIndex = -1;
        for (int i = 0; i < validCommands.length; i++) {
            if (validCommands[i].equals(letters)) {
                System.out.println("Invalid command");
                cmdIndex = i;
            }
        }

        //Check that there's the right amount of tokens
        int totalTokens = st.countTokens()+1;

        //Check that of C,P,PT,PN there's 1 total token.
        if(letters.equals("C")||letters.equals("P")||letters.equals("PT")||letters.equals("PN")){
            if(totalTokens!=1){
                System.out.println("Invalid number of tokens for C, P, PT, or PN");
            }
        }
        //Check that for remove, there's 3 total tokens.
        if(letters.equals("R")){
            if(totalTokens!=3){
                System.out.println("Invalid number of tokens for R");
                cmdIndex=-1;
            }
        }
        //Check that for S, F, AR, AN there's 4 total tokens .
        if(letters.equals("S")||letters.equals("F")||letters.equals("AR")||letters.equals("AN")){
            if(totalTokens!=4){
                System.out.println("Invalid number of tokens for S, F, AR, AT, or AN");
                cmdIndex=-1;
            }
        }
        //Check that for AI, AT, T, there's 5 total tokens.
        if(letters.equals("AI")||letters.equals("AT")||letters.equals("T")){
            if(totalTokens!=5){
                System.out.println("Invalid number of tokens for AI or T");
                cmdIndex=-1;
            }
        }
        return cmdIndex;
    }
    public static void addStudent(Roster roster, Student newStudent){
        boolean added = roster.add(newStudent);
        if(!added){
            System.out.println("Didn't add.");
        }
        else{
            System.out.println("Added student.");
        }
    }
    public static boolean isNumber(String test){
        try{
            double testDouble = Double.parseDouble(test);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    public static boolean isString(String test){
        try{
            int testInt = Integer.parseInt(test);
            return false;
        }
        catch(NumberFormatException e){
            return true;
        }
    }
    //Checks validity of line
    public static void doCommand(String cmd, StringTokenizer st,Roster roster){
        if(cmd.equals("P")){
            System.out.println("Going to print");
        //    roster.print();
            return;
        }
        if(cmd.equals("PT")){
            System.out.println("Going to print by tuition");
        //    roster.printByTuition;
            return;
        }
        if(cmd.equals("PN")){
            System.out.println("Going to print by name");
            //roster.printByName();
            return;
        }
        if(cmd.equals("C")){
            System.out.println("Going to tuition calculate.");
         //   roster.calculateTuition();
            return;
        }

        //Now need to quickly check that the rest of the line is valid.

        //Check the name and major are strings:
        String name = st.nextToken();
        boolean isNameString = isString(name);
        if(!isNameString){
            System.out.println("You put a number for name. Invalid.");
            return;
        }
        String major = st.nextToken();
        boolean isMajorString = isString(major);
        if(!isMajorString){
            System.out.println("You put a number for name. Invalid.");
            return;
        }

        //Check that it's a valid major
        int validMajor = -1;
        String[] validMajors ={"CS", "IT", "BA", "EE", "ME"};
        for(int i = 0; i<validMajors.length; i++){
            if(validMajors[i].equals(major.toUpperCase())){
                validMajor=i;
            }
        }
        if(validMajor==-1){
            System.out.println("Invalid major name");
            return;
        }

        //We have name, major. Since remove requires no more tokens, deal with it first.
        if(cmd.equals("R")){
            System.out.println("We're in remove.");
            /*
            //WE NEED CONSTRUCTOR WITHOUT CREDITS FOR REMOVE
            Student newStudent = new Student(name,major);
            roster.remove(newStudent);

             */
            return;
        }

        //Next comes S,F, AT, AR, AN. Gotta check the next token for them.
        if(cmd.equals("S")){
            System.out.println("We're in set study abroad");
            //Check the last token is "true" or "false"
            String studyAbroadTest = st.nextToken();
            if(!(studyAbroadTest.equals("true")||studyAbroadTest.equals("false"))){
                System.out.println("You need to give a boolean for studyabroad, not a string");
                return;
            }
            boolean studyAbroad = Boolean.parseBoolean(studyAbroadTest);
            /*
            //WE NEED CONSTRUCTOR WITHOUT CREDITS FOR study abroad change
            //ALSO GOTTA CHECK THEYRE INTERNATIONAL
            Student newStudent = new Student(name, major, studyAbroad);
            newStudent.setStudyAbroad(studyAbroad);
            */

            return;
        }

        if(cmd.equals("F")){
            System.out.println("We're in give financial aid.");
            String finAid = st.nextToken();
            boolean validDouble = isNumber(finAid);
            if(!validDouble){
                System.out.println("Invalid financial aid amount - not a number");
                return;
            }
            double financialAid = Double.parseDouble(finAid);
            /*
            //NEED CONSTRUCTOR FOR NAME,MAJOR
            Student newStudent = new Student(name,major);
            //ALSO GOTTA CHECK THEYRE RESIDENT
            newStudent.setFinancialAid(financialAid);
             */
            return;
        }
        //Gotta check their credits for adding any type! So before I do that, I'll deal with my last command, T
        if(cmd.equals("T")){
            System.out.println("We're in pay tuition");
            //Check it's a valid payment amount
            String paymentAmountString = st.nextToken();
            boolean isNumber = isNumber(paymentAmountString);
            if(!isNumber){
                System.out.println("Amount of payment isn't a number!");
            }
            double paymentAmount = Double.parseDouble(paymentAmountString);

            //Check the date is valid
            String dateOfPaymentString = st.nextToken();
            Date dateOfPayment = new Date(dateOfPaymentString);
            if(!dateOfPayment.isValid()){
                System.out.println("Invalid payment date!");
            }
            /*
            //Send the payment
            Student newStudent = new Student(name, major);
            roster.setPayment(newStudent, paymentAmount, dateOfPayment);
             */
            return;
        }

        //Now can go onto adds
        String creditsString = st.nextToken();
        boolean isNumber = isNumber(creditsString);
        if (!isNumber) {
            System.out.println("Number of credits isn't a number!");
            return;
        }
        int credits = Integer.parseInt(creditsString);
        if (credits <= 3 || credits > 26) {
            System.out.println("Invalid credit amount");
            return;
        }
        if(cmd.equals("AN")||cmd.equals("AR")) {
            if(cmd.equals("AN")){
                System.out.println("We're adding non resident.");
                /*
                NonResident newStudent = new NonResident(name,major,credits);
                addStudent(roster, newStudent)

                 */
                return;
            }
            else if(cmd.equals("AR")){
                System.out.println("We're adding resident.");
                int financialAid = 0;
                /*
                Resident newStudent = new Resident(name,major,credits,0);
                addStudent(roster, newStudent);

                 */
            }
        }

        //Last comes AI, AT with 5 tokens. For both, we've already got credits.
        // Now just need state or studyabroad
        if(cmd.equals("AT")){
            System.out.println("We're adding tristate.");
            String state = st.nextToken();
            state=state.toUpperCase();
            if(!(state.equals("NY")||state.equals("CT"))){
                System.out.println("Trying to add tristate but not from NY or CT. Invalid!");
                return;
            }
            /*
            TriState newStudent = new TriState(name, major, credits, state);
            addStudent(roster, newStudent);

             */
            return;
        }
        if(cmd.equals("AI")){
            System.out.println("We're adding international.");
            String studyAbroadTest = st.nextToken();
            if(!(studyAbroadTest.equals("true")||studyAbroadTest.equals("false"))){
                System.out.println("You need to give a boolean for studyabroad, not a string");
                return;
            }
            boolean studyAbroad = Boolean.parseBoolean(studyAbroadTest);
            /*
            International newStudent = new International(name, major, credits, studyAbroad);
            addStudent(roster, newStudent);

             */
            return;
        }
    }
}
