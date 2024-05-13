package neptune.mixins;

import neptune.Neptune;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import neptune.command.Command;
import neptune.command.CommandManager;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    // TODO: make a randomized prefix and make the rnadomized string prefix thing in gui
    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(String msg, CallbackInfo ci) {

        StringBuilder CMD = new StringBuilder();
        for (int i = 1; i < msg.toCharArray().length; ++i) {
            CMD.append(msg.toCharArray()[i]);
        }
        String[] args = CMD.toString().split(" ");

        if (msg.startsWith(Neptune.getInstance().getCommandManager().getCommandPrefix())) {
            for (Command command : CommandManager.INSTANCE.getCmds()) {
                if (args[0].equalsIgnoreCase(command.getName())) {
                    command.onCmd(msg, args);
                    ci.cancel();
                    break;
                }
            }
        }
    }
}