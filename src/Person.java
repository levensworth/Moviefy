import java.util.Objects;

public abstract class Person {
    private long id;
    private String firstName;
    private String lastName;
    private static long lastId = 0L;

    public Person(String firstName, String lastName) {
        this.id = lastId++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj.getClass() == this.getClass()){
            return this.id == ((Person)obj).getId();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}