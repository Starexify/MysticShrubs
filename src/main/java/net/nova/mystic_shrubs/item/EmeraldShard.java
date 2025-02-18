package net.nova.mystic_shrubs.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nova.mystic_shrubs.init.MSItems;
import net.nova.mystic_shrubs.init.Sounds;

public class EmeraldShard extends BaseItem {
    public EmeraldShard(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getResult() {
        return new ItemStack(MSItems.EMERALD_PIECE);
    }

    @Override
    public void playSound(Level level, Player player) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.EMERALD_SHARD_USED, SoundSource.PLAYERS, 0.5f, 1.0f);
    }
}
