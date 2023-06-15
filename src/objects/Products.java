
package objects;

public class Products {
    
    String proID, proName, proUnit, proOrigin;
    float price;

    public Products() {
    }   

    public Products(String proID, String proName, String proUnit, String proOrigin, float price) {
        this.proID = proID;
        this.proName = proName;
        this.proUnit = proUnit;
        this.proOrigin = proOrigin;
        this.price = price;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProUnit() {
        return proUnit;
    }

    public void setProUnit(String proUnit) {
        this.proUnit = proUnit;
    }

    public String getProOrigin() {
        return proOrigin;
    }

    public void setProOrigin(String proOrigin) {
        this.proOrigin = proOrigin;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }       
    
    @Override
    public String toString() {
        return  String.format("%s,%s,%s,%s,%f", proID,proName,proUnit,proOrigin,price);
    }
    
    public String output(){
        String s = String.format("%-5s| %-23s| %-20s| %-23s| %-15.3f",this.proID, this.proName, this.proUnit, this.proOrigin, this.price);
        return s;
    }
}
