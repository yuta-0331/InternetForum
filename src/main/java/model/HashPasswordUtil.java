package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashPasswordUtil {
    MessageDigest md;
    
    public HashPasswordUtil() throws NoSuchAlgorithmException {
        this.md = MessageDigest.getInstance("SHA3-256");

    }
    public String create(String password) throws NoSuchAlgorithmException{
        byte[] sha3_256_result = md.digest(password.getBytes());
        return String.format("%040x", new BigInteger(1, sha3_256_result)); 
    }
    
    public boolean checkHash(String pass, String hashPass) throws NoSuchAlgorithmException {
        return pass.equals(hashPass);
    }
}
