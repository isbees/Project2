package uintTesting;

import java.util.StringTokenizer;
import java.util.Calendar;

/**
 * The albums.Date class holds a day, month, and year and can check if it's a valid date or represent it in String
 * formatting.
 *
 * @author Zachary Goldman
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Takes mm/dd/yyyy and creates a albums.Date object
     *
     * @param date in mm/dd/yyyy
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        String month = st.nextToken();
        this.month = Integer.parseInt(month);
        String day = st.nextToken();
        this.day = Integer.parseInt(day);
        String year = st.nextToken();
        this.year = Integer.parseInt(year);
    }

    /**
     * Create an object with the current date
     */
    public Date() {
        Calendar now = Calendar.getInstance();
        this.day = now.get(Calendar.DAY_OF_MONTH);
        this.month = (int) now.get(Calendar.MONTH) + 1;
        this.year = now.get(Calendar.YEAR);
    }

    /**
     * Checks to see if the date is a valid date falling after 1980 up until today, given leap years
     *
     * @return valid, true if a valid date, false otherwise
     */
    public boolean isValid() {
        boolean valid = false;
        //Check the month is valid
        if (this.month > 12 || this.month < 1) {
            return valid;
        }

        //Check the day is valid
        Boolean dayValid = false;
        if (this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7 || this.month == 8 || this.month == 10 || this.month == 12) {
            if (this.day <= 31 && this.day >= 1) {
                dayValid = true;
            }
        } else if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) {
            if (this.day <= 30 && this.day >= 1) {
                dayValid = true;
            }
        } else if (this.month == 2) {
            if (isLeapYear(this.year)) {
                if (this.day <= 29 && this.day >= 1) {
                    dayValid = true;
                }
            } else {
                if (this.day <= 28 && this.day >= 1) {
                    dayValid = true;
                }
            }
        }
        if (!dayValid) {
            return valid;
        }
        //Check the year is valid

        //Not too early or late (before 2021 or after 2021)
        Date now = new Date();
        if (this.year < 2021 || this.year > now.year) {
            return valid;
        }
        if (this.year == now.year) {
            if (this.month > now.month) {
                return valid;
            }
            //If it's October 2021 but equal or after our day, invalid
            if (this.month == now.month) {
                if (this.day >= now.day) {
                    return valid;
                }
            }
        }
        valid = true;
        return valid;
    }


    /**
     * Compares 2 albums, returns 1 if the date of the album's releaseDate is later than this album, 0 if the same releaseDate, -1 if earlier
     *
     * @param date representing the date of the album we're comparing to this album
     * @return int that is 1 if later, 0 if the same day, -1 if earlier.
     */
    @Override
    public int compareTo(Date date) {
        int theirDay = date.day;
        int theirMonth = date.month;
        int theirYear = date.year;
        if (theirYear >= this.year) {
            if (theirYear > this.year) {
                return 1;
            }
            //Same year
            else if (theirYear == this.year) {
                if (theirMonth > this.month) {
                    return 1;
                }
                //same month
                else if (theirMonth == this.month) {
                    if (theirDay > this.day) {
                        return 1;
                    }
                    //same day
                    else if (theirDay == this.day) {
                        return 0;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Returns true if a leap year, false if not.
     *
     * @param year in question
     * @return true if leap year, false if not.
     */
    private boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }
        if (year % 400 == 0) {
            return true;
        }
        return false;
    }

    /**
     * testBed main used to test isValid() method.
     *
     * @param args from command line, not given in our class.
     */
    public static void main(String[] args) {
        //Tests of isValid()
        int allPassedCases = 0;
        //Case 1 - Create an instance of albums.Date with month >12
        System.out.print("Case 1: ");
        Date testDateOne = new Date("13/1/2018");
        boolean valid = testDateOne.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 2 - Create an instance of albums.Date with month as January and day <1
        System.out.print("Case 2: ");
        Date testDateTwo = new Date("0/1/2018");
        valid = testDateTwo.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 3 - Create an instance of albums.Date with month as January and day >31
        System.out.print("Case 3: ");
        Date testDateThree = new Date("1/40/2018");
        valid = testDateThree.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 4 - Create an instance of albums.Date with month as June and day >30
        System.out.print("Case 4: ");
        Date testDateFour = new Date("6/31/2018");
        valid = testDateFour.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }

        //Case 5 - Create an instance of albums.Date with month as June and day <1
        System.out.print("Case 5: ");
        Date testDateFive = new Date("6/0/2018");
        valid = testDateFive.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 6 - Create an instance of albums.Date of a leap year year and February 29
        System.out.print("Case 6: ");
        Date testDateSix = new Date("2/29/2000");
        valid = testDateSix.isValid();
        if (valid == true) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 7 - Create an instance of albums.Date of a non-leap year year and February 29
        System.out.print("Case 7: ");
        Date testDateSeven = new Date("2/29/1999");
        valid = testDateSeven.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 8 - Create an instance of albums.Date with year <1980
        System.out.print("Case 8: ");
        Date testDateEight = new Date("6/2/1979");
        valid = testDateEight.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 9 - instance of albums.Date with year >2021
        System.out.print("Case 9: ");
        Date testDateNine = new Date("6/2/2022");
        valid = testDateNine.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 10 - instance of albums.Date with year = 2021, month>9
        System.out.print("Case 10: ");
        Date testDateTen = new Date("10/1/2021");
        valid = testDateTen.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 11 - instance of albums.Date with year = 2021, month=9, day>current day (27 in our case)
        System.out.print("Case 11: ");
        Date testDateEleven = new Date("09/28/2021");
        valid = testDateEleven.isValid();
        if (valid == false) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 12 - instance of albums.Date with current date
        System.out.print("Case 12: ");
        Date testDateTwelve = new Date();
        valid = testDateTwelve.isValid();
        if (valid == true) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        //Case 13 - instance of albums.Date with valid date a day before current date testing current date
        System.out.print("Case 13: ");
        Date testDateThirteen = new Date("09/24/2021");
        valid = testDateThirteen.isValid();
        if (valid == true) {
            System.out.println("Passed.");
            allPassedCases++;
        } else {
            System.out.println("Failed.");
        }
        if (allPassedCases == 13) {
            System.out.println("All cases passed.");
        }
    }

    /**
     * Gets the day of the albums.Date in a String
     *
     * @return this.day
     */
    private String getDay() {
        return "" + this.day;
    }

    /**
     * Gets the month of the albums.Date in a String
     *
     * @return this.month
     */
    private String getMonth() {
        return "" + this.month;
    }

    /**
     * Gets the year of the albums.Date in a String
     *
     * @return this.year
     */
    private String getYear() {
        return "" + this.year;
    }

    /**
     * Gets the month/day/year of the albums.Date in a String
     *
     * @return dateInFormat of mm/dd/yyyy
     */
    public String getDate() {
        String dateInFormat = getMonth() + "/" + getDay() + "/" + getYear();

        if (month == 0 || day == 0 || year == 0) {
            dateInFormat = "--/--/--";
        }

        return dateInFormat;
    }
}