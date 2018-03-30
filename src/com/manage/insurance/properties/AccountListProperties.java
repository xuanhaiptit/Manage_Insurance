package com.manage.insurance.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.manage.insurance.constant.Constants;

/**
 * Class read data in file txt
 * 
 * @author lexuanhai
 *
 */
public class AccountListProperties {
    private static Map<String, String> accountList = new HashMap<String, String>();
    /**
     * Read data file account_list.txt
     */
    static {
        try {
            InputStream inputStream =
                    AccountListProperties.class.getClassLoader().getResourceAsStream(Constants.FILE_ACCOUNT_LOGIN);
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    line = line.trim();
                    int i = 0;
                    if (line != null && !line.isEmpty()) {
                        String[] account = line.split("-");
                        accountList.put(account[i++], account[i++]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error read file");
            e.printStackTrace();
        }
    }

    /**
     * Get password corresponding username
     * 
     * @param username in get password
     * @return password corresponding
     */
    public static String getPassByName(String username) {
        String password = null;
        if (accountList.containsKey(username)) {
            password = accountList.get(username);
        }
        return password;
    }
}
