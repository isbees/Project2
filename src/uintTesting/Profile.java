package uintTesting;

public class Profile {

    private boolean fullTime;
    private String name;
    private Major major;

    /**
     * empty constructor
     */
    public Profile() {
    }

    /**
     * constructor for testing since fullTime is omitted
     * @param name of the student
     * @param major of the student
     */
    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    /**
     * constructor with all variables
     * @param name of the student
     * @param major of the student
     * @param fullTime status of the student
     */
    public Profile(String name, Major major, boolean fullTime) {
        this.fullTime = fullTime;
        this.name = name;
        this.major = major;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile newProfile = (Profile) obj;
            return newProfile.name.equals(this.name) && newProfile.major.equals(this.major);
        }
        return false;
    }

    @Override
    public String toString() {
        return name + ":" + major;
    }

    public boolean getFullTime() {
        return fullTime;
    }

    public String getName() {
        return name;
    }

    public Major getMajor() {
        return major;
    }

}
