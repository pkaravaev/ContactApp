package com.pkaravaev.dao;


import com.pkaravaev.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public void save(User u) {

        String sql = " INSERT INTO user (name, phone, email, address, loginName, password, role,  loginStatus)" +
                "VALUES (:name, :phone, :email, :address, :loginName, :password, :role, :loginStatus)";

        Map m = new HashMap();

        m.put("name", u.getName());
        m.put("phone", u.getPhone());
        m.put("email", u.getEmail());
        m.put("address", u.getAddress());
        m.put("loginName", u.getLoginname());
        m.put("password", u.getPassword());
        m.put("role", u.getRole());
        m.put("loginStatus", u.getLoginstatus());

        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource ps = new MapSqlParameterSource(m);
        super.getNamedParameterJdbcTemplate().update(sql, ps, kh);

        Integer userid = kh.getKey().intValue();
        u.setUserid(userid);
    }

    @Override
    public void update(User u) {
        String sql = "UPDATE user SET name=:name, " +
                "phone=:phone, " +
                "email=:email, " +
                "address=:address," +
                "role=:role," +
                "loginStatus=:loginStatus WHERE userid=:userid";

        Map m = new HashMap();

        m.put("name", u.getName());
        m.put("phone", u.getPhone());
        m.put("email", u.getEmail());
        m.put("address", u.getAddress());
        m.put("role", u.getRole());
        m.put("loginStatus", u.getLoginstatus());
        m.put("userid", u.getUserid());
        getNamedParameterJdbcTemplate().update(sql, m);

    }

    @Override
    public void delete(User u) {
        String sql = "DELETE FROM user WHERE userid=? ";
        getJdbcTemplate().update(sql,u.getUserid());
    }

    @Override
    public void delete(Integer userid) {

        String sql = "DELETE FROM user WHERE userid=? ";
        getJdbcTemplate().update(sql,userid);
    }

    @Override
    public User findById(Integer userid) {
        return null;
    }

    @Override
    public List<User> findAll(Integer userid) {

        String sql = "";
        return null;
    }

    @Override
    public List<User> findByProperty(String propName, Object propValue) {
        return null;
    }


}
