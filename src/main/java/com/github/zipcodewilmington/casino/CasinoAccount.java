package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.games.BlackJack.BlackJackPlayer;
import com.github.zipcodewilmington.casino.games.Person.Person;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class CasinoAccount {
    private final String accountName;
    private String accountPassword;
    private Integer accountBalance;
    private Person person;

    public CasinoAccount(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountBalance = 0;
        this.person=new Person(accountName, 5000);
    }

    public String getAccountName(){
        return accountName;
    }

    public String getAccountPassword(){
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Person getPerson() {
        return person;
    }
}
