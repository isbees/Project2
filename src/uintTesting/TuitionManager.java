package uintTesting;
//test cases: https://drive.google.com/file/d/1GGFrN0Pq54FvUzb8OBUEzgtPgOXlaSJ3/view
//Notes:
//Potential errors:

    //number format exception error (pass string to int)
    //no such element exception (scanning something which doesnt exist)
    //Input mismatch exception (put in string read into int)
    //invalid credit hours
    //invalid major code
    //invalid state code
    //invalid input

//Valid inputs: (major can be upper or lower case as with state)

    //Add: (check that credits >=3 and <=26 generally, then more specific)
        // international: cmd, name, major, credits, studyabroad
            //AI, name, major, credits, studyabroad (if study abroad true, credit hours gotta be <=12)
            //-> cont (gotta be full time >=12 if studyabroad is false)
        // tristate
            //AT, name, major, credits, state (check if nyc, connecticut)
        // non resident
            //AN, name, major, credits
        // resident
            //AR, name, major, credits

    //Remove
        //R, name, major

    //Calculate Tuition dues for all roster students
        //C

    //Study Abroad
        //S, name, maj, true/false

    //Finaid
        //F, name, maj, $given (0<=x<=10k)

    //Paying tuition
        //T, name, maj, $pay, date
public class TuitionManager {
    public static void run(){

    }


}
