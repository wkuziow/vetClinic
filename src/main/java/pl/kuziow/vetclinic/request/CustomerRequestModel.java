package pl.kuziow.vetclinic.request;

public class CustomerRequestModel {

    private String digitID;
    private String digitPIN;
    private String email;

    public String getDigitID() {
        return digitID;
    }

    public void setDigitID(String digitID) {
        this.digitID = digitID;
    }

    public String getDigitPIN() {
        return digitPIN;
    }

    public void setDigitPIN(String digitPIN) {
        this.digitPIN = digitPIN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
