package xyz.codem.randomdinner.database;

public class DbSchema {
    public static final class Table {
        public static final String NAME = "dinner";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String RESULT = "result";
            public static final String ENTRY = "entry";
        }
    }
}