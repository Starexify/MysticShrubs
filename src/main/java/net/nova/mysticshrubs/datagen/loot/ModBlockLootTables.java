package net.nova.mysticshrubs.datagen.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.nova.mysticshrubs.MysticShrubs;
import net.nova.mysticshrubs.blocks.MysticShrubBlock;
import net.nova.mysticshrubs.init.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.MYSTIC_SHRUB.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MysticShrubBlock.AGE, 1));

        this.add(ModBlocks.MYSTIC_SHRUB.get(), this.applyExplosionDecay(ModBlocks.MYSTIC_SHRUB.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool().when(lootitemcondition$builder)
                                .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(MysticShrubs.rl("heart_drop")))
                                        .when(LootItemRandomChanceCondition.randomChance(0.25F))
                                        .setWeight(1))

                                .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(MysticShrubs.rl("emerald_shard")))
                                        .when(LootItemRandomChanceCondition.randomChance(0.6F))
                                        .setWeight(1))

                                .add(LootItem.lootTableItem(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("minecraft", "arrow")))
                                        .when(LootItemRandomChanceCondition.randomChance(0.7F))
                                        .setWeight(2))
                        )));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
                        .filter(key -> key.getNamespace().equals(MODID))
                        .isPresent())
                .collect(Collectors.toSet());
    }
}
