import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    // Start-up menu
    public static final int CREATE_ACCOUNT = 1;
    public static final int LOGIN = 2;
    public static final int EXIT = 3;
    // New menu for logged-in users
    public static final int PUBLISH_NEW_PROPERTY = 1;
    public static final int REMOVE_POST_ON_PROPERTY = 2;
    public static final int SHOW_ALL_PROPERTIES_IN_THE_SYSTEM = 3;
    public static final int SHOW_ALL_PROPERTIES_PUBLISHED_BY_USER = 4;
    public static final int SEARCH_PROPERTY = 5;
    public static final int LOGOUT = 6;

    public static void main(String[] args) {
        startUpMenu();
    }

    public static void startUpMenu(){
        RealEstate realestate = new RealEstate();
        System.out.println("Choose one of the following options: ");
        System.out.println("1 - Create an account");
        System.out.println("2 - Log into existing account");
        System.out.println("3 - Exit");
        int choice = scanner.nextInt();
        switch (choice){
            case CREATE_ACCOUNT:
                realestate.createUser();
                break;
            case LOGIN:
                User currentUser = realestate.login();
                if(currentUser == null) {
                    System.out.println("Your username or password is incorrect.");
                    startUpMenu();
                } else {
                    System.out.println("Welcome back, " + currentUser.getUsername());
                    System.out.println();
                    loggedInMenu(currentUser);
                }
                break;
            case EXIT:
                break;
        }
    }

    public static void loggedInMenu(User user){
        RealEstate realestate = new RealEstate();
        System.out.println("Are you interested to do the next things: ");
        System.out.println("1 - Publish a new property");
        System.out.println("2 - Remove a post on a property");
        System.out.println("3 - Show all properties in the system");
        System.out.println("4 - Show all of your properties");
        System.out.println("5 - Search for a property");
        System.out.println("6 - Logout");
        int choice = scanner.nextInt();
        switch (choice){
            case PUBLISH_NEW_PROPERTY:
             realestate.postNewProperty(user);
                break;
            case REMOVE_POST_ON_PROPERTY:
                realestate.removeProperty(user);
                break;
            case SHOW_ALL_PROPERTIES_IN_THE_SYSTEM:
                realestate.printAllProperties();
                break;
            case SHOW_ALL_PROPERTIES_PUBLISHED_BY_USER:
                realestate.printUserProperties(user);
                break;
            case SEARCH_PROPERTY:
                realestate.search();
                break;
            case LOGOUT:
                startUpMenu();
                break;
        }

    }
}
