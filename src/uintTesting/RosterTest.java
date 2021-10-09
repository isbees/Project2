package uintTesting;

import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    //Able to add to empty, add to 1, add to 2 for tristate, resident, nonresident, and international
    //Also maybe test adding a few diff types together
    @Test
    public void add() {
        //Test 1 International: (.1->.3) -> Add to empty, 1, and 2.

        //Add to empty
        Profile IProfile1 = new Profile("John Adams", "CS", true);
        International test1Inter = new International(IProfile1,14);
        Roster test1Roster = new Roster();
        assertTrue(test1Roster.add(test1Inter));
        //Add to 1
        Profile IProfile2 = new Profile("Mark Sams", "IT", true);
        International test2Inter = new International(IProfile2,15);
        assertTrue(test1Roster.add(test2Inter));
        //Add to 2
        Profile IProfile3 = new Profile("Andy Can", "CS", true);
        International test3Inter = new International(IProfile3,16);
        assertTrue(test1Roster.add(test3Inter));

        //Test 2 Tristate: (.1->.3) -> Add to empty, 1, and 2.

        //Add to empty
        Profile TProfile1 = new Profile("John Adams", "CS", true);
        TriState test1Tri = new TriState(TProfile1,14);
        Roster test2Roster = new Roster();
        assertTrue(test2Roster.add(test1Tri));
        //Add to 1
        Profile TProfile2 = new Profile("Mark Sams", "IT", true);
        TriState test2Tri = new TriState(TProfile2,14);
        assertTrue(test2Roster.add(test2Tri));
        //Add to 2
        Profile TProfile3 = new Profile("Andy Can", "CS", true);
        TriState test3Tri = new TriState(TProfile3,14);
        assertTrue(test2Roster.add(test3Tri));


        //Test 3 NonResident: (.1->.3) -> Add to empty, 1, and 2.

        //Add to emptty
        Profile NRProfile1 = new Profile("John Adams", "CS", true);
        NonResident test1NR = new NonResident(NRProfile1,14);
        Roster test3Roster = new Roster();
        assertTrue(test3Roster.add(test1NR));
        //Add to 1
        Profile NRProfile2 = new Profile("Mark Sams", "IT", true);
        NonResident test2NR = new NonResident(NRProfile2,14);
        assertTrue(test3Roster.add(test2NR));
        //Add to 2
        Profile NRProfile3 = new Profile("Andy Can", "CS", true);
        NonResident test3NR = new NonResident(NRProfile3,14);
        assertTrue(test3Roster.add(test3NR));

        //Test 4 Resident: (.1->.3) -> Add to empty, 1, and 2.
        //Add to emptty
        Profile RProfile1 = new Profile("John Adams", "CS", true);
        Resident test1R = new Resident(RProfile1,14);
        Roster test4Roster = new Roster();
        assertTrue(test4Roster.add(test1R));
        //Add to 1
        Profile RProfile2 = new Profile("Mark Sams", "IT", true);
        Resident test2R = new Resident(RProfile2,14);
        assertTrue(test4Roster.add(test2R));
        //Add to 2
        Profile RProfile3 = new Profile("Andy Can", "CS", true);
        Resident test3R = new Resident(RProfile3,14);
        assertTrue(test2Roster.add(test3R));

        //Test 5 Make a roster adding International, TriState, NonResident, and Resident
        Roster test5Roster = new Roster();

        //Add international student
        Profile interProfile = new Profile("John Adams", "CS", true);
        International interStudent = new International(interProfile, 14);
        assertTrue(test5Roster.add(interStudent));

        //Add TriState student
        Profile triProfile = new Profile("Mark Sams", "IT", true);
        TriState triStudent = new TriState(triProfile, 14);
        assertTrue(test5Roster.add(triStudent));

        //Add NonResident student
        Profile nonResProfile = new Profile("Andy Can", "CS", true);
        NonResident nonResStudent = new NonResident(nonResProfile, 14);
        assertTrue(test5Roster.add(nonResStudent));

        //Add Resident student
        Profile resProfile = new Profile("Hughe Landough", "IT", true);
        Resident resStudent = new Resident(resProfile, 14);
        assertTrue(test5Roster.add(resStudent));

    }

    @Test
    public void remove() {
        //Unable to remove from empty

        //Able to remove from roster of 1

        //Able to remove from roster of 2

    }
}