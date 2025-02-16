package net.nova.mysticshrubs.data.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.nova.mysticshrubs.block.MysticShrubBlock;
import net.nova.mysticshrubs.init.MSBlocks;
import net.nova.mysticshrubs.init.MSItems;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static net.nova.mysticshrubs.MysticShrubs.MODID;

public class BlockLootTables extends BlockLootSubProvider {
    public BlockLootTables(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder lootAgeCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(MSBlocks.MYSTIC_SHRUB.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MysticShrubBlock.AGE, 1));

        add(MSBlocks.MYSTIC_SHRUB.get(), this.applyExplosionDecay(MSBlocks.MYSTIC_SHRUB.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootAgeCondition)
                        .add(LootItem.lootTableItem(MSItems.HEART_DROP).setWeight(1).when(LootItemRandomChanceCondition.randomChance(0.25F)))
                        .add(LootItem.lootTableItem(MSItems.EMERALD_SHARD).setWeight(1).when(LootItemRandomChanceCondition.randomChance(0.6F)))
                        .add(LootItem.lootTableItem(Items.ARROW).setWeight(2).when(LootItemRandomChanceCondition.randomChance(0.7F)))
                )));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream().filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
                .filter(key -> key.getNamespace().equals(MODID))
                .isPresent()).collect(Collectors.toSet());
    }
}
