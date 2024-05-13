package neptune;

import neptune.Neptune;
import neptune.utils.MinecraftInterface;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Feature implements ModInitializer, MinecraftInterface {

    public static final String NAME = "Neptune";
    public static final String VERSION = "b0.2.0 - Release";
    private final Logger logger = LogManager.getLogger(Neptune.class);

    @Override
    public void onInitialize() {
        logger.info("[NEPTUNE] Features have been initialized successfully.");
    }
}
