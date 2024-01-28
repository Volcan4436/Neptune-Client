package me.neptune.api;

import net.minecraft.client.MinecraftClient;

public interface InstanceHolder
{
    MinecraftClient mc = MinecraftClient.getInstance();
}
