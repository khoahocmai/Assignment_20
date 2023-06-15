package objects;

public class Customer {

    public String cusID, cusName, cusAddress, cusPhone;

    public Customer() {
    }

    public Customer(String cusID, String cusName, String cusAddress, String cusPhone) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.cusPhone = cusPhone;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }  
    
    @Override
    public String toString() {
        return cusID + "," + cusName + "," + cusAddress + "," + cusPhone;
    }
    
    public String output(){
        String s = String.format("%-5s| %-18s| %-15s| %-15s",this.cusID, this.cusName, this.cusAddress, this.cusPhone);
        return s;
    }


}
