package neptune.mixins;

import neptune.features.movement.NoSlow;
import neptune.module.ModuleManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static neptune.utils.MinecraftInterface.mc;

@Mixin(value = ClientPlayerEntity.class, priority = 900)
public class ClientPlayerEntityMixin {

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"), require = 0)
    boolean fakeIsUsingItem(ClientPlayerEntity instance) {
        NoSlow noSlow = ModuleManager.getValue(NoSlow.class);
        if (this.equals(mc.player) && noSlow.isEnabled() && noSlow.eating()) {
            return false;
        }
        return instance.isUsingItem();
    }

}
