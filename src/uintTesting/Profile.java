package uintTesting;

public class Profile {

    private boolean fullTime;
    private String name;
    private Major major;

    public Profile(){
    }

    public Profile(String name, String major, boolean fullTime){
        this.fullTime = fullTime;
        this.name = name;
        this.major = Major.valueOf(major.toUpperCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile newProfile = (Profile) obj;
            if (newProfile.name.equals(this.name) && newProfile.major.equals(this.major)) {
                return true;
            }
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
