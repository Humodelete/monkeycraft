package com.clase.monkeycraft;

import com.clase.monkeycraft.block.ModBlocks;
import com.clase.monkeycraft.effect.ModEffects;
import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.entity.client.MonkeyRenderer;
import com.clase.monkeycraft.item.CreativeModeModTab;
import com.clase.monkeycraft.item.ModItems;
import com.clase.monkeycraft.item.ScepterItem;
import com.clase.monkeycraft.potion.ModPotions;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import cpw.mods.util.Lazy;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.client.renderer.entity.EntityRenderers;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MonkeyCraft.MOD_ID)
public class MonkeyCraft
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "monkeycraft";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public MonkeyCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        //Adds the creative mode tab
        CreativeModeModTab.register(modEventBus);

        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModPotions.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.addListener(this::registerBindings);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.MONKEY_SKULL_T0);
            event.accept(ModItems.MONKEY_SKULL_T1);
            event.accept(ModItems.MONKEY_SKULL_T2);
            event.accept(ModItems.MONKEY_SKULL_T3);
            event.accept(ModItems.BANANA_PEEL.get());
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
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.DOOM_POTION.getHolder().get()));

            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.LONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.LONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.LONG_DOOM_POTION.getHolder().get()));

            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.STRONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.STRONG_DOOM_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.STRONG_DOOM_POTION.getHolder().get()));

            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.MONKEY_POTION.getHolder().get()));

            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.LONG_MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.LONG_MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.LONG_MONKEY_POTION.getHolder().get()));

            event.accept(PotionContents.createItemStack(Items.POTION, ModPotions.STRONG_MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.STRONG_MONKEY_POTION.getHolder().get()));
            event.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.STRONG_MONKEY_POTION.getHolder().get()));
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            EntityRenderers.register(ModEntities.MONKEY.get(), MonkeyRenderer::new);
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
    }
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            while (SCEPTER_KEY.get().consumeClick()) {
                // Execute logic to perform on click here
                String heldItem = String.valueOf(Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem()
                        .getName(Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND)));
                /*String scepterIdentifying = "translation{key='item.monkeycraft.monkey_scepter_t%s', args=[]}";
                for(int i =0; i<4; i++){
                    scepterIdentifying = scepterIdentifying, i;
                }*/
                if(heldItem.equals("translation{key='item.monkeycraft.monkey_scepter_t0', args=[]}")
                || heldItem.equals("translation{key='item.monkeycraft.monkey_scepter_t1', args=[]}")
                || heldItem.equals("translation{key='item.monkeycraft.monkey_scepter_t2', args=[]}")
                || heldItem.equals("translation{key='item.monkeycraft.monkey_scepter_t3', args=[]}"))
                {
                    ScepterItem scepter = (ScepterItem)Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
                    scepter.cicleSelectedPosition();
                }
            }
        }
    }
}
