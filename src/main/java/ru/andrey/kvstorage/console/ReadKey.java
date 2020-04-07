package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

public class ReadKey implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String dbName;
    private final String tableName;
    private final String key;

    ReadKey(ExecutionEnvironment env, String dbName, String tableName, String key) {
        this.env = env;
        this.dbName = dbName;
        this.tableName = tableName;
        this.key = key;
    }


    @Override
    public DatabaseCommandResult execute() {
        if (env.getDatabase(dbName).isEmpty()) {
            return DatabaseCommandResult.DbCommandResult.error("Database not found");
        }

        Database database = env.getDatabase(dbName).get();
        try {
            String result = database.read(tableName, key);
            return DatabaseCommandResult.DbCommandResult.success(result);
        } catch (DatabaseException ex) {
            return DatabaseCommandResult.DbCommandResult.error(ex.getMessage());
        }
    }
}
