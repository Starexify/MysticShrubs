package net.nova.mystic_shrubs.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.nova.mystic_shrubs.init.MSItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "dropCustomDeathLoot", at = @At("TAIL"))
    private void dropMysticShrubsLoot(ServerLevel serverLevel, DamageSource damageSource, boolean bl, CallbackInfo ci) {
        if ((Object) this instanceof Monster) {
            RandomSource random = serverLevel.getRandom();
            if (random.nextDouble() <= 0.05) spawnAtLocation(serverLevel, MSItems.HEART_DROP);
            if (random.nextDouble() <= 0.25) spawnAtLocation(serverLevel, MSItems.EMERALD_SHARD);
        }
    }
}