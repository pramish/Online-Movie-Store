package MODEL;

import java.io.Serializable;

public class Staff implements Serializable {

    private String ID;
    private String email;
    private String name;
    private String position;
    private String address;

    public Staff() {
    }

    public Staff(String ID, String email, String name, String position, String address) {
        this.ID = ID;
        this.email = email;
        this.name = name;
        this.position = position;
        this.address = address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
