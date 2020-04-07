package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;


public class CreateTable implements DatabaseCommand {
    private final ExecutionEnvironment env;
    private final String dbName;
    private final String tableName;


    CreateTable(ExecutionEnvironment env, String dbName, String tableName) {
        this.env = env;
        this.dbName = dbName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() {
        if (env.getDatabase(dbName).isEmpty()) {
            return DatabaseCommandResult.DbCommandResult.error("Database doesn't found");
        }
        Database database = env.getDatabase(dbName).get();
        try {

            database.createTableIfNotExists(tableName);
            return DatabaseCommandResult.DbCommandResult.success("Table is created.");
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.DbCommandResult.error(ex.getMessage());
        }


    }
}
