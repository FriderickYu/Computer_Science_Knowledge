package org.ytq.dao.impl;

import org.ytq.dao.BookDao;

public class BookDaoImpl implements BookDao {
    private String databaseName;
    private int connectionName;

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setConnectionName(int connectionName) {
        this.connectionName = connectionName;
    }

    @Override
    public void save() {
        System.out.println("book dao save ..." + databaseName + "," + connectionName);
    }
}
