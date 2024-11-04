package gr.aueb.mobileapp.service.exceptions;

import gr.aueb.mobileapp.model.MobileContact;

public class PhoneNumberAlreadyExistsException extends Exception{ // Exception class is serializable
    private final static long serialVersionUID = 1L;

    public PhoneNumberAlreadyExistsException(MobileContact mobileContact) {
        super("Mobile contact with phone number " + mobileContact.getPhoneNumber() + "already exists");
    }

}
