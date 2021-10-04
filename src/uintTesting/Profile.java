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
    public String toString(){
        return name + ":" + major;
    }

    public boolean getFullTime(){
        return fullTime;
    }

}
