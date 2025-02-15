package net.nova.mysticshrubs.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class BaseItem extends Item {
    protected final int requiredCount = 8;

    public BaseItem(Properties properties) {
        super(properties);
    }

    public abstract void playSound(Level level, Player player);
    public abstract ItemStack getResult();

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack currentItem = new ItemStack(this);
        int itemCount = 0;

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.is(currentItem.getItem())) {
                itemCount += stack.getCount();
            }
        }
        if (itemCount >= requiredCount) {
            if (!player.isCreative()) {
                int removedCount = 0;
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack stack = player.getInventory().getItem(i);
                    if (stack.is(currentItem.getItem())) {
                        int toRemove = Math.min(stack.getCount(), requiredCount - removedCount);
                        stack.shrink(toRemove);
                        removedCount += toRemove;
                        if (removedCount >= requiredCount) {
                            break;
                        }
                    }
                }
            }

            if (!player.getInventory().add(getResult())) player.drop(getResult(), false);
            if (!level.isClientSide) playSound(level, player);

            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
