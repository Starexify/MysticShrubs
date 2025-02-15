package net.nova.mysticshrubs.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.nova.mysticshrubs.init.MSItems;
import net.nova.mysticshrubs.init.Sounds;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

@EventBusSubscriber(modid = MODID)
public class GameEvents {
    @SubscribeEvent
    public static void postPlayerPickup(ItemEntityPickupEvent.Post event) {
        Player player = event.getPlayer();
        ItemStack item = event.getOriginalStack();
        Level level = player.level();

        if (item.is(MSItems.EMERALD_SHARD) || item.is(MSItems.HEART_DROP)) {
            SoundEvent sound = item.is(MSItems.EMERALD_SHARD) ? Sounds.EMERALD_SHARD_PICKUP.get() : Sounds.COLLECT_HEART.get();
            if (!level.isClientSide) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), sound, SoundSource.PLAYERS, 0.7f, 1.0f);
            }
        }
    }

    // Heart mechanic
    @SubscribeEvent
    public static void prePlayerPickup(ItemEntityPickupEvent.Pre event) {
        ItemStack item = event.getItemEntity().getItem();
        Player player = event.getPlayer();
        Level level = player.level();

        if (item.is(MSItems.HEART_DROP) && player.getHealth() < player.getMaxHealth() - 1.0f) {
            // Heal the player by 2 health points (one heart) and remove the item
            event.setCanPickup(TriState.FALSE);
            player.heal(2.0f);
            event.getItemEntity().discard();
            if (!level.isClientSide) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 0.7f, 1.0f);
            }
        }
    }

    // Spawn hearts or emerald shards on entity death
    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        LevelAccessor world = event.getEntity().level();

        if (entity instanceof Monster) {
            double Random = Math.random();
            if (world instanceof ServerLevel level) {
                if (Random <= 0.05) {
                    ItemEntity entityToSpawn = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(MSItems.HEART_DROP.get()));
                    entityToSpawn.setPickUpDelay(10);
                    level.addFreshEntity(entityToSpawn);
                }
                if (Random <= 0.25) {
                    ItemEntity entityToSpawn = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(MSItems.EMERALD_SHARD.get()));
                    entityToSpawn.setPickUpDelay(10);
                    level.addFreshEntity(entityToSpawn);
                }
            }
        }
    }
}
