import java.util.Scanner;

public class RealEstate {
    private User[] users;
    private Property[] properties;
    private Address[] addresses;

    private static final Scanner scanner = new Scanner(System.in);
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PROPERTY_FOR_NORMAL_USER = 3;
    public static final int MAX_PROPERTY_FOR_BROKER_USER = 10;
    public static final int PHONE_NUMBER_LENGTH = 10;


    public RealEstate() {
        this.users = new User[0];
        this.properties = new Property[0];
        this.addresses = new Address[10];
        addresses[0] = new Address("Ashkelon", "Afridar");
        addresses[1] = new Address("Tel-Aviv", "Herzl");
        addresses[2] = new Address("Asdod", "Anavim");
        addresses[3] = new Address("Ashkelon", "Herzl");
        addresses[4] = new Address("Asdod", "Afridar");
        addresses[5] = new Address("Ashkelon", "Balfour");
        addresses[6] = new Address("Tel-Aviv", "Bialik");
        addresses[7] = new Address("Ashkelon", "Eli-Cohen");
        addresses[8] = new Address("Tel-Aviv", "Rothschild");
        addresses[9] = new Address("Ashkelon", "Bar-Kohva");
    }


    boolean postNewProperty(User user) {
        String chosenCity;
        String chosenStreet;
        String finalAddress;
        int propType;
        int userInputForRent;
        int floor = 0;
        int numberOfRooms;
        int propertyNumber;
        boolean rent = false;
        int price;


        int count = 0;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].getPublishedByUser().equals(user.getUsername())) {
                count++;
            }
            if (user.getAccountType().equals("Normal") && count == MAX_PROPERTY_FOR_NORMAL_USER || user.getAccountType().equals("Broker") && count == MAX_PROPERTY_FOR_BROKER_USER) {
                System.out.println("You have reached the limit of your posts");
                return false;
            }
        }
        showCityList();

        System.out.println("Choose your desired city: ");
        chosenCity = scanner.nextLine();
        for (int i = 0; i < addresses.length; i++) {
            if (chosenCity.equals(addresses[i].getCity())) {
                showStreetList(chosenCity);
                break;
            } else if (i == addresses.length - 1 && !chosenCity.equals(addresses[i].getCity())) {
                System.out.println("That city does not exist.");
                return false;
            }
        }
        System.out.println("Choose your desired street: ");
        chosenStreet = scanner.nextLine();
        for (int i = 0; i < addresses.length; i++) {
            if (chosenCity.equals(addresses[i].getCity()) && chosenStreet.equals(addresses[i].getStreet())) {
                break;
            } else if (i == addresses.length - 1 && !chosenStreet.equals(addresses[i].getStreet())) {
                System.out.println("That street does not exist.");
                return false;
            }
        }
        finalAddress = chosenCity + "," + chosenStreet;
