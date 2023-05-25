package org.ytq.factory;

import org.springframework.beans.factory.FactoryBean;
import org.ytq.dao.UserDao;
import org.ytq.dao.impl.UserDaoImpl;

public class UserDaoFactoryBean implements FactoryBean<UserDao> {

    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }
}
