package gr.aueb.mobileapp.model;

public class MobileContact extends AbstractEntity {
    private UserDetails userDetails;
    private String phoneNumber;

    public MobileContact() {};

    public MobileContact(long id, UserDetails userDetails, String phoneNumber) {
        setId(id);
        this.userDetails = userDetails;
        this.phoneNumber = phoneNumber;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "MobileContact{" +
                "userDetails=" + userDetails +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobileContact that = (MobileContact) o;

        if (!userDetails.equals(that.userDetails)) return false;
        return phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = userDetails.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        return result;
    }
}
