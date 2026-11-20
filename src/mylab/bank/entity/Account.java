package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public class Account {

    private final String accountNumber;
    private final String ownerName;
    private double balance;

    public Account(String accountNumber,
                   String ownerName, double balance) {
//에러 자꾸남 에서드 문제 있음 찾아보기 
    	// .... --> isEmpty 가 올바른 메서드였음 
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("계좌번호를 입력해야 합니다.");
        }
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("소유자 이름을 입력해야 합니다.");
        }
        if (balance < 0 || !Double.isFinite(balance)) {
            throw new IllegalArgumentException("초기 잔액은 0원 이상이어야 합니다.");
        }

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        validateAmount(amount);
        balance += amount;

        System.out.printf(
            "%.1f원이 입금되었습니다. 현재 잔액: %.1f원%n",
            amount, balance
        );
    }

    public void withdraw(double amount)
            throws InsufficientBalanceException {

        validateAmount(amount);

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "잔액이 부족합니다. 현재 잔액: " + balance + "원"
            );
        }

        balance -= amount;

        System.out.printf(
            "%.1f원이 출금되었습니다. 현재 잔액: %.1f원%n",
            amount, balance
        );
    }

    protected void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                "금액은 0보다 커야 합니다."
            );
        }
    }
//toString 
    @Override
    public String toString() {
        return String.format(
            "계좌번호: %s, 소유자: %s, 잔액: %.1f원",
            accountNumber, ownerName, balance
        );
    }
}