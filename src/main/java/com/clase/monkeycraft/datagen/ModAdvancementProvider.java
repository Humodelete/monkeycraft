package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.item.ModItems;
import com.clase.monkeycraft.block.ModBlocks;
import com.clase.monkeycraft.potion.ModPotions;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {

        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        ModItems.BANANA.get(),
                        Component.translatable("advancement.monkeycraft.root.title"),
                        Component.translatable("advancement.monkeycraft.root.desc"),
                        ResourceLocation.withDefaultNamespace("textures/block/moss_block.png"),
                        AdvancementType.TASK,
                        false, false, false
                )
                .addCriterion("tick", PlayerTrigger.TriggerInstance.tick())
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/root"));

        AdvancementHolder getBanana = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModItems.BANANA.get(),
                        Component.translatable("advancement.monkeycraft.get_banana.title"),
                        Component.translatable("advancement.monkeycraft.get_banana.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_banana", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BANANA.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/get_banana"));

        AdvancementHolder getBananaPeel = Advancement.Builder.advancement()
                .parent(getBanana)
                .display(
                        ModItems.BANANA_PEEL.get(),
                        Component.translatable("advancement.monkeycraft.get_banana_peel.title"),
                        Component.translatable("advancement.monkeycraft.get_banana_peel.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_banana_peel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BANANA_PEEL.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/get_banana_peel"));

        AdvancementHolder getMonkeyPotion = Advancement.Builder.advancement()
                .parent(getBananaPeel)
                .display(
                        Items.POTION,
                        Component.translatable("advancement.monkeycraft.get_monkey_potion.title"),
                        Component.translatable("advancement.monkeycraft.get_monkey_potion.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_monkey_potion", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(Items.POTION)
                                .hasComponents(DataComponentPredicate.builder()
                                        .expect(DataComponents.POTION_CONTENTS, new PotionContents(registries.lookupOrThrow(Registries.POTION).getOrThrow(ModPotions.MONKEY_POTION.getKey())))
                                        .build())
                                .build()
                ))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/get_monkey_potion"));

        AdvancementHolder getDoomBanana = Advancement.Builder.advancement()
                .parent(getBanana)
                .display(
                        ModItems.BANANA_OF_DOOM_AND_DESPAIR.get(),
                        Component.translatable("advancement.monkeycraft.get_doom_banana.title"),
                        Component.translatable("advancement.monkeycraft.get_doom_banana.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_doom_banana", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BANANA_OF_DOOM_AND_DESPAIR.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/get_doom_banana"));

        AdvancementHolder getDoomPotion = Advancement.Builder.advancement()
                .parent(getDoomBanana)
                .display(
                        ModItems.BANANA_OF_DOOM_AND_DESPAIR.get(),
                        Component.translatable("advancement.monkeycraft.get_doom_potion.title"),
                        Component.translatable("advancement.monkeycraft.get_doom_potion.desc"),
                        null, AdvancementType.GOAL, true, true, false
                )
                .addCriterion("has_doom_potion", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(Items.POTION)
                                .hasComponents(DataComponentPredicate.builder()
                                        .expect(DataComponents.POTION_CONTENTS, new PotionContents(registries.lookupOrThrow(Registries.POTION).getOrThrow(ModPotions.DOOM_POTION.getKey())))
                                        .build())
                                .build()
                ))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/get_doom_potion"));

        AdvancementHolder deathWish = Advancement.Builder.advancement()
                .parent(getDoomPotion)
                .display(
                        ModItems.BANANA_OF_DOOM_AND_DESPAIR.get(),
                        Component.translatable("advancement.monkeycraft.death_wish.title"),
                        Component.translatable("advancement.monkeycraft.death_wish.desc"),
                        null, AdvancementType.CHALLENGE, true, true, false
                )
                .addCriterion("eat_doom_banana", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BANANA_OF_DOOM_AND_DESPAIR.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/death_wish"));

        AdvancementHolder wearableHead = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModBlocks.MONKEY_HEAD.get().asItem(),
                        Component.translatable("advancement.monkeycraft.wearable_head.title"),
                        Component.translatable("advancement.monkeycraft.wearable_head.desc"),
                        null, AdvancementType.CHALLENGE, true, true, true
                )
                .addCriterion("has_monkey_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MONKEY_HEAD.get().asItem()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/wearable_head"));

        AdvancementHolder skullT0 = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModItems.MONKEY_SKULL_T0.get(),
                        Component.translatable("advancement.monkeycraft.skull_t0.title"),
                        Component.translatable("advancement.monkeycraft.skull_t0.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_skull_0", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SKULL_T0.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/skull_t0"));

        AdvancementHolder skullT1 = Advancement.Builder.advancement()
                .parent(skullT0)
                .display(
                        ModItems.MONKEY_SKULL_T1.get(),
                        Component.translatable("advancement.monkeycraft.skull_t1.title"),
                        Component.translatable("advancement.monkeycraft.skull_t1.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_skull_1", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SKULL_T1.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/skull_t1"));

        AdvancementHolder skullT2 = Advancement.Builder.advancement()
                .parent(skullT1)
                .display(
                        ModItems.MONKEY_SKULL_T2.get(),
                        Component.translatable("advancement.monkeycraft.skull_t2.title"),
                        Component.translatable("advancement.monkeycraft.skull_t2.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_skull_2", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SKULL_T2.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/skull_t2"));

        AdvancementHolder skullT3 = Advancement.Builder.advancement()
                .parent(skullT2)
                .display(
                        ModItems.MONKEY_SKULL_T3.get(),
                        Component.translatable("advancement.monkeycraft.skull_t3.title"),
                        Component.translatable("advancement.monkeycraft.skull_t3.desc"),
                        null, AdvancementType.GOAL, true, true, false
                )
                .addCriterion("has_skull_3", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SKULL_T3.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/skull_t3"));

        AdvancementHolder scepterT0 = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModItems.MONKEY_SCEPTER_T0.get(),
                        Component.translatable("advancement.monkeycraft.scepter_t0.title"),
                        Component.translatable("advancement.monkeycraft.scepter_t0.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_scepter_0", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SCEPTER_T0.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/scepter_t0"));

        AdvancementHolder scepterT1 = Advancement.Builder.advancement()
                .parent(scepterT0)
                .display(
                        ModItems.MONKEY_SCEPTER_T1.get(),
                        Component.translatable("advancement.monkeycraft.scepter_t1.title"),
                        Component.translatable("advancement.monkeycraft.scepter_t1.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_scepter_1", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SCEPTER_T1.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/scepter_t1"));

        AdvancementHolder scepterT2 = Advancement.Builder.advancement()
                .parent(scepterT1)
                .display(
                        ModItems.MONKEY_SCEPTER_T2.get(),
                        Component.translatable("advancement.monkeycraft.scepter_t2.title"),
                        Component.translatable("advancement.monkeycraft.scepter_t2.desc"),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_scepter_2", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SCEPTER_T2.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/scepter_t2"));

        AdvancementHolder scepterT3 = Advancement.Builder.advancement()
                .parent(scepterT2)
                .display(
                        ModItems.MONKEY_SCEPTER_T3.get(),
                        Component.translatable("advancement.monkeycraft.scepter_t3.title"),
                        Component.translatable("advancement.monkeycraft.scepter_t3.desc"),
                        null, AdvancementType.CHALLENGE, true, true, false
                )
                .addCriterion("has_scepter_3", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SCEPTER_T3.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/scepter_t3"));
    }
}