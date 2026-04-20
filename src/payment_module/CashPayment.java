package payment_module;

import java.util.Scanner;

public class CashPayment implements IPayment {

    @Override
    public void pay(int amount) {
        Scanner sc = new Scanner(System.in);
        System.out.println("💵 [Cash Payment] Total amount to pay: " + amount + " won");

        System.out.print("Enter the amount of cash you are putting in:  > ");
        int cashReceived = Integer.parseInt(sc.nextLine());

        if (cashReceived >= amount) {
            int change = cashReceived - amount;
            System.out.println("Processing...");
            System.out.println("Successfully received " + cashReceived + " won.");
            System.out.println("Change: " + change + " won");
        } else {
            System.out.println("Error: Insufficient cash! Transaction cancelled.");
        }

    }

}

