package net.nova.mysticshrubs.events;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.nova.mysticshrubs.init.ModItems;
import net.nova.mysticshrubs.init.ModSounds;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

@EventBusSubscriber(modid = MODID)
public class ModEventBusEvents {

    // Player picked up item
    @SubscribeEvent
    public static void onPlayerPickUp(ItemEntityPickupEvent.Post event) {
        Player player = event.getPlayer();
        ItemStack item = event.getOriginalStack();

        if (item.is(ModItems.EMERALD_SHARD)) {
            if (!player.level().isClientSide) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.EMERALD_SHARD_PICKUP.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
            } else {
                player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.EMERALD_SHARD_PICKUP.get(), SoundSource.PLAYERS, 1.0f, 1.0f, false);
            }
        }

        if (item.is(ModItems.HEART_DROP)) {
            if (!player.level().isClientSide) {
                if (player.getHealth() < player.getMaxHealth() - 1.0f ) {

                    // Heal the player by 2 health points (one heart)
                    player.heal(2.0f);

                    // Despawn the item
                    // event.setCanceled(true);
                    // player.getInventory().removeItem(item);
                    //item.getEntityRepresentation().remove(Entity.RemovalReason.DISCARDED);
                }
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
            } else {
                player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 1.0f, 1.0f, false);
            }
        }
    }

    // On entity death spawn item
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        LevelAccessor world = event.getEntity().level();

        if (event != null && entity instanceof Monster) {
            double Random = Math.random();
            if (world instanceof ServerLevel level) {
                if (Random <= 0.05) {
                    ItemEntity entityToSpawn = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.HEART_DROP.get()));
                    entityToSpawn.setPickUpDelay(10);
                    level.addFreshEntity(entityToSpawn);
                }
                if (Random <= 0.25) {
                    ItemEntity entityToSpawn = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ModItems.EMERALD_SHARD.get()));
                    entityToSpawn.setPickUpDelay(10);
                    level.addFreshEntity(entityToSpawn);
                }
            }
        }
    }
}
