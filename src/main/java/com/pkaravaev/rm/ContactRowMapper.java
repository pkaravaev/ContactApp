package com.pkaravaev.rm;

import com.pkaravaev.domain.Contact;
import com.pkaravaev.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {


    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

        Contact u = new Contact();

        u.setContactid(rs.getInt("contactid"));
        u.setUserid(rs.getInt("userid"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setAddress(rs.getString("address"));
        u.setPhone(rs.getString("phone"));
        u.setRemark(rs.getString("remark"));

        return u;
    }
}
