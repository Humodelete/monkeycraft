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
                        Component.literal("MonkeyCraft"),
                        Component.literal("Welcome to the jungle."),
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
                        Component.literal("Oh, Banana!"),
                        Component.literal("Get a banana for the first time."),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_banana", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BANANA.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/get_banana"));

        AdvancementHolder getBananaPeel = Advancement.Builder.advancement()
                .parent(getBanana)
                .display(
                        ModItems.BANANA_PEEL.get(),
                        Component.literal("Slippery Business"),
                        Component.literal("Obtain a banana peel."),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_banana_peel", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BANANA_PEEL.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/get_banana_peel"));

        AdvancementHolder getMonkeyPotion = Advancement.Builder.advancement()
                .parent(getBananaPeel)
                .display(
                        Items.POTION,
                        Component.literal("Simian Agility"),
                        Component.literal("Brew the Simian Agility potion using a banana peel."),
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
                        Component.literal("Doom and Despair"),
                        Component.literal("Obtain the forbidden banana."),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_doom_banana", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BANANA_OF_DOOM_AND_DESPAIR.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/get_doom_banana"));

        AdvancementHolder getDoomPotion = Advancement.Builder.advancement()
                .parent(getDoomBanana)
                .display(
                        ModItems.BANANA_OF_DOOM_AND_DESPAIR.get(),
                        Component.literal("Taste of Doom"),
                        Component.literal("Brew the Doom potion in a brewing stand."),
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
                        Component.literal("Death Wish"),
                        Component.literal("Eat the forbidden banana and face the consequences."),
                        null, AdvancementType.CHALLENGE, true, true, false
                )
                .addCriterion("eat_doom_banana", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BANANA_OF_DOOM_AND_DESPAIR.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/death_wish"));

        AdvancementHolder wearableHead = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModBlocks.MONKEY_HEAD.get().asItem(),
                        Component.literal("Monkey See, Monkey Do"),
                        Component.literal("Obtain the wearable monkey head."),
                        null, AdvancementType.CHALLENGE, true, true, true
                )
                .addCriterion("has_monkey_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModBlocks.MONKEY_HEAD.get().asItem()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/wearable_head"));

        AdvancementHolder skullT0 = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModItems.MONKEY_SKULL_T0.get(),
                        Component.literal("Head Collector"),
                        Component.literal("Obtain your first monkey skull."),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_skull_0", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SKULL_T0.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/skull_t0"));

        AdvancementHolder skullT1 = Advancement.Builder.advancement()
                .parent(skullT0)
                .display(
                        ModItems.MONKEY_SKULL_T1.get(),
                        Component.literal("Mystic Skull"),
                        Component.literal("Upgrade the monkey skull to Tier 1."),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_skull_1", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SKULL_T1.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/skull_t1"));

        AdvancementHolder skullT2 = Advancement.Builder.advancement()
                .parent(skullT1)
                .display(
                        ModItems.MONKEY_SKULL_T2.get(),
                        Component.literal("Advanced Skull"),
                        Component.literal("Upgrade the monkey skull to Tier 2."),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_skull_2", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SKULL_T2.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/skull_t2"));

        AdvancementHolder skullT3 = Advancement.Builder.advancement()
                .parent(skullT2)
                .display(
                        ModItems.MONKEY_SKULL_T3.get(),
                        Component.literal("Master Skull"),
                        Component.literal("Upgrade the monkey skull to its final form."),
                        null, AdvancementType.GOAL, true, true, false
                )
                .addCriterion("has_skull_3", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SKULL_T3.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/skull_t3"));

        AdvancementHolder scepterT0 = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModItems.MONKEY_SCEPTER_T0.get(),
                        Component.literal("Primal Magic"),
                        Component.literal("Craft the basic monkey scepter."),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_scepter_0", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SCEPTER_T0.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/scepter_t0"));

        AdvancementHolder scepterT1 = Advancement.Builder.advancement()
                .parent(scepterT0)
                .display(
                        ModItems.MONKEY_SCEPTER_T1.get(),
                        Component.literal("Apprentice Scepter"),
                        Component.literal("Upgrade the scepter to Tier 1."),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_scepter_1", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SCEPTER_T1.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/scepter_t1"));

        AdvancementHolder scepterT2 = Advancement.Builder.advancement()
                .parent(scepterT1)
                .display(
                        ModItems.MONKEY_SCEPTER_T2.get(),
                        Component.literal("Empowered Scepter"),
                        Component.literal("Upgrade the scepter to Tier 2."),
                        null, AdvancementType.TASK, true, true, false
                )
                .addCriterion("has_scepter_2", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SCEPTER_T2.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/scepter_t2"));

        AdvancementHolder scepterT3 = Advancement.Builder.advancement()
                .parent(scepterT2)
                .display(
                        ModItems.MONKEY_SCEPTER_T3.get(),
                        Component.literal("Scepter of the Ape King"),
                        Component.literal("Unleash the full power of the Tier 3 scepter."),
                        null, AdvancementType.CHALLENGE, true, true, false
                )
                .addCriterion("has_scepter_3", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.MONKEY_SCEPTER_T3.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/scepter_t3"));
    }
}