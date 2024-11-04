package gr.aueb.mobileapp.dao;

import gr.aueb.mobileapp.model.MobileContact;

import java.util.List;

public interface IMobileContactDAO {
    /**
     *
     * @param mobileContact
     * @return
     */
    MobileContact insert(MobileContact mobileContact);

    /**
     *
     * @param mobileContact
     * @return
     */
    MobileContact update(long id, MobileContact mobileContact);

    /**
     *
     * @param id
     */
    void delete(long id);

    /**
     *
     * @param id
     * @return
     */
    MobileContact get(long id);

    /**
     *
     * @return
     */
    List<MobileContact> getaAll();

    /**
     *
     * @param phoneNumber
     */
    void delete(String phoneNumber);

    /**
     *
     * @param phoneNumber
     * @return
     */
    MobileContact get(String phoneNumber);

    /**
     *
     * @param phoneNumber
     * @return
     */
    boolean phoneNumberExists(String phoneNumber);

    /**
     *
     * @param id
     * @return
     */
    boolean userIdExists(long id);
}
