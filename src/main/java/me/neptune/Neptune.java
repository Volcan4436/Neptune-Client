package me.neptune;

import com.google.common.eventbus.EventBus;
import me.neptune.managers.ManagerHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Neptune implements ClientModInitializer, ModInitializer
{
    public static final String NAME = "Neptune";
    public static final String VER_STRING = "1.0.0";
    private static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static final EventBus EVENT_BUS = new EventBus();


    @Override
    public void onInitializeClient()
    {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[NEPTUNE] Initializing Neptune by heedi.");

        /* initialize shit here. */
        LOGGER.info("[NEPTUNE] All finished initializing Neptune, took {}ms", (System.currentTimeMillis() - startTime));
        LOGGER.info("[NEPTUNE] Neptune Utility Mod by heedi has launched!");
    }

    @Override
    public void onInitialize() {
        ManagerHelper.init();
    }

    public static Logger getLogger(){
        return LOGGER;
    }
}
