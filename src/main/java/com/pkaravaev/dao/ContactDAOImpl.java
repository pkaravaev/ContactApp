package com.pkaravaev.dao;

import com.pkaravaev.domain.Contact;
import com.pkaravaev.rm.ContactRowMapper;
import com.pkaravaev.rm.UserRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ContactDAOImpl extends BaseDAO implements ContactDAO {

    @Override
    public void save(Contact c) {

        String sql = "INSERT INTO contact (userid, name, phone, email, address, remark) VALUES ( :userid, :name, :phone, :email, :address, :remark)";

        Map m = new HashMap();

        m.put("userid", c.getUserid());
        m.put("name", c.getName());
        m.put("phone", c.getPhone());
        m.put("email", c.getEmail());
        m.put("address", c.getAddress());
        m.put("remark", c.getRemark());

        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource ps = new MapSqlParameterSource(m);
        getNamedParameterJdbcTemplate().update(sql, ps, kh);
        Integer contactid = kh.getKey().intValue();
        c.setContactid(contactid);

    }

    @Override
    public void update(Contact c) {

        String sql = "UPDATE  contact SET  name=:name, phone=:phone, email=:email, address=:address, remark=:remark WHERE contactid=:contactid";
        Map m = new HashMap();
        m.put("contactid", c.getContactid());
        m.put("name", c.getName());
        m.put("phone", c.getPhone());
        m.put("email", c.getEmail());
        m.put("address", c.getAddress());
        m.put("remark", c.getRemark());

        getNamedParameterJdbcTemplate().update(sql, m);

    }

    @Override
    public void delete(Contact c) {
        this.delete(c.getContactid());
    }

    @Override
    public void delete(Integer contactid) {
        String sql = "DELETE FROM contact WHERE contactid=?";
        getJdbcTemplate().update(sql,contactid);

    }

    @Override
    public Contact findById(Integer contactid) {
        String sql = "SELECT contactid, userid, name, phone, email, address, remark FROM contact WHERE contact=?";
        return getJdbcTemplate().queryForObject(sql, new ContactRowMapper(), contactid);
    }

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT contactid, userid, name, phone, email, address, remark FROM contact";
        return getJdbcTemplate().query(sql, new ContactRowMapper());
    }

    @Override
    public List<Contact> findByProperty(String propName, Object propValue) {

        String sql = "SELECT contactid, userid, name, phone, email, address, remark FROM contact WHERE "+propName + "=?";
        return getJdbcTemplate().query(sql, new ContactRowMapper(), propValue);
    }
}
