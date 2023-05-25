package org.ytq.factory;

import org.ytq.dao.OrderDao;
import org.ytq.dao.impl.OrderDaoImpl;

public class OrderDaoFactory {
    public static OrderDao getOrderDao(){
        System.out.println("factory setup ...");
        return new OrderDaoImpl();
    }
}
