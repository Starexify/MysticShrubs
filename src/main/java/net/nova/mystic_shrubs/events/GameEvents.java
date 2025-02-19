package net.nova.mystic_shrubs.events;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.nova.mystic_shrubs.init.MSItems;
import net.nova.mystic_shrubs.init.Sounds;

import static net.nova.mystic_shrubs.MysticShrubs.MODID;

@EventBusSubscriber(modid = MODID)
public class GameEvents {
    // Heart mechanic
    @SubscribeEvent
    public static void prePlayerPickup(ItemEntityPickupEvent.Pre event) {
        ItemStack item = event.getItemEntity().getItem();
        Player player = event.getPlayer();
        Level level = player.level();

        if (!event.getItemEntity().hasPickUpDelay() && item.is(MSItems.HEART_DROP) && player.getHealth() < player.getMaxHealth() - 1.0f) {
            // Heal the player by 2 health points (one heart) and remove the item
            event.setCanPickup(TriState.FALSE);
            player.heal(2.0f);
            event.getItemEntity().discard();
            if (!level.isClientSide)
                level.playSound(null, event.getItemEntity().getX(), event.getItemEntity().getY(), event.getItemEntity().getZ(), Sounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}
