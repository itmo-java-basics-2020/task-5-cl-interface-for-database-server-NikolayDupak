package ru.andrey.kvstorage.logic;

import ru.andrey.kvstorage.exception.DatabaseException;

import java.util.Hashtable;

public class MyDatabase implements Database {

    private final String databaseName;
    private final Hashtable<String, Hashtable<String, String>> Tables;

    public MyDatabase(String name) {
        this.databaseName = name;
        this.Tables = new Hashtable<>();
    }

    @Override
    public String getName() {
        return databaseName;
    }

    @Override
    public void createTableIfNotExists(String tableName) throws DatabaseException {
        if (Tables.get(tableName).isEmpty())
            throw new DatabaseException("Table was exist");
        Tables.put(tableName, new Hashtable<>());
    }

    @Override
    public void createTableIfNotExists(String tableName, int segmentSizeInBytes) throws DatabaseException {
        if (Tables.get(tableName).isEmpty())
            throw new DatabaseException("Table was exist");
        Tables.put(tableName, new Hashtable<>());
    }

    @Override
    public void write(String tableName, String objectKey, String objectValue) throws DatabaseException {
        if (Tables.get(tableName).isEmpty())
            throw new DatabaseException("Table was exist");
        Hashtable<String, String> table = Tables.get(tableName);
        table.put(objectKey, objectValue);
    }

    @Override
    public String read(String tableName, String objectKey) throws DatabaseException {
        if (Tables.get(tableName).isEmpty())
            throw new DatabaseException("Table was exist");
        Hashtable<String, String> table = Tables.get(tableName);
        return table.get(objectKey);
    }
}
