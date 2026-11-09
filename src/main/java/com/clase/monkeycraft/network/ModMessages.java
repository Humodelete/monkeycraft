package com.clase.monkeycraft.network;

import com.clase.monkeycraft.MonkeyCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {

        INSTANCE = ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath(MonkeyCraft.MOD_ID, "messages"))
                .networkProtocolVersion(1)
                .simpleChannel();

        INSTANCE.messageBuilder(ScepterTierPacket.class, id())
                .decoder(ScepterTierPacket::new)
                .encoder(ScepterTierPacket::toBytes)
                .consumerNetworkThread(ScepterTierPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }
}