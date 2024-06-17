package dao;

import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {
    List<Contact> contact;
    public ContactDAOImpl(){
        contact = new ArrayList<Contact>();
    }
    @Override
    public List<Contact> getAllContacts() {
        return contact;
    }
    @Override
    public Contact getContact(int ContactID) {
        return contact.get(ContactID);
    }
    @Override
    public void updateContact(Contact contact) {
    }
    @Override
    public void deleteContact(Contact contact) {
    }
}
