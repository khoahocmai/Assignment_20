package menu;

import tools.MyUtil;
import java.util.Scanner;

public class Menu {

    String[] arr;
    int n;

    public Menu(int size) {
        if (size < 0) {
            size = 10;
        }
        arr = new String[size];
    }

    public void add(String ope) {
        if (n < arr.length) {
            arr[n++] = ope;
        }
    }

    public int getChoice() {
        int choice = 0;
        if (n > 0) {
            System.out.println("\t\t    MAIN MENU");
            System.out.println("\t====================================");
            for (int i = 0; i < n; i++) {
                System.out.println("\t[" + (i + 1) + "] - " + arr[i]);
            }
            System.out.println("\t====================================");
            Scanner sc = new Scanner(System.in);
            choice = MyUtil.getChoice();
        }
        return choice;
    }
    
    public int showMenu() {
        int choice = 0;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                System.out.println((i + 1) + " - " + arr[i]);
            }
            Scanner sc = new Scanner(System.in);
            choice = MyUtil.getChoice();
        }
        return choice;
    }
}
