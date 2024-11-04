package gr.aueb.mobileapp.dao;

import gr.aueb.mobileapp.model.MobileContact;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MobileContactDAOImpl implements IMobileContactDAO{
    private static final List<MobileContact> contacts = new ArrayList<>(); // in-memory storage instead of DB



    @Override
    public MobileContact insert(MobileContact mobileContact) {
        if (mobileContact == null) return null;
        contacts.add(mobileContact);
        return mobileContact;
    }

    @Override
    public MobileContact update(long id, MobileContact mobileContact) {
        if (id != mobileContact.getId()) return null;
        int positionToUpdate = getIndexById(id);
        return contacts.set(positionToUpdate, mobileContact);
    }
    /*public MobileContact update(long id, MobileContact oldContact, MobileContact mobileContact) {
        if (id != mobileContact.getId()) return null;
        //int positionToUpdate = getIndexById(id);
        //equals() should have been implemented as indexOf will use it for comparison
        int positionToUpdate = contacts.indexOf(oldContact);
        return contacts.set(positionToUpdate, mobileContact);
    }*/

    @Override
    public void delete(long id) {
        contacts.removeIf((contact) -> contact.getId() == id);
    }

    @Override
    public MobileContact get(long id) {
        int pos = getIndexById(id);
        if (pos == -1) return null;
        return contacts.get(pos);
    }

    @Override
    public List<MobileContact> getaAll() {
        // return contacts; everyone have this reference can modify our list for example by contacts.delete(5)
        //the reference is final not what's included
        //return new ArrayList<>(contacts); for this reason is better practice to return a new Arraylist a copy
        // we do not care if sb changes copy's data
        return Collections.unmodifiableList(contacts); // better alternative
    }

    @Override
    public void delete(String phoneNumber) {
        contacts.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumber));
    }

    @Override
    public MobileContact get(String phoneNumber) {
        int pos = getIndexByPhoneNumber(phoneNumber);
        if (pos == -1) return null;
        return contacts.get(pos);
    }

    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        return getIndexByPhoneNumber(phoneNumber) != -1;
    }

    @Override
    public boolean userIdExists(long id) {
        return getIndexById(id) != -1;
    }

    private int getIndexById(long id) {
        int pos = -1;

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == id) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    private int getIndexByPhoneNumber(String phoneNumber) {
        int pos = -1;

        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phoneNumber)) {
                pos = i;
                break;
            }
        }
        return pos;
    }
}
