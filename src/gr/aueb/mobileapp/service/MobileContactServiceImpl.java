package gr.aueb.mobileapp.service;

import gr.aueb.mobileapp.dao.IMobileContactDAO;
import gr.aueb.mobileapp.dto.MobileContactDTO;
import gr.aueb.mobileapp.dto.UserDetailsDTO;
import gr.aueb.mobileapp.model.MobileContact;
import gr.aueb.mobileapp.model.UserDetails;
import gr.aueb.mobileapp.service.exceptions.MobileContactNotFoundException;
import gr.aueb.mobileapp.service.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.mobileapp.service.exceptions.UserIdAlreadyExistsException;

import java.util.List;

public class MobileContactServiceImpl implements IMobileContactService {

    private final IMobileContactDAO dao; // composition

    public MobileContactServiceImpl(IMobileContactDAO dao) { // dependency injection
        this.dao = dao;
    }

    @Override
    public MobileContact insertMobileContact(MobileContactDTO contactDTO)
            throws PhoneNumberAlreadyExistsException, UserIdAlreadyExistsException {
        MobileContact mobileContact;
        try {
            mobileContact = new MobileContact();

            if (dao.phoneNumberExists(contactDTO.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistsException(mobileContact);
            }
            if (dao.userIdExists(contactDTO.getId())) {
                throw new UserIdAlreadyExistsException(mobileContact);
            }
            mapMobileContact(mobileContact, contactDTO);
            mobileContact = dao.insert(mobileContact);
        } catch (PhoneNumberAlreadyExistsException | UserIdAlreadyExistsException e) {
            e.printStackTrace();
            throw e;
        }
        return mobileContact;
    }

        @Override
        public MobileContact updateMobileContact (long id, MobileContactDTO contactDTO)
                throws MobileContactNotFoundException, PhoneNumberAlreadyExistsException  { //UserIdAlreadyExistsException
            MobileContact mobileContact;
            try {
                mobileContact = new MobileContact();
                mapMobileContact(mobileContact, contactDTO);

                if (id != contactDTO.getId() || !dao.userIdExists(id)) {
                    throw new MobileContactNotFoundException(id);
                }

                if (dao.phoneNumberExists(contactDTO.getPhoneNumber())) {
                     if (!dao.get(contactDTO.getId()).equals(dao.get(contactDTO.getPhoneNumber())));
                         throw new PhoneNumberAlreadyExistsException(mobileContact);
                }
              /*if (dao.userIdExists(contactDTO.getId())) {
                    throw new UserIdAlreadyExistsException(mobileContact);
                }*/


                mobileContact = dao.update(id, mobileContact);
            } catch (PhoneNumberAlreadyExistsException | MobileContactNotFoundException e) { //UserIdAlreadyExistsException
                e.printStackTrace();
                throw e;
            }
            return mobileContact;
        }

        @Override
        public void deleteMobileContact (String phoneNumber) throws MobileContactNotFoundException {
            MobileContact mobileContact;
            try {
                mobileContact = new MobileContact();
                if (!dao.phoneNumberExists(phoneNumber)) {
                    throw new MobileContactNotFoundException(mobileContact);
                }
                dao.delete(phoneNumber);
            } catch (MobileContactNotFoundException e) {
                e.printStackTrace();
                throw e;
            }
        }

        @Override
        public void deleteMobileContact ( long id) throws MobileContactNotFoundException {
            MobileContact mobileContact;
            try {
                mobileContact = new MobileContact();
                if (!dao.userIdExists(id)) {
                    throw new MobileContactNotFoundException(mobileContact);
                }
                dao.delete(id);
            } catch (MobileContactNotFoundException e) {
                e.printStackTrace();
                throw e;
            }
        }

        @Override
        public MobileContact getMobileContact (String phoneNumber) throws MobileContactNotFoundException {
            MobileContact mobileContact;
            try {
                mobileContact = dao.get(phoneNumber);
                if (mobileContact == null) {
                    throw new MobileContactNotFoundException(phoneNumber);
                }
                return mobileContact; // return inside try, as it is not stated as null outside try
            } catch (MobileContactNotFoundException e) {
                e.printStackTrace();
                throw e;
            }
        }

        @Override
        public MobileContact getMobileContact (long id) throws MobileContactNotFoundException {
            MobileContact mobileContact;
            try {
                mobileContact = dao.get(id);
                if (mobileContact == null) {
                    throw new MobileContactNotFoundException(id);
                }
                return mobileContact; // return inside try, as it is not stated as null outside try
            } catch (MobileContactNotFoundException e) {
                e.printStackTrace();
                throw e;
            }
        }

        @Override
        public List<MobileContact> getAllMobileContacts () {
            return dao.getaAll();
        }

        private void mapMobileContact(MobileContact mobileContact, MobileContactDTO mobileContactDTO) {
            mobileContact.setId(mobileContactDTO.getId());
            mobileContact.setPhoneNumber(mobileContactDTO.getPhoneNumber());
            //mapUserDetails(mobileContact.getUserDetails(), mobileContactDTO.getUserDetails());
            UserDetails userDetails = new UserDetails();
            mapUserDetails(userDetails,mobileContactDTO.getUserDetails());
            mobileContact.setUserDetails(userDetails);
        }

        private void mapUserDetails (UserDetails userDetails, UserDetailsDTO userDetailsDTO) {
            userDetails.setFirstname(userDetailsDTO.getFirstname());
            userDetails.setLastname(userDetailsDTO.getLastname());
            userDetails.setId(userDetailsDTO.getId());

        }
    }
