package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {

    private final double withdrawalLimit;

    public CheckingAccount(String accountNumber,
                           String ownerName,
                           double balance,
                           double withdrawalLimit) {

        super(accountNumber, ownerName, balance);

        if (!Double.isFinite(withdrawalLimit)
                || withdrawalLimit <= 0) {
            throw new IllegalArgumentException(
                "출금 한도는 0보다 커야 합니다."
            );
        }

        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientBalanceException {

        validateAmount(amount);

        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException(
                "출금 한도를 초과했습니다. 한도: "
                + withdrawalLimit + "원"
            );
        }

        super.withdraw(amount);
    }

    @Override
    public String toString() {
        return super.toString()
            + String.format(
                ", 출금 한도: %.1f원", withdrawalLimit
            );
    }
}