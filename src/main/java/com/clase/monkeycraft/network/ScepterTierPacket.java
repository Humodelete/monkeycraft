package com.clase.monkeycraft.network;

import com.clase.monkeycraft.item.ScepterItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class ScepterTierPacket {
    private final int selectedTier;

    public ScepterTierPacket(int selectedTier) {
        this.selectedTier = selectedTier;
    }

    public ScepterTierPacket(FriendlyByteBuf buffer) {
        this.selectedTier = buffer.readInt();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(this.selectedTier);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            Player sender = context.getSender();
            if (sender instanceof ServerPlayer player) {
                ItemStack mainHand = player.getMainHandItem();
                if (mainHand.getItem() instanceof ScepterItem scepter) {
                    scepter.setSelectedPosition(this.selectedTier, player);
                }
            }
        });
        context.setPacketHandled(true);
    }
}