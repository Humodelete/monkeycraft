package com.clase.monkeycraft.util;

import javax.swing.text.JTextComponent;

public class KeyBindingList {
    public static JTextComponent.KeyBinding[] ModKeyBindings;

    public static void register(){
        ModKeyBindings = new JTextComponent.KeyBinding[1];

        ModKeyBindings[0] = new JTextComponent.KeyBinding("key.monkeycraft.selector", 342, "key.monkeycraft.category");
    }
}
