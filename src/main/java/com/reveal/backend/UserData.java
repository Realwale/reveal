package com.reveal.backend;



public class UserData {

    private String fullName;
    private String email;
    private String uniqueCode;

    public UserData() {
    }

    public UserData(String fullName, String email, String uniqueCode) {
        this.fullName = fullName;
        this.email = email;
        this.uniqueCode = uniqueCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    @Override
    public String toString() {
        return "Full Name: " + fullName + "\nEmail: " + email + "\nUnique Code: " + uniqueCode;
    }
}
