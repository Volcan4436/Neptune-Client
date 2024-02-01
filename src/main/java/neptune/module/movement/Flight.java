package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.PacketEvent;
import neptune.event.events.PlayerMoveEvent;
import neptune.event.events.TickEvent;
import neptune.mixins.PlayerMoveC2SPacketAccessor;
import neptune.module.Mod;
import neptune.setting.BooleanSetting;
import neptune.setting.ModeSetting;
import neptune.setting.NumberSetting;
import neptune.utils.player.MoveHelper;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import java.util.Objects;

public class Flight extends Mod {

    double startHeight;
    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Vulcan");
    private final NumberSetting speed = new NumberSetting("Speed", 0.1, 5, 5, 0.1);
    private final NumberSetting vulcanclip = new NumberSetting("Clip", 1, 100, 10, 0.1);
    private final BooleanSetting shouldClip = new BooleanSetting("Should Clip", false);

    public Flight() {
        super("Flight", "Lets you fly (like a god)", Category.MOVEMENT);
        addSettings(mode, speed);
        vulcanclip.setDependency(() -> mode.getMode().equals("Vulcan"));
        shouldClip.setDependency(() -> mode.getMode().equals("Vulcan"));
        addSettings(vulcanclip, shouldClip);
    }

    @EventHandler
    private final void onTickEvent(TickEvent event) {
        if (mode.isMode("Vanilla")) {
            mc.player.getAbilities().setFlySpeed((float) this.speed.getValue());
            mc.player.getAbilities().flying = true;
        }
        if (mode.isMode("Vulcan")) {
            if (mc.player == null) return;

            // Get the block beneath me and check if it is a block that can be stood on
            if (mc.world.getBlockState(mc.player.getBlockPos().down()).getBlock() != Blocks.AIR) {
                mc.player.setPos(mc.player.getX(), mc.player.getY() + 0.2, mc.player.getZ());
                mc.player.setVelocity(mc.player.getVelocity().x, 0, mc.player.getVelocity().z);
                mc.player.fallDistance = 0f;
                mc.player.setOnGround(false);
                if (mc.player.getVelocity().y < 0.3) {
                    mc.player.setVelocity(mc.player.getVelocity().x, 0.5, mc.player.getVelocity().z);
                }
            }
        }
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent event) {

        double clipHeight = startHeight - vulcanclip.getValue();
        assert mc.player != null;
        System.out.println("The Player Height is " + mc.player.getY() + "\n And the clip height is " + mc.player.getY());

        assert mc.player != null;
        if (mc.player.fallDistance > 2) {
            mc.player.setOnGround(true);
            mc.player.fallDistance = 0f;
        }
        if (mc.player.age % 3 == 0) {
            MoveHelper.motionYPlus(0.026);
        } else {
            MoveHelper.motionY(-0.0991);
        }
        if (vulcanclip.getValue() == mc.player.getY() && clipHeight == mc.player.getY()) {
            if (clipHeight == mc.player.getY()) {
                mc.player.updatePosition(mc.player.getX(), mc.player.getY() + vulcanclip.getValue(), mc.player.getZ());
            }
        }
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    private long lastModifiedTime = 0;
    private double lastY = Double.MAX_VALUE;

    @EventHandler
    private void onSendPacket(PacketEvent.Sent packetEvent) {
        PlayerMoveC2SPacket packet = (PlayerMoveC2SPacket) packetEvent.packet;
        long currentTime = getCurrentTimeMillis();
        double currentY = packet.getY(Double.MAX_VALUE);
        if (currentY != Double.MAX_VALUE) {
            if (currentTime - lastModifiedTime > 1000 && lastY != Double.MAX_VALUE && Objects.requireNonNull(mc.world).getBlockState(Objects.requireNonNull(mc.player).getBlockPos().down()).isAir()) {
                ((PlayerMoveC2SPacketAccessor) packet).setY(lastY - 0.03130D);
                lastModifiedTime = currentTime;
            } else lastY = currentY;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (mode.isMode("Vanilla")) {
            assert mc.player != null;
            mc.player.getAbilities().flying = false;
        }
    }
}
