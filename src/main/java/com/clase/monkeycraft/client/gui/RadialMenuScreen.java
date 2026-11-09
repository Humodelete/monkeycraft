package com.clase.monkeycraft.client.gui;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.item.ModItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class RadialMenuScreen extends Screen {
    private static final ResourceLocation RADIAL_BG = ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/gui/radial_bg.png");
    private int selectedSector = -1;

    public RadialMenuScreen() {
        super(Component.literal("Menú de Invocación"));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int radius = 128;

        guiGraphics.fill(0, 0, this.width, this.height, 0x60000000);

        guiGraphics.blit(RADIAL_BG, centerX - radius, centerY - radius, 0, 0, radius * 2, radius * 2, radius * 2, radius * 2);

        double distance = Math.sqrt(Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2));
        if (distance < 20 || distance > radius + 25) {
            selectedSector = -1;
        } else {
            double angle = Math.toDegrees(Math.atan2(mouseY - centerY, mouseX - centerX));
            if (angle < 0) angle += 360;
            double adjustedAngle = (angle + 45) % 360;
            selectedSector = (int) (adjustedAngle / 90);
        }

        drawSectorItem(guiGraphics, new ItemStack(ModItems.MONKEY_SCEPTER_T0.get()), centerX, centerY, radius, 0); // Derecha
        drawSectorItem(guiGraphics, new ItemStack(ModItems.MONKEY_SCEPTER_T1.get()), centerX, centerY, radius, 1); // Abajo
        drawSectorItem(guiGraphics, new ItemStack(ModItems.MONKEY_SCEPTER_T2.get()), centerX, centerY, radius, 2); // Izquierda
        drawSectorItem(guiGraphics, new ItemStack(ModItems.MONKEY_SCEPTER_T3.get()), centerX, centerY, radius, 3); // Arriba

        if (selectedSector != -1) {
            guiGraphics.drawCenteredString(this.font, "Summon level " + selectedSector, centerX, centerY - radius - 20, 0xFFFFFF);
        }
    }

    private void drawSectorItem(GuiGraphics guiGraphics, ItemStack stack, int centerX, int centerY, int radius, int sector) {
        float angle = (float) Math.toRadians(sector * 90);
        int itemX = centerX + (int) (Math.cos(angle) * (radius * 0.65)) - 8;
        int itemY = centerY + (int) (Math.sin(angle) * (radius * 0.65)) - 8;

        if (sector == selectedSector) {
            guiGraphics.fill(itemX - 6, itemY - 6, itemX + 22, itemY + 22, 0x55FFFFFF);
        }

        guiGraphics.renderItem(stack, itemX, itemY);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (selectedSector != -1 && button == 0) {
            System.out.println("Has elegido el Mono: " + selectedSector);
            this.minecraft.setScreen(null);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}