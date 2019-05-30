package MODEL;

public class Customer {

    private String id;
    private String name;
    private String email;
    private String type;
    private String address;
    private String status;

    public Customer() {
        this.id = "";
        this.name = "";
        this.email = "";
        this.type = "";
        this.address = "";
        this.status = "";
    }

    public Customer(String id, String name, String email, String type, String address, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
        this.address = address;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
