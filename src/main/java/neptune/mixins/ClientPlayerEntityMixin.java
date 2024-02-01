package neptune.mixins;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ClientPlayerEntity.class, priority = 900)
public class ClientPlayerEntityMixin {
}
