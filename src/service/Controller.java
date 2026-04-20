package service;

import module.Coffee;
import module.Dessert;
import payment_module.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Controller {
    Scanner sc = new Scanner(System.in);
    // Manage the coffee and dessert menus as a list
    private List<Coffee> coffeeList = new ArrayList<>();
    private List<Dessert> dessertList = new ArrayList<>();
    private List<Coffee> coffeeCartList = new ArrayList<>();
    private List<Dessert> dessertCartList = new ArrayList<>();
    // List of administrators
    private List<Coffee> soldCoffeeList = new ArrayList<>();
    private List<Dessert> soldDessertList = new ArrayList<>();
    private int totalSales = 0;

    public Controller() {
        // Menu Settings
        coffeeList.add(new Coffee("Americano", 3500, false));
        coffeeList.add(new Coffee("Coffee Latte", 4000, false));
        coffeeList.add(new Coffee("Mocha Coffee", 4500, false));
        dessertList.add(new Dessert("Honey Bread", 5000));
        dessertList.add(new Dessert("Bagel", 4000));
        dessertList.add(new Dessert("Macaron", 2000));
    }


    public void showMenu() {
        System.out.println();
        System.out.println("╔════════════ [ CAFE ] ═══════════╗");
        System.out.println("║  1. Coffee Menu                 ║");
        System.out.println("║  2. Dessert Menu                ║");
        System.out.println("║  3. View Cart                   ║");
        System.out.println("║  4. Checkout                    ║");
        System.out.println("║  9. Admin Mode                  ║");
        System.out.println("║  0. Exit                        ║");
        System.out.println("╚═════════════════════════════════╝" );

    }

    public void showCoffeeMenu() {
        System.out.println("╔════════════ [ COFFEE ] ═══════════╗");
        for (int i = 0; i < coffeeList.size(); i++) {
            String menuLine = (i + 1) + ". " + coffeeList.get(i).getName();
            System.out.printf("║  %-31s  ║\n", menuLine);
        }
        System.out.println("║  0. Back to the Menu              ║");
        System.out.println("╚═══════════════════════════════════╝");
    }

    public void showDessertMenu() {
        System.out.println("╔════════════ [ DESSERT ] ══════════╗");
        for (int i = 0; i < dessertList.size(); i++) {
            String menuLine = (i + 1) + ". " + dessertList.get(i).getName();
            System.out.printf("║  %-31s  ║\n", menuLine);
        }
        System.out.println("║  0. Back to the Menu              ║");
        System.out.println("╚═══════════════════════════════════╝");
    }


    public void selectMenu(int choice) {
        boolean stay = true;

        while (stay) {
            if (choice == 1) {
                showCoffeeMenu();
            } else {
                showDessertMenu();
            }

            System.out.print("Please select your order number > ");
            int num = safeInput();

            if (num == 0) {
                stay = false;
            } else {
                if (choice == 1 && num > 0 && num <= coffeeList.size()) {
                    // References the contents of the `coffeeList` associated with the variable of type
                    // `Coffee` based on the entered number
                    Coffee selected = coffeeList.get(num - 1);
                    System.out.println("Would you like some ice? [YES : 1] [NO : 2]");
                    boolean ice = (safeInput() == 1);
                    addToCoffeeCart(selected.getName(), selected.getPrice(), ice);
                } else if (choice == 2 && num > 0 && num <= dessertList.size()) {
                    // References the contents of the DessertList corresponding
                    // to the number entered into a variable of type Dessert
                    Dessert selected = dessertList.get(num - 1);
                    addToDessertCart(selected.getName(), selected.getPrice());
                }

                System.out.println("\nStay in this category and order more? [1: YES / 2: NO(Back to Main)]");
                if (safeInput() == 2) {
                    stay = false;
                }
            }
        }
    }

    public void addToCoffeeCart(String name, int price, boolean ice) {
        coffeeCartList.add(new Coffee(name, price, ice));
    }

    public void addToDessertCart(String name, int price) {
        dessertCartList.add(new Dessert(name, price));
    }

    public boolean showCartList() {
        System.out.println("\n---------- YOUR CART ----------");
        int totalPrice = 0;
        // 장바구니가 비어있는지 검사
        if (coffeeCartList.isEmpty() && dessertCartList.isEmpty()) {
            System.out.println("Your cart is empty.");
        }
        // 비어있지 않으면 목록 출력
        else {
            for (Coffee c : coffeeCartList) {
                System.out.println("[Coffee] " + c.getName() + " : " + c.getPrice() + " won");
                totalPrice += c.getPrice();
            }
            for (Dessert d : dessertCartList) {
                System.out.println("[Dessert] " + d.getName() + " : " + d.getPrice() + " won");
                totalPrice += d.getPrice();
            }
            System.out.println("-------------------------------");
            System.out.println("Total Price: " + totalPrice + " won");
        }
        System.out.println("-------------------------------");

        System.out.println("1. Proceed to Payment");
        System.out.println("2. Back to Main Menu");
        System.out.print("Select Number > ");

        int cartChoice = safeInput();

        if (cartChoice == 1) {
            return true;
        } else {
            return false;
        }
    }

    public int getTotal() {
        int total = 0;
        for (Coffee c : coffeeCartList)
            total += c.getPrice();
        for (Dessert d : dessertCartList)
            total += d.getPrice();
        return total;
    }

    public void toPay() {
        int total = getTotal();

        if (total <= 0) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("1. Card  2. Cash  0.Back to Menu");
        System.out.print("Select Payment Method > ");
        int payMethod = safeInput();
        if (payMethod > 0) {
            IPayment p = null;
            if (payMethod == 1) {
                p = new CardPayment();
            } else if(payMethod == 2){
                p = new CashPayment();
            }
            else{
                System.out.println("Wrong Input. Returning to menu");
                return;
            }
            p.pay(total);
            //Calculate total sales
            totalSales += total;
            soldCoffeeList.addAll(coffeeCartList);
            soldDessertList.addAll(dessertCartList);
            // Clear the list for the next guest
            coffeeCartList.clear();
            dessertCartList.clear();

            System.out.println("Payment completed. Cart has been cleared for the next customer.");
        }

    }

    public void showAdminMode() {
        System.out.println("\n========== ADMIN: SALES ANALYSIS ==========");
        // 매출이 없는 경우
        if (soldCoffeeList.isEmpty() && soldDessertList.isEmpty()) {
            System.out.println("No sales data available for today.");
        }
        // 매출이 있는 경우
        else {
            System.out.println("[Sold Items List]");
            for (Coffee c : soldCoffeeList) {
                System.out.println("- " + c.getName() + " (" + c.getPrice() + " won)");
            }
            for (Dessert d : soldDessertList) {
                System.out.println("- " + d.getName() + " (" + d.getPrice() + " won)");
            }
            System.out.println("-------------------------------------------");
            System.out.println("Total Sales Amount: " + totalSales + " won");
        }
        System.out.println("===========================================");
        System.out.println("Press Enter to return...");
        sc.nextLine();
    }

    public int safeInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("! Only numbers are allowed. Try again >  ");
            }
        }
    }
}





