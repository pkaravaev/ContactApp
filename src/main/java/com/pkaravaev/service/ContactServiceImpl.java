package com.pkaravaev.service;

import com.pkaravaev.dao.BaseDAO;
import com.pkaravaev.dao.ContactDAO;
import com.pkaravaev.domain.Contact;
import com.pkaravaev.rm.ContactRowMapper;
import com.pkaravaev.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl extends BaseDAO implements ContactService {

    @Autowired
    private ContactDAO contactDAO;

    @Override
    public void save(Contact contact) {
        contactDAO.save(contact);
    }

    @Override
    public void update(Contact contact) {
        contactDAO.update(contact);
    }

    @Override
    public void delete(Integer contactId) {
        contactDAO.delete(contactId);
    }

    @Override
    public void delete(Integer[] contactIds) {

        String ids = StringUtil.toCommaSeparatesString(contactIds);
        String sql = "DELETE FROM contact WHERE contactId IN("+ids+")";
        getJdbcTemplate().update(sql, contactIds);

    }

    @Override
    public List<Contact> findUserContact(Integer userid) {
        return  contactDAO.findByProperty("userid", userid);
    }

    @Override
    public List<Contact> findUserContact(Integer userid, String txt) {

        String sql = "SELECT contactid, userid, name, phone, email, address, remark FROM contactId WHERE  userId=? AND (name LIKE '%"+txt+"%'  OR address LIKE '%"+txt+"%' " +
                "OR  phone LIKE '%"+txt+"%' OR  email LIKE '%"+txt+"%' OR  remark LIKE '%"+txt+"%')";
        return getJdbcTemplate().query(sql, new ContactRowMapper(), userid);
    }

    @Override
    public Contact findById(Integer contactId) {
       return contactDAO.findById(contactId);
    }
}
