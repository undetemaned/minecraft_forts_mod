package net.undetemaned.forts.networking.packets

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.undetemaned.forts.networking.packetManager
import net.undetemaned.forts.util.*
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.PacketSender
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking


object addEnergyRateC2SPacket {
    fun receive(server: MinecraftServer?, player: ServerPlayerEntity, handler: ServerPlayNetworkHandler?, buf: PacketByteBuf?, responseSender: PacketSender?) {
        addEnergyRate(player as IEntityDataSaver, 1)
    }
}

object removeEnergyRateC2SPacket {
    fun receive(server: MinecraftServer?, player: ServerPlayerEntity, handler: ServerPlayNetworkHandler?, buf: PacketByteBuf?, responseSender: PacketSender?) {
        removeEnergyRate(player as IEntityDataSaver, 1)
    }
}

object syncEnergyRateS2CPacket {
    fun receive(client: MinecraftClient, handler: ClientPlayNetworkHandler?, buf: PacketByteBuf, responseSender: PacketSender?) {
        (client.player as IEntityDataSaver).persistentData!!.putInt("energy rate", buf.readInt())
    }
}
