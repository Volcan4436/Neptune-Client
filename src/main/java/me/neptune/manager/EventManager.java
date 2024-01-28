package me.neptune.manager;

import com.google.common.eventbus.Subscribe;
import me.neptune.Neptune;
import me.neptune.event.Stage;
import me.neptune.event.impl.*;
import me.neptune.modules.Feature;
import me.neptune.modules.commands.Command;
import me.neptune.util.models.Timer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.Formatting;

public class EventManager extends Feature {
    private final Timer logoutTimer = new Timer();

    public void init() {
        EVENT_BUS.register(this);
    }

    public void onUnload() {
        EVENT_BUS.unregister(this);
    }

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        mc.getWindow().setTitle("OyVey 0.0.3");
        if (!fullNullCheck()) {
//            OyVey.inventoryManager.update();
            Neptune.moduleManager.onUpdate();
            Neptune.moduleManager.sortModules(true);
            onTick();
//            if ((HUD.getInstance()).renderingMode.getValue() == HUD.RenderingMode.Length) {
//                OyVey.moduleManager.sortModules(true);
//            } else {
//                OyVey.moduleManager.sortModulesABC();
//            }
        }
    }

    public void onTick() {
        if (fullNullCheck())
            return;
        Neptune.moduleManager.onTick();
        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == null || player.getHealth() > 0.0F)
                continue;
            EVENT_BUS.post(new DeathEvent(player));
//            PopCounter.getInstance().onDeath(player);
        }
    }

    @Subscribe
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (fullNullCheck())
            return;
        if (event.getStage() == Stage.PRE) {
            Neptune.speedManager.updateValues();
            Neptune.rotationManager.updateRotations();
            Neptune.positionManager.updatePosition();
        }
        if (event.getStage() == Stage.POST) {
            Neptune.rotationManager.restoreRotations();
            Neptune.positionManager.restorePosition();
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        Neptune.serverManager.onPacketReceived();
        if (event.getPacket() instanceof WorldTimeUpdateS2CPacket)
            Neptune.serverManager.update();
    }

    @Subscribe
    public void onWorldRender(Render3DEvent event) {
        Neptune.moduleManager.onRender3D(event);
    }

    @Subscribe public void onRenderGameOverlayEvent(Render2DEvent event) {
        Neptune.moduleManager.onRender2D(event);
    }

    @Subscribe public void onKeyInput(KeyEvent event) {
        Neptune.moduleManager.onKeyPressed(event.getKey());
    }

    @Subscribe public void onChatSent(ChatEvent event) {
        if (event.getMessage().startsWith(Command.getCommandPrefix())) {
            event.cancel();
            try {
                if (event.getMessage().length() > 1) {
                    Neptune.commandManager.executeCommand(event.getMessage().substring(Command.getCommandPrefix().length() - 1));
                } else {
                    Command.sendMessage("Please enter a command.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendMessage(Formatting.RED + "An error occurred while running this command. Check the log!");
            }
        }
    }
}