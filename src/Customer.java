import javafx.beans.property.SimpleStringProperty;
import java.util.List;
import java.util.ArrayList;

public class Customer extends User {
    private final SimpleStringProperty username;
    private List<String> preferences;

    public Customer() {
        super();
        this.username = new SimpleStringProperty("");
        preferences = new ArrayList<String>();
    }

    public Customer(String name, String surname, String email, String password, String username, List<String> preferences) {
        super(name, surname, email, password, "customer");
        this.username = new SimpleStringProperty(username);
        this.preferences = preferences;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }
}
