package net.nova.mysticshrubs.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.nova.mysticshrubs.MysticShrubs;

public class Sounds {
    public static final SoundEvent EMERALD_SHARD_PICKUP = registerSoundEvents("emerald_shard_pickup");
    public static final SoundEvent EMERALD_SHARD_USED = registerSoundEvents("emerald_shard_used");
    public static final SoundEvent EMERALD_PIECE_USED = registerSoundEvents("emerald_piece_used");
    public static final SoundEvent COLLECT_HEART = registerSoundEvents("collect_heart");

    public static SoundEvent registerSoundEvents(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, MysticShrubs.rl(name), SoundEvent.createVariableRangeEvent(MysticShrubs.rl(name)));
    }

    public static void initialize() {
        MysticShrubs.LOGGER.info("Registering Sounds");
    }
}
