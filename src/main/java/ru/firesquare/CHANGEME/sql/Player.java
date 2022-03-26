package ru.firesquare.CHANGEME.sql;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "{CHANGEME}_players")
public class Player {
    @DatabaseField(canBeNull = false, id = true)
    private String name;

    // ORMLite boilerplate
    public Player() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
