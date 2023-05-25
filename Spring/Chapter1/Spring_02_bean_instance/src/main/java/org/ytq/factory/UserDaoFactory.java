package org.ytq.factory;

import org.ytq.dao.UserDao;
import org.ytq.dao.impl.UserDaoImpl;

public class UserDaoFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
