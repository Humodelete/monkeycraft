package com.clase.monkeycraft;

import com.clase.monkeycraft.block.ModBlocks;
import com.clase.monkeycraft.block.entity.ModBlockEntities;
import com.clase.monkeycraft.effect.ModEffects;
import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.entity.client.*;
import com.clase.monkeycraft.item.CreativeModeModTab;
import com.clase.monkeycraft.item.ModItems;
import com.clase.monkeycraft.item.ScepterItem;
import com.clase.monkeycraft.item.custom.ModBoatItem;
import com.clase.monkeycraft.potion.ModPotions;
import com.clase.monkeycraft.sound.ModSounds;
import com.clase.monkeycraft.worldgen.tree.custom.ModTreePlacers;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import cpw.mods.util.Lazy;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

@Mod(MonkeyCraft.MOD_ID)
public class MonkeyCraft
{
    public static final String MOD_ID = "monkeycraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MonkeyCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        CreativeModeModTab.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModPotions.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
        ModSounds.register(modEventBus);
        ModTreePlacers.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modEventBus.addListener(this::registerBindings);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ModBlocks.PALM_TREE_SAPLING.get().asItem(), 0.3F);
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ModBlocks.PALM_TREE_LEAVES.get().asItem(), 0.3F);
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ModItems.BANANA.get(), 0.65F);
            net.minecraft.world.level.block.ComposterBlock.COMPOSTABLES.put(ModItems.BANANA_PEEL.get(), 0.65F);
        });
        com.clase.monkeycraft.network.ModMessages.register();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.MONKEY_SKULL_T0);
            event.accept(ModItems.MONKEY_SKULL_T1);
            event.accept(ModItems.MONKEY_SKULL_T2);
            event.accept(ModItems.MONKEY_SKULL_T3);
            event.accept(ModItems.BANANA_PEEL.get());
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ModItems.MONKEY_SPAWN_EGG.get());
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.PALM_TREE_LOG.get());
            event.accept(ModBlocks.PALM_TREE_WOOD.get());
            event.accept(ModBlocks.STRIPPED_PALM_TREE_LOG.get());
            event.accept(ModBlocks.STRIPPED_PALM_TREE_WOOD.get());
            event.accept(ModBlocks.PALM_TREE_PLANKS.get());
            event.accept(ModBlocks.PALM_TREE_DOOR.get());
            event.accept(ModBlocks.PALM_TREE_TRAPDOOR.get());
            event.accept(ModBlocks.PALM_TREE_FENCE.get());
            event.accept(ModBlocks.PALM_TREE_FENCE_GATE.get());
            event.accept(ModBlocks.PALM_TREE_STAIRS.get());
            event.accept(ModBlocks.PALM_TREE_SLAB.get());
            event.accept(ModBlocks.MONKEY_HEAD.get());
        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModItems.PALM_TREE_SIGN.get());
            event.accept(ModItems.PALM_TREE_HANGING_SIGN.get());
        }
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(ModBlocks.PALM_TREE_DOOR.get());
            event.accept(ModBlocks.PALM_TREE_TRAPDOOR.get());
            event.accept(ModBlocks.PALM_TREE_BUTTON.get());
            event.accept(ModBlocks.PALM_TREE_PRESSURE_PLATE.get());
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.MONKEY_SCEPTER_T0);
            event.accept(ModItems.MONKEY_SCEPTER_T1);
            event.accept(ModItems.MONKEY_SCEPTER_T2);
            event.accept(ModItems.MONKEY_SCEPTER_T3);
        }
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.BANANA_OF_DOOM_AND_DESPAIR.get());
            event.accept(ModItems.BANANA.get());

            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.LONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.STRONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.LONG_MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.STRONG_MONKEY_POTION.getHolder().get()));

            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.LONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.STRONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.LONG_MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.STRONG_MONKEY_POTION.getHolder().get()));

            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.LONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.STRONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.LONG_MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.STRONG_MONKEY_POTION.getHolder().get()));
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.PALM_TREE_BOAT.get());
            event.accept(ModItems.PALM_TREE_CHEST_BOAT.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            EntityRenderers.register(ModEntities.MONKEY.get(), MonkeyRenderer::new);
            EntityRenderers.register(ModEntities.MONKEY_TANK.get(), MonkeyTankRenderer::new);
            EntityRenderers.register(ModEntities.MONKEY_DART.get(), MonkeyDartRenderer::new);
            EntityRenderers.register(ModEntities.MONKEY_WIZARD.get(), MonkeyWizardRenderer::new);
            EntityRenderers.register(ModEntities.MONKEY_JESTER.get(), MonkeyJesterRenderer::new);

            event.enqueueWork(() -> {
                Sheets.addWoodType(ModBlocks.PALM_WOOD_TYPE);
            });
        }

        @SubscribeEvent
        public static void registerRenderers(net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
            event.registerEntityRenderer(ModEntities.PALM_TREE_BOAT_ENTITY.get(), context -> new ModBoatRenderer(context, false));
            event.registerEntityRenderer(ModEntities.PALM_TREE_CHEST_BOAT_ENTITY.get(), context -> new ModBoatRenderer(context, true));
            event.registerEntityRenderer(ModEntities.SUMMON_CIRCLE.get(), SummonCircleRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(new net.minecraft.client.model.geom.ModelLayerLocation(
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "boat/palm_tree"), "main"),
                    net.minecraft.client.model.BoatModel::createBodyModel);

            event.registerLayerDefinition(new net.minecraft.client.model.geom.ModelLayerLocation(
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "chest_boat/palm_tree"), "main"),
                    net.minecraft.client.model.ChestBoatModel::createBodyModel);
        }
    }

    public static final Lazy<KeyMapping> SCEPTER_KEY = Lazy.of (() ->
            new KeyMapping("key.monkeycraft.change_summon",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_R,
                    "key.categories.monkeycraft.key_category"));

    @SubscribeEvent
    public void registerBindings(RegisterKeyMappingsEvent event){
        event.register(SCEPTER_KEY.get());
        event.register(MENU_KEY.get());
    }

    public static final Lazy<KeyMapping> MENU_KEY = Lazy.of (() ->
            new KeyMapping("key.monkeycraft.open_radial",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_LEFT_ALT,
                    "key.categories.monkeycraft.key_category"));

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {

            Player player = Minecraft.getInstance().player;
            if (player == null) return;

            while (MENU_KEY.get().consumeClick()) {
                if (player.getMainHandItem().getItem() instanceof ScepterItem) {
                    Minecraft.getInstance().setScreen(new com.clase.monkeycraft.client.gui.RadialMenuScreen());
                }
            }

            while (SCEPTER_KEY.get().consumeClick()) {
                if (player.getMainHandItem().getItem() instanceof ScepterItem scepter) {
                    scepter.cycleSelectedPosition();
                }
            }
        }
    }
}