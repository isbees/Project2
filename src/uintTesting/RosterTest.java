package uintTesting;

import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    //Able to add to empty, add to 1, add to 2 for tristate, resident, nonresident, and international
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

        //Test 5 Make a roster adding International, TriState, NonResident, and Resident, showing roster can hold all
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

        //Test 6 - can't add same student twice
        Roster test6 = new Roster();
        assertTrue(test6.add(resStudent));
        assertFalse(test6.add(resStudent));
    }

    //Able remove from 2 or 1, but not from empty for tristate, resident, nonresident, and international
    @Test
    public void remove() {
        //Making usable international, nonresident, tristate, resident, and student.
        Profile interProfile = new Profile("John Adams", "CS", true);
        International interStudent = new International(interProfile, 14);
        Profile triProfile = new Profile("Mark Sams", "IT", true);
        TriState triStudent = new TriState(triProfile, 14);
        Profile nonResProfile = new Profile("Paul Jeff", "CS", true);
        NonResident nonResStudent = new NonResident(nonResProfile, 14);
        Profile resProfile = new Profile("Andy Can", "CS", true);
        NonResident resStudent = new NonResident(resProfile, 14);
        Student testStudent = new Student("Landon Ades", "CS", 15);


        //Test 1 - Unable to remove any type of student from an empty roster
        Roster test1 = new Roster();
        assertFalse(test1.remove(interStudent));
        assertFalse(test1.remove(triStudent));
        assertFalse(test1.remove(nonResStudent));
        assertFalse(test1.remove(resStudent));
        assertFalse(test1.remove(testStudent));

        //Test 2 - Unable to remove a student not in the roster, can remove the student in the roster
        Roster test2 = new Roster();
        test2.add(testStudent);
        assertFalse(test2.remove(interStudent));
        assertFalse(test2.remove(triStudent));
        assertFalse(test2.remove(nonResStudent));
        assertFalse(test2.remove(resStudent));
        assertTrue(test2.remove(testStudent));

        //Test 3 - Can remove any type of student from the roster if they're in there - resStudent
        Roster test3 = new Roster();
        test3.add(resStudent);
        assertFalse(test3.remove(interStudent));
        assertFalse(test3.remove(triStudent));
        assertFalse(test3.remove(nonResStudent));
        assertTrue(test3.remove(resStudent));
        assertFalse(test3.remove(testStudent));


        //Test 4 - Can remove any type of student from the roster if they're in there - nonResStudent
        Roster test4 = new Roster();
        test4.add(nonResStudent);
        assertFalse(test4.remove(interStudent));
        assertFalse(test4.remove(triStudent));
        assertTrue(test4.remove(nonResStudent));
        assertFalse(test4.remove(resStudent));
        assertFalse(test4.remove(testStudent));


        //Test 5 - Can remove any type of student from the roster if they're in there - triStudent
        Roster test5 = new Roster();
        test5.add(triStudent);
        assertFalse(test5.remove(interStudent));
        assertTrue(test5.remove(triStudent));
        assertFalse(test5.remove(nonResStudent));
        assertFalse(test5.remove(resStudent));
        assertFalse(test5.remove(testStudent));
    }
}