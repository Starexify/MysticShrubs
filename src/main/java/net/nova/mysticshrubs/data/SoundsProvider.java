package net.nova.mysticshrubs.data;

import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.nova.mysticshrubs.init.Sounds;

import java.util.function.Supplier;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class SoundsProvider extends SoundDefinitionsProvider {
    public SoundsProvider(PackOutput output) {
        super(output, MODID);
    }

    @Override
    public void registerSounds() {
        addSound(Sounds.EMERALD_SHARD_PICKUP);
        addSound(Sounds.EMERALD_SHARD_USED);
        addSound(Sounds.EMERALD_PIECE_USED);
        addSound(Sounds.COLLECT_HEART);
    }

    // Some methods for simpler generation
    protected void addSound(final Supplier<SoundEvent> soundEvent) {
        this.add(soundEvent.get(), definition()
                .subtitle(getSubtitle(soundEvent))
                .with(sound(soundEvent.get().location().toString())));
    }

    public static String getSubtitle(Supplier<SoundEvent> soundEvent) {
        return "sounds." + MODID + "." + soundEvent.get().location().getPath();
    }
}
