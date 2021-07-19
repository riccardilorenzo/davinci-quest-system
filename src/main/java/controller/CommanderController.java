package controller;

import model.Attribute;

/**
 * Provides a controller for commanders, which interfaces with the DB.
 * @author TheMind
 */
public class CommanderController {
    public static final int MAX_ATTRIBUTE_BASE_POINTS = Attribute.values().length * 5;
    public static final int MAX_ATTRIBUTE_POINTS = MAX_ATTRIBUTE_BASE_POINTS + 10;
}
