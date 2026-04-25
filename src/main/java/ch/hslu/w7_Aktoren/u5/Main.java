package ch.hslu.w7_Aktoren.u5;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    final int nofAccounts = 10000000;
    List<Account> accounts = ThreadLocalRandom.current()
            .ints(0, 1200)
            .limit(nofAccounts)
            .mapToObj(Account::new)
            .toList();
}
