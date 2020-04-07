package ru.andrey.kvstorage.console;


import ru.andrey.kvstorage.logic.Database;

public class CreateDatabase implements DatabaseCommand {

    private final ExecutionEnvironment env;
    private final String dbName;
    private final Database database;

    CreateDatabase(ExecutionEnvironment env, String dbName, Database database) {
        this.env = env;
        this.dbName = dbName;
        this.database = database;
    }


    @Override
    public DatabaseCommandResult execute() {

        if (env.getDatabase(this.dbName).isEmpty()) {
            return DatabaseCommandResult.DbCommandResult.error("Database was exists");
        } else {
            env.addDatabase(database);
            return DatabaseCommandResult.DbCommandResult.success("Database is created.");
        }

    }
}
