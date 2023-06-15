package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.Menu;
import objects.Customer;
import objects.Order;
import objects.Products;
import tools.MyUtil;

public class ManagementSystem {

    private static final String FORMAT_DATE = "dd/MM/yyyy";
    private static final String PRODUCTS_FILE = "products.txt";
    private static final String CUSTOMERS_FILE = "customers.txt";
    private static final String ORDERS_FILE = "orders.txt";
    private ArrayList<Products> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);

    public ManagementSystem() {
        products = loadProducts();
        customers = loadCustomers();
        try {
            orders = loadOrders();
        } catch (ParseException ex) {
            Logger.getLogger(ManagementSystem.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public final ArrayList<Products> loadProducts() {
        products.clear();
        try {
            File f = new File(PRODUCTS_FILE);
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;

            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");

                String id = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                String unit = stk.nextToken().trim();
                String origin = stk.nextToken().trim();
                float price = Float.parseFloat(stk.nextToken());
                Products product = new Products(id, name, unit, origin, price);
                products.add(product);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
        return products;
    }

    public final ArrayList<Customer> loadCustomers() {
        customers.clear();
        try {
            File f = new File(CUSTOMERS_FILE);
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;

            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");

                String id = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                String address = stk.nextToken().trim();
                String phone = stk.nextToken().trim();

                Customer customer = new Customer(id, name, address, phone);
                customers.add(customer);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
        return customers;
    }

    public final ArrayList<Order> loadOrders() throws ParseException {
        orders.clear();
        try {
            File f = new File(ORDERS_FILE);
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;

            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");

                String id = stk.nextToken().toUpperCase().trim();
                String customerId = stk.nextToken().toUpperCase().trim();
                String productId = stk.nextToken().toUpperCase().trim();
                int quantity = Integer.parseInt(stk.nextToken().trim());
                String dateString = stk.nextToken().trim();
                Date date = null;
                try {
                    date = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    System.out.println("Error");
                }
                String statusString = stk.nextToken().trim();
                boolean status;
                status = statusString.equals("true");
                Order order = new Order(id, customerId, productId, quantity, date, status);
                orders.add(order);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Error loading order: " + e.getMessage());
        }
        return orders;
    }

    public void showProducts() {
        System.out.println("The list of Products: ");
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%-8s| %-25s| %-20s| %-25s| %-15.3f\n",
                    products.get(i).getProID(), products.get(i).getProName(), products.get(i).getProUnit(),
                    products.get(i).getProOrigin(), products.get(i).getPrice());
        }     
    }

    public void showCustomers() {
        System.out.println("The list of Customers: ");
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("%-8s| %-18s| %-13s |%-15s\n",
                    customers.get(i).getCusID(), customers.get(i).getCusName(),
                    customers.get(i).getCusAddress(), customers.get(i).getCusPhone());
        }
        System.out.println("");
    }

    public Customer findCus(String ID) {
        for (Customer c : customers) {
            if (c.getCusID().equals(ID)) {
                return c;
            }
        }
        return null;
    }

    public void searchCus() {

        String cusID = MyUtil.inputPattern("Input the Customer's ID", "^C\\d{3}$", "Customer's ID: “Cxxx”, which x is digits");
        if (findCus(cusID) == null) {
            System.out.println("Do not find that Customer !!");
        } else {
            System.out.println(findCus(cusID).toString());

        }
    }

    public void addCustomer() {
        String cusID;
        do {
            cusID = MyUtil.inputPattern("Input the Customer's ID", "^C\\d{3}$", "Customer's ID: “Cxxx”, which x is digits");
            if (findCus(cusID) != null) {
                System.out.println("Duplicate ID. Try again!!");
            } else {
                break;
            }
        } while (true);
        String cusName = MyUtil.inputNonBlankStr("Input the Customer's name");
        String cusAdd = MyUtil.inputNonBlankStr("Input the Customer's address");
        String cusPhone = MyUtil.inputPattern("Input the Customer's phone", "\\d{10,12}", "Customer's phone which has length from 10 to 12 characters");
        Customer cs = new Customer(cusID, cusName, cusAdd, cusPhone);
        customers.add(cs);
        System.out.println("The Customer add completed ");
    }

    public void updateCustomer() {
        Customer csUp;
        int count = 0;
        String cusID = MyUtil.inputPattern("Input the Customer's ID", "^C\\d{3}$", "Customer's ID: “Cxxx”, which x is digits");
        if (findCus(cusID) == null) {
            System.out.println("Customer does not exist");
            System.out.println("Update fail!!!!");
            return;
        } else {
            csUp = findCus(cusID);
        }
        System.out.println("New customer information - Enter for skipping");
        for (int i = 0; i < customers.size(); i++) {
            if (csUp.equals(customers.get(i))) {
                System.out.print("Input the Customer's name: ");
                String cusName = MyUtil.sc.nextLine().toUpperCase().trim();
                if (!cusName.isEmpty()) {
                    count++;
                    csUp.setCusName(cusName);
                }

                System.out.print("Input the Customer's address: ");
                String cusAdd = MyUtil.sc.nextLine().toUpperCase().trim();
                if (!cusAdd.isEmpty()) {
                    count++;
                    csUp.setCusAddress(cusAdd);
                }

                System.out.print("Input the Customer's phone: ");
                String cusPhone = MyUtil.sc.nextLine().trim();
                if (!cusPhone.isEmpty()) {
                    count++;
                    csUp.setCusPhone(cusPhone);
                }

                if (count == 0) {
                    System.out.println("Update fail!!!!");
                } else {
                    System.out.println("The information is updated");
                    System.out.println(customers.get(i).output());
                }
                break;
            }
        }
    }

    public void saveCusFromFile() {
        try {
            FileWriter fw = new FileWriter(CUSTOMERS_FILE);
            PrintWriter pw = new PrintWriter(fw);

            customers.forEach((obj) -> {
                pw.println(obj.toString());
            });

            pw.close();
            fw.close();
            System.out.println("Customer data saved to file: " + CUSTOMERS_FILE);
        } catch (IOException e) {
            System.out.println("Error saving customer data to file: " + CUSTOMERS_FILE);
        }

    }

    public void listAllOrders(ArrayList<Order> order) {
        if (order.isEmpty()) {
            System.out.println("Empty!");
            return;
        }
        
        ArrayList<Order> sortedOrders = order;
        // load cus Name
        for (int i = 0; i < order.size(); i++) {
            for (int j = 0; j < customers.size(); j++) {
                if (orders.get(i).getCustomerID().equals(customers.get(j).getCusID())) {
                    sortedOrders.get(i).setCustomerName(customers.get(j).getCusName());
                }
            }
        }
        Comparator<Order> e = new Comparator<Order>() {
            @Override
            public int compare(Order t, Order t1) {
                return t.getCustomerName().compareToIgnoreCase(t1.getCustomerName());
            }
        };
        
        Collections.sort(sortedOrders, e);
        System.out.println("The list of Order: ");
        for (int i = 0; i < order.size(); i++) {
            System.out.printf("%-7s| %-7s| %-7s| %-5s| %-15s| %-10s\n", order.get(i).getOrderID(), order.get(i).getCustomerID(),
                    order.get(i).getProductID(), order.get(i).getOrderQuantity(),
                    dateFormat.format(order.get(i).getOrderDate()), order.get(i).getStatus());

        }
        System.out.println("");
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void listPendingOrders() {
        for (Order o : orders) {
            if (o.getStatus() == false) {
                System.out.printf("%-7s| %-7s| %-7s| %-5s| %-15s| %-10s\n", o.getOrderID(), o.getCustomerID(),
                        o.getProductID(), o.getOrderQuantity(), dateFormat.format(o.getOrderDate()), o.getStatus());
            }
        }
    }

    private Order findOrder(String ID) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID().equals(ID)) {
                return orders.get(i);
            }
        }
        return null;
    }

    public void addOrder() {
        boolean kt;
        do {
            String orderID;
            do {
                orderID = MyUtil.inputPattern("Input the Order's ID", "^D\\d{3}$", "Order's ID: “Dxxx”, which x is digits");
                if (findOrder(orderID) != null) {
                    System.out.println("Duplicate ID. Try again!!");
                } else {
                    break;
                }
            } while (true);
            Menu subMenu = new Menu(customers.size());
            for (int i = 0; i < customers.size(); i++) {
                subMenu.add(customers.get(i).output());
            }
            int choice = subMenu.showMenu();
            String cusID = customers.get(choice - 1).getCusID();

            Menu subMenu2 = new Menu(products.size());
            for (int i = 0; i < products.size(); i++) {
                subMenu2.add(products.get(i).output());
            }
            choice = subMenu2.showMenu();
            String proID = products.get(choice - 1).getProID();

            int quantity = MyUtil.inputInt("Enter order quantity", 0);
            Date date = MyUtil.inputMDY("Enter order Date", "Input month / day / year !!!");
            boolean status;
            String tmp = MyUtil.inputPattern("Enter status(true or false)", "^(TRUE|FALSE)?$", "Just(true or false)");
            status = tmp.equals("TRUE");
            Order order = new Order(orderID, cusID, proID, quantity, date, status);
            orders.add(order);

            System.out.println("\n Do you wan to creating new order ??");
            kt = MyUtil.checkBool("User choose");
        } while (kt);
        System.out.println("The Customer add completed ");
    }

    public void updateOrder() {
        Order orUp;
        int count = 0;
        String orderID = MyUtil.inputPattern("Input the Order's ID", "^D\\d{3}$", "Order's ID: “Dxxx”, which x is digits");
        if (findOrder(orderID) == null) {
            System.out.println("Order does not exist");
            System.out.println("Update fail!!");
            return;
        } else {
            orUp = findOrder(orderID);
        }
        System.out.println("New customer information - Enter for skipping");
        for (int i = 0; i < orders.size(); i++) {
            if (orUp.equals(orders.get(i))) {
                System.out.print("Input the Customer's ID: ");
                String cusID = MyUtil.sc.nextLine().toUpperCase().trim();
                if (!cusID.isEmpty()) {
                    count++;
                    orUp.setCustomerID(cusID);
                }

                System.out.print("Input the Product's ID: ");
                String proID = MyUtil.sc.nextLine().toUpperCase().trim();
                if (!proID.isEmpty()) {
                    count++;
                    orUp.setProductID(proID);
                }

                System.out.print("Input the Order's quantity: ");
                String quantity = MyUtil.sc.nextLine().trim();
                if (!quantity.isEmpty()) {
                    count++;
                    orUp.setOrderQuantity(Integer.parseInt(quantity));
                }

                System.out.print("Input the Order's date m/d/y: ");
                String dateTmp = MyUtil.sc.nextLine().trim();
                if (!dateTmp.isEmpty()) {
                    count++;
                    try {
                        Date date = dateFormat.parse(dateTmp);
                        orUp.setOrderDate(date);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                System.out.print("Input the Order's status: ");
                String statusTmp = MyUtil.sc.nextLine().toUpperCase().trim();
                if (!statusTmp.isEmpty()) {
                    count++;
                    boolean status = statusTmp.equals("TRUE");
                    orUp.setStatus(status);
                }

                if (count == 0) {
                    System.out.println("Update fail!!!!");
                } else {
                    System.out.println("The information is updated");
                    System.out.println(orders.get(i).toString());
                }
                break;
            }
        }
    }

    public void deleteOrder() {
        String orderID = MyUtil.inputPattern("Input the Order's ID", "^D\\d{3}$", "Order's ID: “Dxxx”, which x is digits");
        if (findOrder(orderID) == null) {
            System.out.println("Order does not exist");
            System.out.println("Delete fail!!");
            return;
        }
        int indexRemove = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID().equals(orderID)) {
                indexRemove = i;
                break;
            }
        }
        orders.remove(indexRemove);
        System.out.println("Order deleted successfully.");
    }

    public void saveOrdersToFile() throws IOException {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(ORDERS_FILE);
            pw = new PrintWriter(fw);

            for (Order c : orders) {
                pw.println(c.toString());
            }
            System.out.println("Order data saved to file: " + ORDERS_FILE);
        } catch (IOException e) {
            System.out.println("Error saving Order data to file: " + ORDERS_FILE);
        } finally {
            pw.close();
            fw.close();
        }
    }

}
