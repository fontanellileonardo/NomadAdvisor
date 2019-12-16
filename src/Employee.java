public class Employee extends User {

    public Employee() {
        super();
    }

    public Employee(String name, String surname, String email, String password) {
        super(name, surname, email, password, "employee");
    }
}
