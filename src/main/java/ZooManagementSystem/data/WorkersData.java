package ZooManagementSystem.data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WorkersData {

    // private instance, so that it can be
    // accessed only by getInstance() method
    private static WorkersData instance;
    private String username;
    private String passwordHash;


    //private constructer
    private WorkersData(String username,String password){
        this.username=username;
        this.passwordHash = hashPassword(password);
    }

    // method to return instance of class to ensure that one database being used
    public static WorkersData getInstance(String username , String password){
        if (instance==null) // if instance is null , initialize
            instance=new WorkersData(username,password);
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }

    public boolean matchesPassword(String password) {
        if (passwordHash.isEmpty() || password == null || password.isBlank()) {
            return false;
        }
        return passwordHash.equals(hashPassword(password));
    }

    private static String hashPassword(String password) {
        if (password == null || password.isBlank()) {
            return "";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 is not available", e);
        }
    }
}
