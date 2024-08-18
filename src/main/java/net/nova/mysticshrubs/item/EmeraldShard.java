package net.nova.mysticshrubs.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nova.mysticshrubs.MysticShrubs;
import net.nova.mysticshrubs.init.MSItems;
import net.nova.mysticshrubs.init.Sounds;

public class EmeraldShard extends BaseItem {
    public EmeraldShard(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getResult() {
        return new ItemStack(MSItems.EMERALD_PIECE.get());
    }

    @Override
    public void playSound(Level level, Player player) {
        if (!player.level().isClientSide) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.EMERALD_SHARD_USED.get(), SoundSource.PLAYERS, 0.5f, 1.0f);
        } else {
            level.playLocalSound(player.getX(), player.getY(), player.getZ(), Sounds.EMERALD_SHARD_USED.get(), SoundSource.PLAYERS, 0.5f, 1.0f, false);
        }
    }
}
