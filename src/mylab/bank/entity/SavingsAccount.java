package mylab.bank.entity;

public class SavingsAccount extends Account {

    private final double interestRate;

    public SavingsAccount(String accountNumber,
                          String ownerName,
                          double balance,
                          double interestRate) {

        super(accountNumber, ownerName, balance);

        if (!Double.isFinite(interestRate)
                || interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException(
                "이자율은 0~1 사이여야 합니다."
            );
        }

        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate;

        if (interest > 0) {
            deposit(interest);
        }

        System.out.printf(
            "이자 %.1f원이 적용되었습니다. 현재 잔액: %.1f원%n",
            interest, getBalance()
        );
    }

    @Override
    public String toString() {
        return super.toString()
            + String.format(
                ", 이자율: %.1f%%", interestRate * 100
            );
    }
}