package net.nova.mysticshrubs.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static  net.nova.mysticshrubs.MysticShrubs.MODID;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> EMERALD_SHARD_PICKUP = registerSoundEvents("emerald_shard_pickup");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMERALD_SHARD_USED = registerSoundEvents("emerald_shard_used");
    public static final DeferredHolder<SoundEvent, SoundEvent> EMERALD_PIECE_USED = registerSoundEvents("emerald_piece_used");
    public static final DeferredHolder<SoundEvent, SoundEvent> COLLECT_HEART = registerSoundEvents("collect_heart");

    public static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, name)));
    }
}
