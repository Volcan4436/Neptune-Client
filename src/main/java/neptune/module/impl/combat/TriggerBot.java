package neptune.module.impl.combat;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.BooleanSetting;
import neptune.setting.impl.ModeSetting;
import neptune.setting.impl.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/*
 * TODO: Add Modes
 *  - StrikeAC
 *  - Always
 *  - Look (Block when looking at opponent)
 *  - Blink (Block when blinking)
 *  - Swing (Block after swing)
 *  - Fake (Client side only)
 *  - Delay (Simple but with a delay)
 *  - Spoof (Fake with velocity + packet)
 *  - Jump (Simple with jump)
 *  - Ground (Simple but only on ground)
 *  - Air (Simplebut only in air)
 *  - Packet
 * */
@ModuleInfo(description = "Automatically Attacks for you.")
public class TriggerBot extends Module {
    private final BooleanSetting Click = new BooleanSetting("Click"); //This works fine, but could be improved for low TPS
    private final NumberSetting delaySetting = new NumberSetting("Delay", 3, 0, 60, 1);
    private final BooleanSetting AutoDelay = new BooleanSetting("1.9", true); // 1.9+ AutoDelay
    private final BooleanSetting Swing = new BooleanSetting("Swing", true); // Need to Add option to cancel the onclick swing
    private final BooleanSetting autoBlock = new BooleanSetting("AutoBlock", true); // Need to Add option to cancel the onclick swing
    private final ModeSetting blockMode = new ModeSetting("AutoBlock-Mode", "Simple", "Chance", "Dev");
    private final NumberSetting autoBlockChance = new NumberSetting("AutoBlock-Chance", 100, 0, 100, 1);

    private final Random random = ThreadLocalRandom.current();
    private Entity target; // Need to Add options to target non players
    private int delay;
    private int number;

    public TriggerBot() {
        addSettings(Click, delaySetting, AutoDelay, Swing, autoBlock, blockMode, autoBlockChance);
    }

    @Listen
    public void onTick(TickEvent event) {
        target = null; //makes it not crash, why, i don't fucking know
        target = mc.targetedEntity;
        if (target == null)
            return;

        delay++; //This is the Tick Delay for the Attack Timing
        if (!mc.targetedEntity.isPlayer())
            return;

        if (!AutoDelay.isEnabled()) {
            if (Click.isEnabled() && mc.options.attackKey.isPressed() && delay >= delaySetting.getValue()) {
                if (Swing.isEnabled())
                    mc.player.swingHand(Hand.MAIN_HAND);

                mc.interactionManager.attackEntity(mc.player, target);
                autoBlock();
                delay = 0;
            }
            if (!Click.isEnabled() && delay >= delaySetting.getValue()) {
                if (Swing.isEnabled())
                    mc.player.swingHand(Hand.MAIN_HAND);

                mc.interactionManager.attackEntity(mc.player, target);
                autoBlock();
                delay = 0;
            }
        }
        if (AutoDelay.isEnabled()) {
            if (mc.player.getHealth() <= 0.0f || mc.player.getAttackCooldownProgress(0.5f) < 1.0f) return; //Why this works, idfk, it just does
            if (Click.isEnabled() && mc.options.attackKey.isPressed()) {
                if (Swing.isEnabled())
                    mc.player.swingHand(Hand.MAIN_HAND);

                mc.interactionManager.attackEntity(mc.player, target);
                autoBlock();
            }
            if (!Click.isEnabled()) {
                if (Swing.isEnabled())
                    mc.player.swingHand(Hand.MAIN_HAND);

                mc.interactionManager.attackEntity(mc.player, target);
                autoBlock();
            }
        }
    }

    public void autoBlock() {
        if (!autoBlock.isEnabled())
            return;
        if (mc.player.getMainHandStack().getItem() != Items.SHIELD)
            return;

        switch (blockMode.getMode()) {
            case "Simple" -> {
                if (mc.player.handSwingProgress == 1 || mc.player.handSwingProgress == 2 || mc.player.handSwingProgress == 3) {
                    mc.options.useKey.setPressed(true);
                }
                else if (mc.player.handSwingProgress >= 3) {
                    mc.options.useKey.setPressed(false);
                }
            }
            case "Chance" -> {
                if (mc.player.handSwingProgress == 1) {
                    number = random.nextInt(101);
                }
                if (mc.player.handSwingProgress == 1 || mc.player.handSwingProgress == 2 || mc.player.handSwingProgress == 3 && number > autoBlockChance.getValueInt()) {
                    mc.options.useKey.setPressed(true);
                }
                else if (mc.player.handSwingProgress >= 3) {
                    mc.options.useKey.setPressed(false);
                }
            }
            case "Dev" -> {
                // README: Used for testing purposes
            }
        }
    }
}