package DB;

import javafx.beans.property.SimpleStringProperty;

public class Books {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty amout;
    private SimpleStringProperty auth;
    private SimpleStringProperty cat;

    public Books(String id, String name, String amout, String auth, String cat) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.amout = new SimpleStringProperty(amout);
        this.auth = new SimpleStringProperty(auth);
        this.cat = new SimpleStringProperty(cat);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getAmout() {
        return amout.get();
    }

    public SimpleStringProperty amoutProperty() {
        return amout;
    }

    public String getAuth() {
        return auth.get();
    }

    public SimpleStringProperty authProperty() {
        return auth;
    }

    public String getCat() {
        return cat.get();
    }

    public SimpleStringProperty catProperty() {
        return cat;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAmout(String amout) {
        this.amout.set(amout);
    }

    public void setAuth(String auth) {
        this.auth.set(auth);
    }

    public void setCat(String cat) {
        this.cat.set(cat);
    }
}
