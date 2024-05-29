package neptune.event.events;

import com.google.common.eventbus.Subscribe;
import neptune.utils.math.Clamp;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

import java.util.Arrays;

public class TickRate {
    public static TickRate INSTANCE = new TickRate();

    private final float[] tickRates = new float[20];
    private int nextIndex = 0;
    private long timeLastTimeUpdate = -1;
    private long timeGameJoined;

    private TickRate() {
    }

    @Subscribe
    private void onReceivePacket(PacketEvent.Receive packetEvent) {
        if (PacketEvent.packet instanceof WorldTimeUpdateS2CPacket) {
            long now = Clamp.getCurrentTimeMillis();
            float timeElapsed = (float) (now - timeLastTimeUpdate) / 1000.0F;
            tickRates[nextIndex] = Clamp.clamp(20.0f / timeElapsed, 0.0f, 20.0f);
            nextIndex = (nextIndex + 1) % tickRates.length;
            timeLastTimeUpdate = now;
        }
    }

    @Subscribe
    private void onGameJoined(GameJoinedEvent event) {
        Arrays.fill(tickRates, 0);
        nextIndex = 0;
        //timeGameJoined = timeLastTimeUpdate = Utils.getCurrentTimeMillis();
    }

    public float getTickRate() {
        //if (!Utils.canUpdate()) return 0;
        //if (Utils.getCurrentTimeMillis() - timeGameJoined < 4000) return 20;

        int numTicks = 0;
        float sumTickRates = 0.0f;
        for (float tickRate : tickRates) {
            if (tickRate > 0) {
                sumTickRates += tickRate;
                numTicks++;
            }
        }

        return sumTickRates / numTicks;
    }

    //public float getTimeSinceLastTick() {
    //long now = Utils.getCurrentTimeMillis();
    //if (now - timeGameJoined < 4000) return 0;
    //return (now - timeLastTimeUpdate) / 1000f;
    //}
//}
}
