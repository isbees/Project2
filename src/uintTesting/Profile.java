package uintTesting;

public class Profile {

    private boolean fullTime;
    private String name;
    private Major major;

    public Profile(){
    }

    public Profile(String name, Major major){
        this.name = name;
        this.major = major;
    }

    public Profile(String name, Major major, boolean fullTime){
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
    public String toString(){
        return name + ":" + major;
    }

    public boolean getFullTime(){
        return fullTime;
    }

}
