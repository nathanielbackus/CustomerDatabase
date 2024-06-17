package dao;

import model.Contact;

import java.util.List;

public interface ContactDAO {
    public List<Contact> getAllContacts();
    public Contact getContact(int ContactID);
    public void updateContact(Contact contact);
    public void deleteContact(Contact contact);
}
