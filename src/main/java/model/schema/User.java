package model.schema;

import java.util.function.Consumer;

public class User {
    private int userId;
    private String userName;
    private String mail;
    private String password;
    private String hashedPassword;
    private String profile;
    private String registrationDate;
    private boolean deleteFlag;
    private boolean report;
    private boolean isAdmin;
    
    private User(Builder builder) {
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.mail = builder.mail;
        this.password = builder.password;
        this.hashedPassword = builder.hashedPassword;
        this.profile = builder.profile;
        this.registrationDate = builder.registrationDate;
        this.deleteFlag = builder.deleteFlag;
        this.report = builder.report;
        this.isAdmin = builder.isAdmin;
    }
    
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMail() {
        return mail;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public String getHashedPassword() {
//        return hashedPassword;
//    }

    public String getProfile() {
        return profile;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public boolean isReport() {
        return report;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public static class Builder {
        private int userId;
        public String userName;
        public String mail;
        public String password;
        public String hashedPassword;
        public String profile;
        public String registrationDate;
        public boolean deleteFlag;
        public boolean report;
        public boolean isAdmin;
        
        public Builder(int userId) {
            this.userId = userId;
        }
        
        public Builder with(Consumer<Builder> builder) {
            builder.accept(this);
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}