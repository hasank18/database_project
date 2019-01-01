package DB;

public class user {
    private String UserName;
    private String Password;

    public user(String U,String P) {
        setUserName(U);
        setPassword(P);
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String username) {
        UserName = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


}
