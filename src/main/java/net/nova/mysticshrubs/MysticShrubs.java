package net.nova.mysticshrubs;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.nova.mysticshrubs.data.DataGenerators;
import net.nova.mysticshrubs.init.MSBlocks;
import net.nova.mysticshrubs.init.MSItems;
import net.nova.mysticshrubs.init.Sounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.nova.mysticshrubs.MysticShrubs.MODID;


@Mod(MODID)
public class MysticShrubs {
    public static final String MODID = "mystic_shrubs";
    public static final Logger logger = LoggerFactory.getLogger(MysticShrubs.class);

    public MysticShrubs(IEventBus bus) {
        MSBlocks.BLOCKS.register(bus);
        MSItems.ITEMS.register(bus);
        Sounds.SOUND_EVENTS.register(bus);

        bus.addListener(DataGenerators::gatherData);
    }

    public static void playSound(Level level, Player player, SoundEvent sound) {
        if (!player.level().isClientSide) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), sound, SoundSource.PLAYERS, 0.7f, 1.0f);
        }
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
