package fr.eseo.dis.camille.pfeandroid.bean;

/**
 * Created by Jérome on 20/12/2017.
 */

public class User {
    private int idUser;
    private String userName;
    private String salt;
    private String password;
    private String forename;
    private String surname;


    public User(){
        this.idUser = -1;
        this.userName = "";
        this.salt = "";
        this.password = "";
        this.forename = "";
        this.surname = "";
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
