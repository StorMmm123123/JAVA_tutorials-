package mylab.bank.control;

import mylab.bank.entity.Account;
import mylab.bank.entity.Bank;
import mylab.bank.entity.SavingsAccount;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class BankDemo {

    public static void main(String[] args) {

        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");

        SavingsAccount savings =
            bank.createSavingsAccount("홍길동", 10000, 0.03);

        bank.createCheckingAccount("김철수", 20000, 5000);
        bank.createSavingsAccount("이영희", 30000, 0.02);

        bank.displayAllAccounts();

        testDepositWithdraw(bank);
        testInterest(savings);
        testTransfer(bank);

        bank.displayAllAccounts();
        testExceptions(bank);
    }

    private static void testDepositWithdraw(Bank bank) {

        System.out.println("\n=== 입금/출금 테스트 ===");

        try {
            bank.deposit("AC1000", 5000);
            bank.withdraw("AC1001", 3000);

        } catch (AccountNotFoundException
                 | InsufficientBalanceException e) {

            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    private static void testInterest(SavingsAccount savings) {

        System.out.println("\n=== 이자 적용 테스트 ===");
        savings.applyInterest();
    }

    private static void testTransfer(Bank bank) {

        System.out.println("\n=== 계좌 이체 테스트 ===");

        try {
            bank.transfer("AC1002", "AC1001", 5000);

        } catch (AccountNotFoundException
                 | InsufficientBalanceException e) {

            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    private static void testExceptions(Bank bank) {

        System.out.println("\n=== 예외 처리 테스트 ===");

        tryWithdrawal(bank, "AC1001", 6000);
        tryWithdrawal(bank, "AC1001", 7000);
        tryWithdrawal(bank, "AC1000", 20000);

        try {
            Account account = bank.findAccount("AC9999");
            System.out.println(account);

        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    private static void tryWithdrawal(
            Bank bank, String accountNumber, double amount) {

        try {
            bank.withdraw(accountNumber, amount);

        } catch (WithdrawalLimitExceededException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}