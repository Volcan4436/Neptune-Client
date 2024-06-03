package neptune.event.impl.render;

import neptune.event.api.Event;
import neptune.event.api.EventState;
import net.minecraft.client.gui.DrawContext;

public class RenderEvent extends Event {
    private EventState state;
    private DrawContext context;
    private float tickDelta;
    private final RenderingLayer layer;

    // TODO: Implement 3D Hook
    public RenderEvent(RenderingLayer layer) {
        this.layer = layer;
    }

    public void update(EventState state, DrawContext context, float tickDelta) {
        this.state = state;
        this.context = context;
        this.tickDelta = tickDelta;
    }

    public EventState getState() {
        return state;
    }

    public DrawContext getContext() {
        return context;
    }

    public float getDelta() {
        return tickDelta;
    }

    public RenderingLayer getLayer() {
        return layer;
    }

    public boolean is2D() {
        return layer == RenderingLayer.TWO_DIMENSIONAL;
    }

    public boolean is3D() {
        return layer == RenderingLayer.THREE_DIMENSIONAL;
    }
}