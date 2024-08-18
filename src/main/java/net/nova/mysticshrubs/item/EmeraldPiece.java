package net.nova.mysticshrubs.item;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.nova.mysticshrubs.init.Sounds;

public class EmeraldPiece extends BaseItem {
    public EmeraldPiece(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getResult() {
        return new ItemStack(Items.EMERALD);
    }

    @Override
    public void playSound(Level level, Player player) {
        if (!player.level().isClientSide) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), Sounds.EMERALD_PIECE_USED.get(), SoundSource.PLAYERS, 0.5f, 1.0f);
        } else {
            level.playLocalSound(player.getX(), player.getY(), player.getZ(), Sounds.EMERALD_PIECE_USED.get(), SoundSource.PLAYERS, 0.5f, 1.0f, false);
        }
    }
}
