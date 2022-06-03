package ru.firesquare.old.config;

import redempt.redlib.config.annotations.Comment;
import redempt.redlib.config.annotations.ConfigMappable;

@ConfigMappable
public class Config {
    @Comment("Plugin install timestamp")
    public static Long timestamp = System.currentTimeMillis()/1000L;
    @Comment("Old at timestamp")
    public static Long old_at = 1619802000L;
    @Comment("Division delta between now and install time")
    public static Long division = 2L;
    @Comment("Group to add")
    public static String group = "old";
}
