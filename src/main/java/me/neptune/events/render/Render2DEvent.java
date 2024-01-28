package me.neptune.events.render;

import me.neptune.api.event.Event;
import net.minecraft.client.gui.DrawContext;

public class Render2DEvent extends Event {

    private final DrawContext context;
    private final float tickDelta;

    public Render2DEvent(DrawContext context, float tickDelta) {
        this.context = context;
        this.tickDelta = tickDelta;
    }

    public DrawContext getContext(){
        return context;
    }

    public float getDelta(){
        return tickDelta;
    }
}
