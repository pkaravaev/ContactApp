package com.pkaravaev.dao;

import com.pkaravaev.domain.Contact;


import java.util.List;

public interface ContactDAO {

    public void save(Contact c);

    public void update(Contact c);

    public void delete(Contact c);

    public void delete(Integer contactid);

    public Contact findById(Integer contactid);

    public List<Contact> findAll();

    public List<Contact> findByProperty(String  propName, Object propValue);
}
