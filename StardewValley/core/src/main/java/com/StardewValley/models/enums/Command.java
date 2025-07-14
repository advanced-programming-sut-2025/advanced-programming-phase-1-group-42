package com.StardewValley.models.enums;

import java.util.regex.Matcher;

public interface Command {
    public Matcher matcher(String input);

}
