package com.clase.monkeycraft.client.gui;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.item.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class RadialMenuScreen extends Screen {
    private static final ResourceLocation RADIAL_BG = ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/gui/radial_bg.png");
    private int selectedSector = -1;
    private int maxTier = 0;

    public RadialMenuScreen() {
        super(Component.translatable("gui.monkeycraft.radial.title"));
    }

    @Override
    protected void init() {
        super.init();
        ItemStack heldItem = Minecraft.getInstance().player.getMainHandItem();
        if (heldItem.is(ModItems.MONKEY_SCEPTER_T3.get())) maxTier = 3;
        else if (heldItem.is(ModItems.MONKEY_SCEPTER_T2.get())) maxTier = 2;
        else if (heldItem.is(ModItems.MONKEY_SCEPTER_T1.get())) maxTier = 1;
        else maxTier = 0;
    }

    private int getTierForSector(int sector) {
        return switch (sector) {
            case 3 -> 0;
            case 0 -> 1;
            case 1 -> 2;
            case 2 -> 3;
            default -> -1;
        };
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        guiGraphics.fill(0, 0, this.width, this.height, 0x60000000);

        PoseStack pose = guiGraphics.pose();
        pose.pushPose();
        pose.translate(centerX, centerY, 0);
        pose.scale(1.25F, 1.25F, 1.0F);
        guiGraphics.blit(RADIAL_BG, -64, -64, 0, 0, 128, 128, 128, 128);
        pose.popPose();

        int bgScreenRadius = (int) (64 * 1.25F);

        double distance = Math.sqrt(Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2));
        if (distance < 25 || distance > bgScreenRadius + 5) {
            selectedSector = -1;
        } else {
            double angle = Math.toDegrees(Math.atan2(mouseY - centerY, mouseX - centerX));
            if (angle < 0) angle += 360;
            double adjustedAngle = (angle + 45) % 360;
            selectedSector = (int) (adjustedAngle / 90);
        }

        drawSectorItem(guiGraphics, new ItemStack(ModItems.MONKEY_SCEPTER_T0.get()), centerX, centerY, 3);
        drawSectorItem(guiGraphics, new ItemStack(ModItems.MONKEY_SCEPTER_T1.get()), centerX, centerY, 0);
        drawSectorItem(guiGraphics, new ItemStack(ModItems.MONKEY_SCEPTER_T2.get()), centerX, centerY, 1);
        drawSectorItem(guiGraphics, new ItemStack(ModItems.MONKEY_SCEPTER_T3.get()), centerX, centerY, 2);

        if (selectedSector != -1) {
            int selectedTier = getTierForSector(selectedSector);
            if (selectedTier <= maxTier) {
                guiGraphics.drawCenteredString(this.font, Component.translatable("gui.monkeycraft.radial.summon_level", selectedTier), centerX, centerY - bgScreenRadius - 20, 0xFFFFFF);
            } else {
                guiGraphics.drawCenteredString(this.font, Component.translatable("gui.monkeycraft.radial.locked", selectedTier), centerX, centerY - bgScreenRadius - 20, 0xFF5555);
            }
        }
    }

    private void drawSectorItem(GuiGraphics guiGraphics, ItemStack stack, int centerX, int centerY, int sector) {
        float angle = (float) Math.toRadians(sector * 90);

        int itemRadius = 52;

        int itemX = centerX + (int) (Math.cos(angle) * itemRadius);
        int itemY = centerY + (int) (Math.sin(angle) * itemRadius);

        int tier = getTierForSector(sector);
        boolean isUnlocked = tier <= maxTier;

        if (sector == selectedSector) {
            int highlightColor = isUnlocked ? 0x55FFFFFF : 0x55FF0000;
            guiGraphics.fill(itemX - 14, itemY - 14, itemX + 14, itemY + 14, highlightColor);
        }

        PoseStack pose = guiGraphics.pose();
        pose.pushPose();
        pose.translate(itemX, itemY, 0);
        pose.scale(1.5F, 1.5F, 1.0F);
        guiGraphics.renderItem(stack, -8, -8);
        pose.popPose();

        if (!isUnlocked) {
            guiGraphics.fill(itemX - 12, itemY - 12, itemX + 12, itemY + 12, 0xAA000000);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (selectedSector != -1 && button == 0) {
            int tier = getTierForSector(selectedSector);
            if (tier <= maxTier) {
                System.out.println("Has elegido el Mono: " + tier);
                this.minecraft.setScreen(null);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}