package net.nova.mystic_shrubs.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.nova.mysticshrubs.MysticShrubs;
import net.nova.mysticshrubs.init.Sounds;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class SoundsProvider implements DataProvider {
    public final FabricDataOutput output;
    public final Map<ResourceLocation, JsonObject> sounds = new HashMap<>();

    public SoundsProvider(FabricDataOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        addSound(Sounds.EMERALD_SHARD_PICKUP);
        addSound(Sounds.EMERALD_SHARD_USED);
        addSound(Sounds.EMERALD_PIECE_USED);
        addSound(Sounds.COLLECT_HEART);

        JsonObject json = new JsonObject();
        sounds.forEach((id, definition) -> json.add(id.getPath(), definition));

        return DataProvider.saveStable(cachedOutput, json,
                output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "").json(MysticShrubs.rl("sounds")));
    }

    public void addSound(SoundEvent soundEvent) {
        JsonObject definition = new JsonObject();
        JsonArray soundsArray = new JsonArray();

        soundsArray.add(new JsonPrimitive(soundEvent.location().toString()));
        definition.add("sounds", soundsArray);
        definition.addProperty("subtitle", getSubtitle(soundEvent));
        sounds.put(soundEvent.location(), definition);
    }

    public static String getSubtitle(SoundEvent soundEvent) {
        return "sounds." + MODID + "." + soundEvent.location();
    }

    @Override
    public String getName() {
        return "MS SoundsGenerator";
    }
}
