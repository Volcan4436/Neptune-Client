package me.neptune.managers.util;

/**
 * Interface for Managers that handle different Actions.
 * (like Rotations, Spoofing movement etc.)
 * Allows the manager to declare actions "happening", which means
 * the next action will be executed after the current action has been finished.
 */
public interface ActionManager
{
    void setHappening(boolean happening);
    boolean isHappening();
}
