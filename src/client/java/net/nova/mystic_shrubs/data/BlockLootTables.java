package net.nova.mystic_shrubs.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.nova.mystic_shrubs.block.MysticShrubBlock;
import net.nova.mystic_shrubs.init.MSBlocks;
import net.nova.mystic_shrubs.init.MSItems;

import java.util.concurrent.CompletableFuture;

public class BlockLootTables extends FabricBlockLootTableProvider {
    public BlockLootTables(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        LootItemCondition.Builder lootAgeCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(MSBlocks.MYSTIC_SHRUB).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MysticShrubBlock.AGE, 1));

        add(MSBlocks.MYSTIC_SHRUB, this.applyExplosionDecay(MSBlocks.MYSTIC_SHRUB, LootTable.lootTable()
                .withPool(LootPool.lootPool().when(lootAgeCondition)
                        .add(LootItem.lootTableItem(MSItems.HEART_DROP).setWeight(1).when(LootItemRandomChanceCondition.randomChance(0.25F)))
                        .add(LootItem.lootTableItem(MSItems.EMERALD_SHARD).setWeight(1).when(LootItemRandomChanceCondition.randomChance(0.6F)))
                        .add(LootItem.lootTableItem(Items.ARROW).setWeight(2).when(LootItemRandomChanceCondition.randomChance(0.7F)))
                )));
    }
}
