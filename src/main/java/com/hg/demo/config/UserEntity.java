package com.hg.demo.config;

/**
 * @author admin
 */
public class UserEntity {
    String userName;
    String passWord;

    public UserEntity() {
    }

    public UserEntity(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
