package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static class DbCommandResult implements DatabaseCommandResult {

        private final Optional<String> result;
        private String message;
        private final DatabaseCommandStatus status;

        private DbCommandResult(String result, DatabaseCommandStatus status) {
            this.result = Optional.ofNullable(result);
            this.status = status;
        }

        private DbCommandResult(String result, DatabaseCommandStatus status, String message) {
            this.result = Optional.ofNullable(result);
            this.status = status;
            this.message = message;
        }

        public static DatabaseCommandResult success(String result) {
            return new DbCommandResult(result, DatabaseCommandStatus.SUCCESS);
        }

        public static DatabaseCommandResult error(String message) {
            return new DbCommandResult(null, DatabaseCommandStatus.FAILED, message);
        }

        @Override
        public Optional<String> getResult() {
            return result;
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public String getErrorMessage() {
            return message;
        }
    }
}