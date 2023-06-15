package objects;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Order {
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
    private static final String FORMAT_DATE = "dd/MM/yyyy";
    public String orderID, customerID, productID, customerName;
    public Date orderDate;
    public int orderQuantity;
    public boolean status;

    public Order() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Order(String orderID, String customerID, String productID, int orderQuantity, Date orderDate, boolean status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    } 

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return orderID + "," + customerID + "," + productID + "," + 
                orderQuantity + "," + dateFormat.format(orderDate) + "," + status;
    }


}
