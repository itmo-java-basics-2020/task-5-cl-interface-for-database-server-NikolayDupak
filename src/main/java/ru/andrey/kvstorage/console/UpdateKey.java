package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class UpdateKey implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String dbName;
    private final String tableName;
    private final String key;
    private final String value;


    UpdateKey(ExecutionEnvironment env, String dbName, String tableName, String key, String value) {
        this.env = env;
        this.dbName = dbName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
    }

    @Override
    public DatabaseCommandResult execute() {
        if (env.getDatabase(dbName).isEmpty()) {
            return DatabaseCommandResult.DbCommandResult.error("Database doesn't found");
        }

        Database database = env.getDatabase(dbName).get();
        try {
            database.write(tableName, key, value);
            return DatabaseCommandResult.DbCommandResult.success("Key, value were wrote in database");
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.DbCommandResult.error(ex.getMessage());
        }
    }
}
