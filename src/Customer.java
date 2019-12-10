import javafx.beans.property.SimpleStringProperty;
import java.util.List;
import java.util.ArrayList;

public class Customer extends User {
    private final SimpleStringProperty username;
    private final SimpleStringProperty nationality;
    private List<String> preferences;

    public Customer() {
        super();
        this.username = new SimpleStringProperty("");
        this.nationality = new SimpleStringProperty("");
        preferences = new ArrayList<String>();
    }

    public Customer(String name, String surname, String email, String password, String role,
                    String username, String nationality, List<String> preferences) {
        super(name, surname, email, password, role);
        this.username = new SimpleStringProperty(username);
        this.nationality = new SimpleStringProperty(nationality);
        this.preferences = preferences;
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getNationality() {
        return nationality.get();
    }

    public SimpleStringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }
}
