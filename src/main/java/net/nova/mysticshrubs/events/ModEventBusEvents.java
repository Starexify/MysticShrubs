package net.nova.mysticshrubs.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
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
import net.nova.mysticshrubs.init.ModItems;
import net.nova.mysticshrubs.init.ModSounds;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

@EventBusSubscriber(modid = MODID)
public class ModEventBusEvents {

    // Sounds on pickup
    @SubscribeEvent
    public static void postPlayerPickup(ItemEntityPickupEvent.Post event) {
        Player player = event.getPlayer();
        ItemStack item = event.getOriginalStack();
        Level level = player.level();

        if (item.is(ModItems.EMERALD_SHARD) || item.is(ModItems.HEART_DROP)) {
            SoundEvent sound = item.is(ModItems.EMERALD_SHARD) ? ModSounds.EMERALD_SHARD_PICKUP.get() : ModSounds.COLLECT_HEART.get();
            playSound(level, player, sound);
        }
    }

    private static void playSound(Level level, Player player, SoundEvent sound) {
        if (!player.level().isClientSide) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), sound, SoundSource.PLAYERS, 1.0f, 1.0f);
        } else {
            level.playLocalSound(player.getX(), player.getY(), player.getZ(), sound, SoundSource.PLAYERS, 1.0f, 1.0f, false);
        }
    }

    // Heart mechanic
    @SubscribeEvent
    public static void prePlayerPickup(ItemEntityPickupEvent.Pre event) {
        ItemStack item = event.getItemEntity().getItem();
        Player player = event.getPlayer();

        if (item.is(ModItems.HEART_DROP) && player.getHealth() < player.getMaxHealth() - 1.0f) {
            // Heal the player by 2 health points (one heart) and remove the item
            event.setCanPickup(TriState.FALSE);
            player.heal(2.0f);
            event.getItemEntity().discard();

            if (!player.level().isClientSide) {
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
            } else {
                player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), ModSounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 1.0f, 1.0f, false);
            }
        }
    }

    // Spawn hearts or emerald shards on entity death
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
