package pl.kuziow.vetclinic.response;

public class CustomerREST {
    private String customerId;
    private String digitID;
    private String name;
    private String lastName;
    private String email;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDigitID() {
        return digitID;
    }

    public void setDigitID(String digitID) {
        this.digitID = digitID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
