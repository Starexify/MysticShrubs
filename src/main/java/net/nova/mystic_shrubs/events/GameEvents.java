package net.nova.mystic_shrubs.events;

public class GameEvents {
    /*public static void postPlayerPickup(ItemEntityPickupEvent.Post event) {
        Player player = event.getPlayer();
        ItemStack item = event.getOriginalStack();
        Level level = player.level();

        if (item.is(MSItems.EMERALD_SHARD) || item.is(MSItems.HEART_DROP)) {
            SoundEvent sound = item.is(MSItems.EMERALD_SHARD) ? Sounds.EMERALD_SHARD_PICKUP : Sounds.COLLECT_HEART;
            if (!level.isClientSide) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), sound, SoundSource.PLAYERS, 0.7f, 1.0f);
            }
        }
    }

    // Heart mechanic
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
                level.playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.COLLECT_HEART, SoundSource.PLAYERS, 0.7f, 1.0f);
            }
        }
    }

    // Spawn hearts or emerald shards on entity death
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        LevelAccessor world = event.getEntity().level();

        if (entity instanceof Monster) {
            double Random = Math.random();
            if (world instanceof ServerLevel level) {
                if (Random <= 0.05) {
                    ItemEntity entityToSpawn = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(MSItems.HEART_DROP));
                    entityToSpawn.setPickUpDelay(10);
                    level.addFreshEntity(entityToSpawn);
                }
                if (Random <= 0.25) {
                    ItemEntity entityToSpawn = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(MSItems.EMERALD_SHARD));
                    entityToSpawn.setPickUpDelay(10);
                    level.addFreshEntity(entityToSpawn);
                }
            }
        }
    }*/
}
