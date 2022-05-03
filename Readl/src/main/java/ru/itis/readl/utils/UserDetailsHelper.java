package ru.itis.readl.utils;

import ru.itis.readl.models.Account;
import ru.itis.readl.security.details.AccountUserDetails;

public class UserDetailsHelper {

    public static Account getAccount(AccountUserDetails userDetails){
        if (userDetails == null){

            return null;
        }else {
            return userDetails.getAccount();
        }
    }
}
