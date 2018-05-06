package com.pkaravaev.service;

import com.pkaravaev.dao.BaseDAO;
import com.pkaravaev.dao.UserDAO;
import com.pkaravaev.domain.User;
import com.pkaravaev.exception.UserBlockedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        return null;
    }

    @Override
    public List<User> getUserList() {
        return null;
    }

    @Override
    public void changeLoginStatus(Integer userid, Integer loginStatus) {

    }
}
