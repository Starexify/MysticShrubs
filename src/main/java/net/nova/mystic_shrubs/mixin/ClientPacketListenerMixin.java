package net.nova.mystic_shrubs.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.nova.mystic_shrubs.init.MSItems;
import net.nova.mystic_shrubs.init.Sounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @ModifyArgs(method = "handleTakeItemEntity",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;playLocalSound(DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V"),
            slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/sounds/SoundEvents;ITEM_PICKUP:Lnet/minecraft/sounds/SoundEvent;"))
    )
    private void pickupItemSound(Args args, @Local Entity entity, @Local LivingEntity livingEntity) {
        if (entity instanceof ItemEntity itemEntity) {
            ItemStack item = itemEntity.getItem();
            if (item.is(MSItems.EMERALD_SHARD) || item.is(MSItems.HEART_DROP)) {
                args.set(3, item.is(MSItems.EMERALD_SHARD) ? Sounds.EMERALD_SHARD_PICKUP.get() : Sounds.COLLECT_HEART.get());
                args.set(5, 1.0f);
                args.set(6, 1.0f);
            }
        }
    }
}
