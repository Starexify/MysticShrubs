package net.nova.mysticshrubs.items;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nova.mysticshrubs.init.ModItems;
import net.nova.mysticshrubs.init.ModSounds;

public class HeartDrop extends Item {
    public HeartDrop(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // Check if the player has less than 2 health points
        if (pPlayer.getHealth() < pPlayer.getMaxHealth() - 1.0f) {
            if (pPlayer.getItemInHand(pUsedHand).getItem() == ModItems.HEART_DROP.get() && !pPlayer.isCreative()) {
                if (!pLevel.isClientSide()) {
                    // Remove the Heart Drop from the player's hand
                    pPlayer.setItemInHand(pUsedHand, ItemStack.EMPTY);

                    // Heal the player by 2 health points (one hearts)
                    pPlayer.heal(2.0f);

                    // Play sound regardless of health
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
                } else {
                    // Play sound on the client
                    pLevel.playLocalSound(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.COLLECT_HEART.get(), SoundSource.PLAYERS, 1.0f, 1.0f, false);
                }
            }
        }

        // Return pass to indicate that the interaction didn't do anything
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }
}
