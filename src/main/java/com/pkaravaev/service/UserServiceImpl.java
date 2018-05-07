package com.pkaravaev.service;

import com.pkaravaev.dao.BaseDAO;
import com.pkaravaev.dao.UserDAO;
import com.pkaravaev.domain.User;
import com.pkaravaev.exception.UserBlockedException;
import com.pkaravaev.rm.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl extends BaseDAO implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(User u) {
        userDAO.save(u);
    }

    @Override
    public User login(String loginName, String password) throws UserBlockedException {
        String sql = "SELECT userid, name, phone, email, address, role, loginStatus, loginName FROM user WHERE loginName=:ln AND PASSWORD=:pw";
        Map m = new HashMap();
        m.put("ln", loginName);
        m.put("pw", password);
        try {
            User user = getNamedParameterJdbcTemplate().queryForObject(sql, m, new UserRowMapper());
            if (user.getLoginStatus() == UserService.LOGIN_STATUS_BLOCKED) {
                throw new UserBlockedException("Your account has benn blocked.Contact to Aadmin.");
            } else {
                return user;
            }
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getUserList() {
        return null;
    }

    @Override
    public void changeLoginStatus(Integer userid, Integer loginStatus) {

    }
}
