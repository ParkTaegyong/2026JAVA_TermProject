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
            // 나중에 '금액 부족' 처리 로직을 추가할 수도 있습니다.
            System.out.println("Error: Insufficient cash! Transaction cancelled.");
        }
    }
}

