public class Property {
    public static final int REGULAR_PROPERTY = 1;
    public static final int PENTHOUSE_PROPERTY = 2;
    public static final int PRIVATE_PROPERTY = 3;
    private String address;
    private int numberOfRooms;
    private int price;
    private boolean rent;
    private int propertyType;
    private int propertyNumber;
    private int floor;
    private User publishedByUser;


    public Property(String address, int numberOfRooms, int price, int propertyType, boolean rent, int propertyNumber, int floor, User publishedByUser) {
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.price = price;
        this.propertyType = propertyType;
        this.rent = rent;
        this.propertyNumber = propertyNumber;
        this.floor = floor;
        this.publishedByUser = publishedByUser;
    }

    public String getAddress() {
        return address;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getPrice() {
        return price;
    }

    public boolean isRent() {
        return rent;
    }

    public int getPropertyType() {return propertyType;}

    public int getPropertyNumber() {return propertyNumber;}

    public int getFloor() {return floor;}

    public User getPublishedByUser() {return publishedByUser;}

    public String toString() {
        String isRent;
        String propType = null;
        switch (propertyType){
            case REGULAR_PROPERTY:
                propType = "Regular apartment";
                break;
            case PENTHOUSE_PROPERTY:
                propType = "Penthouse apartment";
                break;
            case PRIVATE_PROPERTY:
                propType = "Private property";
                break;
        }
        if (isRent()) {
            isRent = "for rent:";
        } else {
            isRent = "for sale:";
        }

        String result = propType + " - " + isRent + " " + numberOfRooms + " rooms," + "floor " + floor + " Price:" + price + "$" + " Contact info: " + publishedByUser.getUsername() + " " + publishedByUser.getPhoneNumber() + " (" + publishedByUser.getAccountType() + " account" + ")";

        return result;
    }
}
