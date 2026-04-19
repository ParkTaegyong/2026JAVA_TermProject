package payment_module;

public class CardPayment implements IPayment {
    @Override
    public void pay(int amount) {
        System.out.println("💳 [Card] Processing... " + amount + " won paid.");
    }
}
