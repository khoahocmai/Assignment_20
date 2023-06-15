package main;

import data.ManagementSystem;
import java.io.IOException;
import menu.Menu;
import tools.MyUtil;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static ManagementSystem MS = new ManagementSystem();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Menu menu = new Menu(13);
        menu.add("List all Products");
        menu.add("List all Customers");
        menu.add("Search a Customer");
        menu.add("Add a Customer");
        menu.add("Update a Customer");
        menu.add("Save Customers to the file");
        menu.add("List all Orders");
        menu.add("List all pending Orders");
        menu.add("Add an Order");
        menu.add("Update an Order's information");
        menu.add("Delete an Order's information");
        menu.add("Save Orders to file");
        menu.add("Quit");

        boolean changed = false, kt;
        int choice;
        do {
            System.out.println("\n\t\tORDER MANAGEMENT");
            choice = menu.getChoice();
            System.out.println();
            switch (choice) {
                case 1:
                    MS.showProducts();
                    break;
                case 2:
                    MS.showCustomers();
                    break;
                case 3:
                    MS.searchCus();
                    break;
                case 4:
                    MS.addCustomer();
                    changed = true;
                    break;
                case 5:
                    MS.updateCustomer();
                    changed = true;
                    break;
                case 6:
                    MS.saveCusFromFile();
                    break;
                case 7:
                    MS.listAllOrders(MS.getOrders());
                    break;
                case 8:
                    MS.listPendingOrders();
                    break;
                case 9:
                    MS.addOrder();
                    changed = true;
                    break;
                case 10:
                    MS.updateOrder();
                    changed = true;
                    break;
                case 11:
                    MS.deleteOrder();
                    changed = true;
                    break;
                case 12:
                {
                    try {
                        MS.saveOrdersToFile();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case 13:                   
                    break;
                default:
            }
            System.out.println("\nDo you want to go back menu?");
            kt = MyUtil.checkBool("User choose");
        } while (kt);
        if (changed) {
            System.out.println("Save all changed Y/N?: ");
            System.out.print("User choose: ");
            String response = sc.nextLine().toUpperCase();
            if (response.startsWith("Y")) {
                MS.saveCusFromFile();
                try {
                    MS.saveOrdersToFile();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println("\n\tThe program is finished !!");
    }
}
