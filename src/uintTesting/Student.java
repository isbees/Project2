package uintTesting;

public class Student{

    private Profile person;

    public Student() {

    }

    public Student(String name, String major, boolean fullTime) {
        person = new Profile(name,major,fullTime);
    }

    @Override
    public String toString() {
        return person.toString();  // will add more to this to string later. + ":" +
    }

    public void tuitionDue() {
        // does nothing
    }
}
