package ch.hslu.w7_Aktoren.u5;

class Account {
    long balance;
    Account(int balance) {
        this.balance = balance;
    }
}

enum BalanceCategory {
    NEGATIVE,
    EMPTY,
    LOW,
    MEDIUM,
    HIGH,
    RICH
}