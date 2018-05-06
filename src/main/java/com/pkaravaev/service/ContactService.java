package com.pkaravaev.service;

import com.pkaravaev.domain.Contact;

import java.util.List;

public interface ContactService {

    public void save(Contact contact);

    public void update(Contact contact);

    public void delete(Integer contactId);

    public void delete(Integer[] contactIds);

    public List<Contact> findUserContact(Integer userid);

    public List<Contact> findUserContact(Integer userid, String txt);

}
