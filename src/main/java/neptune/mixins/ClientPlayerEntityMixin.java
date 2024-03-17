package neptune.mixins;

import neptune.module.ModuleManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static neptune.utils.MinecraftInterface.mc;

@Mixin(value = ClientPlayerEntity.class, priority = 900)
public class ClientPlayerEntityMixin {

}
