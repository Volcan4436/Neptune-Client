package neptune.module.misc;

import com.google.common.eventbus.Subscribe;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.ObjectObjectImmutablePair;
import neptune.event.events.ServerConnectBeginEvent;
import neptune.module.Mod;
import neptune.setting.NumberSetting;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;

public class AutoReconnect extends Mod {

    public Pair<ServerAddress, ServerInfo> lastServerConnection;
    private final NumberSetting delay = new NumberSetting("Delay", 0, 15, 5, 0.1);

    public AutoReconnect() {
        super("AutoReconnect", "Automatically reconnects you to the server you were previously in.", Category.MISC);
    }

    private class StaticListener {
        @Subscribe
        private void onGameJoined(ServerConnectBeginEvent event) {
            lastServerConnection = new ObjectObjectImmutablePair<>(event.address, event.info);
        }
    }




}
