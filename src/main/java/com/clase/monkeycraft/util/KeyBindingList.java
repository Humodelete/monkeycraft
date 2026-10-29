package com.clase.monkeycraft.util;

import com.clase.monkeycraft.MonkeyCraft;
import com.mojang.blaze3d.platform.InputConstants;
import cpw.mods.util.Lazy;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.JTextComponent;

@Mod.EventBusSubscriber(modid = MonkeyCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindingList {
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
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("R Key Press"));

            }
        }
    }
}
