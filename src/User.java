public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private String accountType;


    public User (String username, String password, String phoneNumber, String accountType) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.accountType = accountType;
    }
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getAccountType() {return accountType;}
}
