import service.Controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Controller ct = new Controller();
        System.out.println("Welcome to Coffee Shop!!");
        int quit = 1;
        do {
            ct.showMenu();
            System.out.print("Please Select Number > ");
            int choice = Integer.parseInt(sc.nextLine()); // 엔터 버퍼 방지를 위해 nextLine 사용

            if (choice == 1 || choice == 2) {
                ct.selectMenu(choice); // 커피 또는 디저트 선택
            } else if (choice == 3) {
                if (ct.showCartList()) {
                    ct.toPay();
                }
            } else if (choice == 4) {
                System.out.println("Complete your order and proceed to the Payment page.");
                ct.toPay();
            } else if (choice == 0) {
                System.out.println("Exiting the system. Goodbye!");
                quit = 0; // 사용자가 직접 0을 눌렀을 때만 종료
            } else if(choice == 9){
                ct.showAdminMode();
            }
            else {
                System.out.println("Error : Wrong Input!!\n");
            }

        } while (quit == 1);

    }



}
