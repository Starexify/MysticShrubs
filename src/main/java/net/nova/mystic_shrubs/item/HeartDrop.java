package net.nova.mystic_shrubs.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nova.mystic_shrubs.init.MSItems;
import net.nova.mystic_shrubs.init.Sounds;

public class HeartDrop extends Item {
    public HeartDrop(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        // Check if the player has less than 2 health points
        if (player.getHealth() < player.getMaxHealth() - 1.0f) {
            if (player.getItemInHand(hand).getItem().equals(MSItems.HEART_DROP.get()) && !player.isCreative()) {
                if (!level.isClientSide) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                    player.heal(2.0f);
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                } else {
                    level.playLocalSound(player.getX(), player.getY(), player.getZ(), Sounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 1.0f, 1.0f, false);
                }
            }
        }

        return InteractionResult.PASS;
    }
}
