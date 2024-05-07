package neptune.module.combat;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.setting.BooleanSetting;
import neptune.setting.ModeSetting;
import neptune.setting.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public class TriggerBot extends Mod {

    private Entity target; // Need to Add options to target non players
    public static int delay;
    int number;
    private final BooleanSetting Click = new BooleanSetting("Click", false); //This works fine, but could be improved for low TPS
    private final NumberSetting delaySetting = new NumberSetting("Delay", 0, 60, 3, 1);
    private final BooleanSetting AutoDelay = new BooleanSetting("1.9", true); // 1.9+ AutoDelay
    private final BooleanSetting Swing = new BooleanSetting("Swing", true); // Need to Add option to cancel the onclick swing
    private final BooleanSetting autoBlock = new BooleanSetting("AutoBlock", true); // Need to Add option to cancel the onclick swing
    //todo
    // add block modes:
    // - StrikeAC
    // - Always
    // - Look (Block when Looking at Opponent)
    // - Blink (Block when Blinking)
    // - Swing (Block after Swing)
    // - Fake (Client side Only)
    // - Delay (Simple but with a delay)
    // - Spoof (Fake with Velocity + Packet)
    // - Jump (Simple with a Jump)
    // - Ground (Simple but only on Ground)
    // - Air (Simple but only in Air)
    // - Packet
    private final ModeSetting blockMode = new ModeSetting("AutoBlock-Mode", "Simple", "Simple", "Chance", "Dev");
    private final NumberSetting autoBlockChance = new NumberSetting("AutoBlock-Chance", 0, 100, 100, 1);

    public TriggerBot() {
        super("TriggerBot", "Automatically Attacks for you.", Category.COMBAT);
        addSettings(Click, delaySetting, AutoDelay, Swing, autoBlock, blockMode, autoBlockChance);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        delay++; //This is the Tick Delay for the Attack Timing
        target = null; //makes it not crash, why, i don't fucking know
        target = mc.targetedEntity;
        if (target == null) return;
        if (mc.targetedEntity.isPlayer()) {
            if (!AutoDelay.isEnabled()) {
                if (Click.isEnabled() && mc.options.attackKey.isPressed() && delay >= delaySetting.getValue()) {
                    if (Swing.isEnabled()) {
                        mc.player.swingHand(Hand.MAIN_HAND);
                    }
                    mc.interactionManager.attackEntity(mc.player, target);
                    autoBlock();
                    delay = 0;
                }
                if (!Click.isEnabled() && delay >= delaySetting.getValue()) {
                    if (Swing.isEnabled()) {
                        mc.player.swingHand(Hand.MAIN_HAND);
                    }
                    mc.interactionManager.attackEntity(mc.player, target);
                    autoBlock();
                    delay = 0;
                }
            }
            if (AutoDelay.isEnabled()) {
                if (mc.player.getHealth() <= 0.0f || mc.player.getAttackCooldownProgress(0.5f) < 1.0f) return; //Why this works, idfk, it just does
                if (Click.isEnabled() && mc.options.attackKey.isPressed()) {
                    if (Swing.isEnabled()) {
                        mc.player.swingHand(Hand.MAIN_HAND);
                    }
                    mc.interactionManager.attackEntity(mc.player, target);
                    autoBlock();
                }
                if (!Click.isEnabled()) {
                    if (Swing.isEnabled()) {
                        mc.player.swingHand(Hand.MAIN_HAND);
                    }
                    mc.interactionManager.attackEntity(mc.player, target);
                    autoBlock();
                }
            }
        }
    }

    //Call this if you want to autoBlock
    public void autoBlock() {
        if (autoBlock.isEnabled() && mc.player.getOffHandStack().getItem() == Items.SHIELD) {
            if (blockMode.isMode("Simple")) {
                if (mc.player.handSwingProgress == 1 || mc.player.handSwingProgress == 2 || mc.player.handSwingProgress == 3) {
                    mc.options.useKey.setPressed(true);
                }
                else if (mc.player.handSwingProgress >= 3) {
                    mc.options.useKey.setPressed(false);
                }
            }
            if (blockMode.isMode("Chance")) {
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
            if (blockMode.isMode("Dev")) {
                // this is for testing
            }
        }
    }
}
