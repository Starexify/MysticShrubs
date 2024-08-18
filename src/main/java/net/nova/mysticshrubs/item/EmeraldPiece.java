package net.nova.mysticshrubs.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.nova.mysticshrubs.init.MSItems;
import net.nova.mysticshrubs.init.Sounds;

public class EmeraldPiece extends Item {
    public EmeraldPiece(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // Check if the player has at least 8 Emerald Shards
        ItemStack emeraldPiece = new ItemStack(MSItems.EMERALD_PIECE.get());
        int emeraldPieceCount = 0;
        for (int i = 0; i < pPlayer.getInventory().getContainerSize(); i++) {
            ItemStack stack = pPlayer.getInventory().getItem(i);
            if (stack.is(emeraldPiece.getItem())) {
                emeraldPieceCount += stack.getCount();
            }
        }

        // If the player has at least 8 Emerald Shards, remove them and give them an Emerald Piece
        if (emeraldPieceCount >= 8) {
            // Remove 8 Emerald Shards from the player's inventory (only survival)
            if (!pPlayer.isCreative()) {
                int removedCount = 0;
                for (int i = 0; i < pPlayer.getInventory().getContainerSize(); i++) {
                    ItemStack stack = pPlayer.getInventory().getItem(i);
                    if (stack.is(emeraldPiece.getItem())) {
                        int toRemove = Math.min(stack.getCount(), 8 - removedCount);
                        stack.shrink(toRemove);
                        removedCount += toRemove;
                        if (removedCount >= 8) {
                            break;
                        }
                    }
                }
            }

            // Give the player an Emerald Piece
            ItemStack giveEmerald = new ItemStack(Items.EMERALD);
            pPlayer.addItem(giveEmerald);

            // Play sound
            if (!pLevel.isClientSide()) {
                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), Sounds.EMERALD_PIECE_USED.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
            } else {
                pLevel.playLocalSound(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), Sounds.EMERALD_PIECE_USED.get(), SoundSource.PLAYERS, 1.0f, 1.0f, false);
            }

            // Return success to indicate that the interaction was successful
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        }

        // Return pass to indicate that the interaction didn't do anything
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }
}
