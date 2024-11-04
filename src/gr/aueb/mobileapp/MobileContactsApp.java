package gr.aueb.mobileapp;

import gr.aueb.mobileapp.dao.IMobileContactDAO;
import gr.aueb.mobileapp.dao.MobileContactDAOImpl;
import gr.aueb.mobileapp.dto.MobileContactDTO;
import gr.aueb.mobileapp.dto.UserDetailsDTO;
import gr.aueb.mobileapp.model.MobileContact;
import gr.aueb.mobileapp.service.IMobileContactService;
import gr.aueb.mobileapp.service.MobileContactServiceImpl;
import gr.aueb.mobileapp.service.exceptions.MobileContactNotFoundException;
import gr.aueb.mobileapp.service.exceptions.PhoneNumberAlreadyExistsException;
import gr.aueb.mobileapp.service.exceptions.UserIdAlreadyExistsException;

import java.util.List;

public class MobileContactsApp {

    //Wiring
    private final static IMobileContactDAO dao = new MobileContactDAOImpl();
    private final static IMobileContactService service = new MobileContactServiceImpl(dao);

    public static void main(String[] args) {
        try {
            //DTO
            // mobileContactDTO could be given from a user through frontend (eg. form)
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(1L, "Alice", "W.");
            MobileContactDTO mobileContactDTO = new MobileContactDTO(1L, userDetailsDTO, "123456789");

            UserDetailsDTO updatedUserDetailsDTO = new UserDetailsDTO(1L, "Alice", "Wonderland");
            MobileContactDTO updatedMobileContactDTO = new MobileContactDTO(1L, updatedUserDetailsDTO, "123456789");

            // Service
            service.insertMobileContact(mobileContactDTO);
            service.updateMobileContact(1L, updatedMobileContactDTO);

            // Return back
            List<MobileContact> contacts = service.getAllMobileContacts();
            //contacts.forEach(System.out::println);
            for (MobileContact contact : contacts) {
                System.out.println(contact);
            }
        } catch (PhoneNumberAlreadyExistsException | UserIdAlreadyExistsException | MobileContactNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
