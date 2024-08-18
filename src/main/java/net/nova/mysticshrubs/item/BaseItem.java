package net.nova.mysticshrubs.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BaseItem extends Item {
    protected final int requiredCount = 8;

    public BaseItem(Properties pProperties) {
        super(pProperties);
    }

    public ItemStack getResult() {
        return null;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
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

            if (!player.getInventory().add(getResult())) {
                player.drop(getResult(), false);
            }

            playSound(level, player);

            return InteractionResultHolder.success(player.getItemInHand(usedHand));
        }
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    public void playSound(Level level, Player player) {}
}
