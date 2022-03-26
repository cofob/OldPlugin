package ru.firesquare.CHANGEME.config;

import redempt.redlib.config.annotations.Comment;
import redempt.redlib.config.annotations.ConfigMappable;

@ConfigMappable
public class Config {
    @Comment("Some example comment")
    public static Boolean some_example_value = false;

    @Comment("DB config")
    public static String database = "jdbc:sqlite:plugins/Example/database.db";
}
