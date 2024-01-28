package me.neptune.managers.util;

import me.neptune.managers.impl.client.ModuleManager;

import java.util.List;

/**
 * Interface for Managers that require registering Objects.
 * Note that init(); is not implemented within this interface,
 * as it is called statically.
 * @see ModuleManager
 * @param <T> the type of Object that the Manager will register.
 */
public interface IterableRegistry<T>
{
    void register(T object);
    void unregister(T object);
    List<T> getRegistered();
}
