package com.clase.monkeycraft.client.gui;

import com.clase.monkeycraft.MonkeyCraft;
import com.clase.monkeycraft.entity.ModEntities;
import com.clase.monkeycraft.item.ModItems;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class RadialMenuScreen extends Screen {
    private static final ResourceLocation RADIAL_BG = ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/gui/radial_bg.png");
    private static final ResourceLocation RADIAL_HOVER = ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "textures/gui/radial_hover.png");

    private int selectedSector = -1;
    private int maxTier = 0;

    private LivingEntity dummyT0;
    private LivingEntity dummyT1;
    private LivingEntity dummyT2;
    private LivingEntity dummyT3;

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

        if (this.minecraft.level != null) {
            dummyT0 = ModEntities.MONKEY_DART.get().create(this.minecraft.level);
            dummyT1 = ModEntities.MONKEY_WIZARD.get().create(this.minecraft.level);
            dummyT2 = ModEntities.MONKEY_TANK.get().create(this.minecraft.level);
            dummyT3 = ModEntities.MONKEY_JESTER.get().create(this.minecraft.level);
        }
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
        int bgSize = 256;
        int halfBg = bgSize / 2;

        guiGraphics.fill(0, 0, this.width, this.height, 0x60000000);

        guiGraphics.blit(RADIAL_BG, centerX - halfBg, centerY - halfBg, 0, 0, bgSize, bgSize, bgSize, bgSize);

        double distance = Math.sqrt(Math.pow(mouseX - centerX, 2) + Math.pow(mouseY - centerY, 2));

        if (distance < 15 || distance > halfBg + 5) {
            selectedSector = -1;
        } else {
            double angle = Math.toDegrees(Math.atan2(mouseY - centerY, mouseX - centerX));
            if (angle < 0) angle += 360;
            double adjustedAngle = (angle + 45) % 360;
            selectedSector = (int) (adjustedAngle / 90);
        }

        if (selectedSector != -1) {
            int selectedTier = getTierForSector(selectedSector);

            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(centerX, centerY, 0);

            guiGraphics.pose().mulPose(Axis.ZP.rotationDegrees(selectedSector * 90));

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            if (selectedTier <= maxTier) {
                guiGraphics.setColor(1.0F, 1.0F, 1.0F, 0.2F);
            } else {
                guiGraphics.setColor(1.0F, 0.2F, 0.2F, 0.3F);
            }

            guiGraphics.blit(RADIAL_HOVER, -128, -128, 0, 0, 256, 256, 256, 256);

            RenderSystem.disableBlend();
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            guiGraphics.pose().popPose();
        }

        drawSectorEntity(guiGraphics, dummyT0, centerX, centerY, 3, mouseX, mouseY);
        drawSectorEntity(guiGraphics, dummyT1, centerX, centerY, 0, mouseX, mouseY);
        drawSectorEntity(guiGraphics, dummyT2, centerX, centerY, 1, mouseX, mouseY);
        drawSectorEntity(guiGraphics, dummyT3, centerX, centerY, 2, mouseX, mouseY);

        if (selectedSector != -1) {
            int selectedTier = getTierForSector(selectedSector);
            if (selectedTier <= maxTier) {
                guiGraphics.drawCenteredString(this.font, Component.translatable("gui.monkeycraft.radial.summon_level", selectedTier), centerX, centerY - halfBg - 20, 0xFFFFFF);
            } else {
                guiGraphics.drawCenteredString(this.font, Component.translatable("gui.monkeycraft.radial.locked", selectedTier), centerX, centerY - halfBg - 20, 0xFF5555);
            }
        }
    }

    private void drawSectorEntity(GuiGraphics guiGraphics, LivingEntity entity, int centerX, int centerY, int sector, int mouseX, int mouseY) {
        if (entity == null) return;

        float angle = (float) Math.toRadians(sector * 90);

        int itemRadius = 55;
        int offsetY = 0;
        int scale = 30;

        // Configurador individual por sector para ajustes de posición y TAMAÑO
        switch (sector) {
            case 3: // Mono Dardo (Arriba)
                itemRadius = 55;
                offsetY = 5;
                scale = 36;
                break;
            case 0: // Mono Mago (Derecha)
                itemRadius = 65;
                offsetY = 8;
                scale = 41;
                break;
            case 1: // Mono Tanque (Abajo)
                itemRadius = 55;
                offsetY = 12;
                scale = 24;
                break;
            case 2: // Mono Bufón (Izquierda)
                itemRadius = 65;
                offsetY = 8;
                scale = 36;
                break;
        }

        int itemX = centerX + (int) (Math.cos(angle) * itemRadius);
        int itemY = centerY + (int) (Math.sin(angle) * itemRadius) + offsetY;

        int tier = getTierForSector(sector);
        boolean isUnlocked = tier <= maxTier;

        if (!isUnlocked) {
            guiGraphics.setColor(0.0F, 0.0F, 0.0F, 1.0F);
        }

        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, itemX - 40, itemY - 50, itemX + 40, itemY + 30, scale, 0.0f, mouseX, mouseY, entity);

        if (!isUnlocked) {
            guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
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
                com.clase.monkeycraft.network.ModMessages.sendToServer(new com.clase.monkeycraft.network.ScepterTierPacket(tier));
                this.minecraft.setScreen(null);
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
}