package gr.aueb.mobileapp.service.exceptions;

import gr.aueb.mobileapp.model.MobileContact;

public class UserIdAlreadyExistsException extends Exception {
    private final static long serialVersionUID = 1L;

    public UserIdAlreadyExistsException(MobileContact mobileContact) {
        super("Mobile contact with id " + mobileContact.getId() + " already exists");
    }
}