//
        System.out.println("What type is your property: ");
        System.out.println("1 - Normal Apartment");
        System.out.println("2 - Penthouse Apartment");
        System.out.println("3 - Private Property");
        propType = scanner.nextInt();
        System.out.println("What floor is your property on: ");
        floor = scanner.nextInt();
        System.out.println("How many rooms are there in your property: ");
        numberOfRooms = scanner.nextInt();
        System.out.println("What is the property number: ");
        propertyNumber = scanner.nextInt();
        System.out.println("Is your property for rent or sale: ");
        System.out.println("1 - Rent");
        System.out.println("2 - Sale");
        userInputForRent = scanner.nextInt();
        if (userInputForRent == 1) {
            rent = true;
        } else if (userInputForRent == 2) {
            rent = false;
        }
        System.out.println("What is the price for the property: ");
        price = scanner.nextInt();
        addProperty(finalAddress, numberOfRooms, price, propType,rent, propertyNumber, floor, user);
        return true;
    }


    private void showStreetList(String city){
        for (int i = 0; i<addresses.length; i++) {
            if (addresses[i].getCity().equals(city))
            System.out.println(addresses[i].getStreet());
        }
    }

    private void showCityList(){
        for (int i = 0; i<addresses.length; i++) {
            boolean exists = false;
            for (int j = i - 1; j >= 0; j--) {
                if (addresses[i].getCity().equals(addresses[j].getCity())) {
                    exists = true;
                    break;
                }
            }
            if (!exists){
                System.out.println(addresses[i].getCity());
            }
        }
    }

    private boolean hasPost(String username){
        for (int i = 0; i<properties.length; i++){
            if (properties[i].getPublishedByUser().getUsername().equals(username)){
                return true;
            }
        }

        return false;
    }

    void printAllProperties(){
        for (int i = 0; i<properties.length; i++) {
                System.out.println(properties[i].toString());
            }
    }

    void printUserProperties(User user){
        for (int i = 0; i<properties.length; i++) {
            if (hasPost(user.getUsername())) {
                System.out.println(properties[i].toString());
            }
        }
    }

    Property[] search(){
        int i = 0;
        Property[] propArray = new Property[i++];
        boolean rent = false;
        int propertyType;
        int numberOfRooms;
        int floor;
        int minPrice;
        int maxPrice;

        System.out.println("Answer the questions to find a property: ");
        System.out.println("Rent or sale?");
        System.out.println("1 - rent");
        System.out.println("2 - sale");
        int input = scanner.nextInt();
        if (input == 1 ) {
            rent = true;
        } else if (input == 2){
            rent = false;
        }
        System.out.println("What property type do you want?");
        System.out.println("1 - regular apartment");
        System.out.println("2 - penthouse apartment");
        System.out.println("3 - private property");
        propertyType = scanner.nextInt();
        System.out.println("Preferred floor for the property: ");
        floor = scanner.nextInt();
        System.out.println("Preferred number of rooms? ");
        numberOfRooms = scanner.nextInt();
        System.out.println("Price for the property?");
        System.out.println("Minimum price:");
        minPrice = scanner.nextInt();
        System.out.println("Maximum price:");
        maxPrice = scanner.nextInt();

        for (i = 0; i<properties.length; i++){
            if(properties[i].isRent() == rent){
                if(properties[i].getPropertyType() == propertyType){
                    if(properties[i].getFloor() == floor){
                        if(properties[i].getNumberOfRooms() == numberOfRooms ){
                            if(properties[i].getPrice() >= minPrice && properties[i].getPrice() <= maxPrice ) {
                               propArray[i] = properties[i];
                            } else if (input == -999 || floor == -999 || numberOfRooms == -999 || minPrice == -999 || maxPrice == -999) {
                            propArray[i] = properties[i];
                            }
                        }
                    }
                }
            }
        }

        return propArray;
    }

    void removeProperty(User user) {
        int choiceInput;
        if (hasPost(user.getUsername())) {
            for (int i = 0; i < properties.length; i++) {
                if (hasPost(user.getUsername())) {
                    System.out.println(i + "   - " + properties[i].toString());
                }
            }
            System.out.println("Choose a property to remove: ");
            choiceInput = scanner.nextInt();
            Property[] newArray = new Property[this.properties.length - 1];
            for (int j = 0, k = 0; j < properties.length; j++) {
                if (j == choiceInput) {
                    continue;
                }
                newArray[k++] = this.properties[j];
            }
            this.properties = newArray;
            System.out.println("You have successfully removed the property.");
        } else {
            System.out.println("You have not posted any property yet.");
        }
    }

    void createUser() {
        String username;
        String password;
        String phoneNumber;
        String accountType;

        System.out.println("Enter your username: ");
        username = scanner.nextLine();
        while (doesUsernameExists(username)) {
            System.out.println("This username is taken, try again.");
            username = scanner.nextLine();
        }
        System.out.println("Enter a strong password: ");
        System.out.println("Password must contain at least: one number and one character such as (%,$,_) ");
        password = scanner.nextLine();
        while (!isPasswordStrong(password)) {
            System.out.println("Your password is weak or must be invaild format! try again");
            password = scanner.nextLine();
        }
        System.out.println("Enter your phone number: ");
        phoneNumber = scanner.nextLine();
        while (!isPhoneNumberCorrect(phoneNumber)) {
            System.out.println("The phone number is incorrect, try again");
            phoneNumber = scanner.nextLine();
        }
        System.out.println("Enter the type of your account:  ");
        System.out.println("Normal or Broker");
        accountType = scanner.nextLine();
        while (!checkAccountType(accountType)) {
            System.out.println("This account type does not exist, try again.");
            accountType = scanner.nextLine();
        }
        addUser(username, password, phoneNumber, accountType);
        System.out.println("Account has been successfully created!");
    }

    void addProperty(String finalAddress, int numberOfRooms, int price, int propType ,boolean rent, int propertyNumber, int floor, User user) {
        Property property = new Property(finalAddress,numberOfRooms,price,propType,rent,propertyNumber,floor, user);
        Property[] propertyArray = new Property[this.properties.length + 1];
        for (int i = 0; i < this.properties.length; i++) {
            propertyArray[i] = this.properties[i];
        }
        propertyArray[this.properties.length] = property;
        this.properties = propertyArray;
    }

    void addUser(String username, String password, String phoneNumber, String accountType) {
        User createdUser = new User(username, password, phoneNumber, accountType);
        User[] userArray = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            userArray[i] = this.users[i];
        }
        userArray[this.users.length] = createdUser;
        this.users = userArray;
    }

    User login() {
        addUser("test", "test123%", "0531231231", "Normal");
        String username;
        String password;
        boolean usernameExists = false;
        boolean correctPassword = false;

        System.out.println("Enter your username: ");
        username = scanner.nextLine();
        System.out.println("Enter your password: ");
        password = scanner.nextLine();
        if (doesUsernameExists(username)) {
            usernameExists = true;
        }
        if (isPasswordCorrect(password) && usernameExists) {
            correctPassword = true;
        }
        int index = indexOfUser(username);
        if (usernameExists && correctPassword && index > -1) {
            return this.users[index];
        }
        return null;
    }

    private boolean isPasswordCorrect(String checkPassword) {
        for (int i = 0; i < this.users.length; i++) {
            User account = this.users[i];
            if (account.getPassword().equals(checkPassword)) {
                return true;
            }
        }
        return false;
    }

    private int indexOfUser(String input) {
        for (int i = 0; i < this.users.length; i++) {
            User existingUser = this.users[i];
            if (existingUser.getUsername().equals(input)) {
                return i;
            }
        }
        return -1;
    }

    private boolean doesUsernameExists(String usernameCheck){
            for (int i = 0; i < this.users.length; i++) {
                User existingUser = this.users[i];
                if (existingUser.getUsername().equals(usernameCheck)) {
                    return true;
                }
            }
            return false;
        }

        private boolean isPasswordStrong (String passwordCheck){
            boolean containsChar = false;
            boolean containsDigit = false;
            boolean isASpace = false;
            boolean containsSpecialChar = false;

            if (passwordCheck.length() < MIN_PASSWORD_LENGTH) {
                return false;
            }else {
                for (int i = 0; i < passwordCheck.length(); i++) {
                    if (Character.isDigit(passwordCheck.charAt(i))) {
                        containsDigit = true;
                    } else if (Character.isAlphabetic(passwordCheck.charAt(i))) {
                        containsChar = true;
                    } else if (Character.isWhitespace(passwordCheck.charAt(i))) {
                        isASpace = true;
                    } else if (passwordCheck.charAt(i) == '%' || passwordCheck.charAt(i) == '_' || passwordCheck.charAt(i) == '$') {
                        containsSpecialChar = true;
                    }
                }
                if (containsChar & containsDigit & containsSpecialChar & !isASpace) {
                    return true;
                }
            }
            return false;
        }

        private boolean isPhoneNumberCorrect (String phoneNumCheck){
            boolean correctLength = false;
            int count = 0;
            int startOfPhone = PHONE_NUMBER_LENGTH - 8;

            if (phoneNumCheck.length() == PHONE_NUMBER_LENGTH) {
                correctLength = true;
            } else {
                return false;
            }
            for (int i = 0; i < startOfPhone; i++) {
                if (phoneNumCheck.charAt(i) == '0') {
                    count++;
                } else if (phoneNumCheck.charAt(i) == '5' && count == 1) {
                    return true;
                }
            }
            return false;
        }

        private boolean checkAccountType (String checkAccountType){
            if (checkAccountType.equals("Broker") || checkAccountType.equals("Normal")) {
                return true;
            }
            return false;
        }
}