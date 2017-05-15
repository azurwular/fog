/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Repositories;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilities for handling passwords securely
 * 
 * @author azurwular
 */
public class PasswordUtils
{
    public static final String SALT = "this-is-a-super-secure-salt";
    
    public static String getSaltedHash(String input)
    {
        input = SALT + input;
        StringBuilder hash = new StringBuilder();

        try {
                MessageDigest sha = MessageDigest.getInstance("SHA-1");
                byte[] hashedBytes = sha.digest(input.getBytes());
                char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                'a', 'b', 'c', 'd', 'e', 'f' };
                for (int idx = 0; idx < hashedBytes.length; ++idx) {
                        byte b = hashedBytes[idx];
                        hash.append(digits[(b & 0xf0) >> 4]);
                        hash.append(digits[b & 0x0f]);
                }
        } catch (NoSuchAlgorithmException e) {
        }

        return hash.toString();
    }
}
