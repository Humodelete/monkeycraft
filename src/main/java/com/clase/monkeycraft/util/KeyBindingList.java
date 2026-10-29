package com.clase.monkeycraft.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.JTextComponent;

@Mod.EventBusSubscriber
public class KeyBindingList {
    private static final KeyMapping SCEPTER_KEY = new KeyMapping("key.monkeycraft.change_summon",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "key.categories.monkeycraft.key_category");

    @SubscribeEvent
    public static void registerKeyBinds (FMLClientSetupEvent event){

    }
}
