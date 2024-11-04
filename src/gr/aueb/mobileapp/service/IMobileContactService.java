package gr.aueb.mobileapp.service;

import gr.aueb.mobileapp.dto.MobileContactDTO;
import gr.aueb.mobileapp.model.MobileContact;
import gr.aueb.mobileapp.service.exceptions.MobileContactNotFoundException;
import gr.aueb.mobileapp.service.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.mobileapp.service.exceptions.UserIdAlreadyExistsException;

import java.util.List;

public interface IMobileContactService {
    /**
     *
     * @param contactDTO
     * @return
     * @throws PhoneNumberAlreadyExistsException
     * @throws UserIdAlreadyExistsException
     */
    MobileContact insertMobileContact(MobileContactDTO contactDTO)
            throws PhoneNumberAlreadyExistsException, UserIdAlreadyExistsException;

    /**
     *
     * @param id
     * @param contactDTO
     * @return
     * @throws MobileContactNotFoundException
     * @throws PhoneNumberAlreadyExistsException
     * @throws UserIdAlreadyExistsException
     */

    MobileContact updateMobileContact(long id, MobileContactDTO contactDTO)
            throws MobileContactNotFoundException, PhoneNumberAlreadyExistsException, UserIdAlreadyExistsException;

    /**
     *
     * @param phoneNumber
     * @throws MobileContactNotFoundException
     */

    void deleteMobileContact(String phoneNumber) throws MobileContactNotFoundException;

    /**
     *
     * @param id
     * @throws MobileContactNotFoundException
     */

    void deleteMobileContact(long id) throws MobileContactNotFoundException;

    /**
     *
     * @param phoneNumber
     * @return
     * @throws MobileContactNotFoundException
     */

    MobileContact getMobileContact(String phoneNumber) throws MobileContactNotFoundException;

    /**
     *
     * @param id
     * @return
     * @throws MobileContactNotFoundException
     */

    MobileContact getMobileContact(long id) throws MobileContactNotFoundException;

    /**
     *
     * @return
     */

    List<MobileContact> getAllMobileContacts();
}
