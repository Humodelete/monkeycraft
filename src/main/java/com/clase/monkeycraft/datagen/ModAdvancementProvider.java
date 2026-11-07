package com.clase.monkeycraft.datagen;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.BrewedPotionTrigger;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {

        AdvancementHolder root = Advancement.Builder.advancement()
                .display(
                        ModItems.BANANA.get(),
                        Component.literal("¡Oh, plátano!"),
                        Component.literal("Consigue un plátano por primera vez."),
                        ResourceLocation.withDefaultNamespace("textures/gui/advancements/backgrounds/jungle.png"),
                        AdvancementType.TASK,
                        true, true, false
                )
                .addCriterion("has_banana", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BANANA.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/root"));

        AdvancementHolder doomBanana = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ModItems.BANANA_OF_DOOM_AND_DESPAIR.get(),
                        Component.literal("Deseos de Morir"),
                        Component.literal("Cómete el plátano prohibido."),
                        null,
                        AdvancementType.CHALLENGE,
                        true, true, false
                )
                .addCriterion("eat_doom_banana", ConsumeItemTrigger.TriggerInstance.usedItem(ModItems.BANANA_OF_DOOM_AND_DESPAIR.get()))
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/doom_banana"));

        AdvancementHolder doomPotion = Advancement.Builder.advancement()
                .parent(doomBanana)
                .display(
                        ModItems.BANANA_OF_DOOM_AND_DESPAIR.get(),
                        Component.literal("Sabor a Perdición"),
                        Component.literal("Fabrica la poción de Doom en un soporte para pociones."),
                        null,
                        AdvancementType.GOAL, // Azul
                        true, true, false
                )
                .addCriterion("brew_doom_potion", BrewedPotionTrigger.TriggerInstance.brewedPotion())
                .save(saver, ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "monkeycraft/doom_potion"));
    }
}