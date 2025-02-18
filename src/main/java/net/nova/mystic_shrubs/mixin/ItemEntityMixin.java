package net.nova.mystic_shrubs.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nova.mystic_shrubs.init.MSItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract ItemStack getItem();
    @Shadow
    private int pickupDelay;

    @Inject(method = "playerTouch", at = @At("HEAD"), cancellable = true)
    private void onHeartPickup(Player player, CallbackInfo ci) {
        ItemStack item = this.getItem();
        if (!this.level().isClientSide && item.is(MSItems.HEART_DROP) && this.pickupDelay == 0) {
            if (player.getHealth() < player.getMaxHealth() - 1.0f) {
                player.heal(2.0f);
                player.take(this, 1);
                this.discard();
                ci.cancel();
            }
        }
    }
}
