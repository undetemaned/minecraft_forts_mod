package net.undetemaned.forts.networking.packets

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.undetemaned.forts.util.IEntityDataSaver
import net.undetemaned.forts.util.addEnergy
import net.undetemaned.forts.util.removeEnergy
import org.quiltmc.qsl.networking.api.PacketSender


object addEnergyC2SPacket {
    fun receive(server: MinecraftServer?, player: ServerPlayerEntity, handler: ServerPlayNetworkHandler?, buf: PacketByteBuf?, responseSender: PacketSender?) {
        addEnergy(player as IEntityDataSaver, 1)
    }}

object removeEnergyC2SPacket {
    fun receive(server: MinecraftServer?, player: ServerPlayerEntity, handler: ServerPlayNetworkHandler?, buf: PacketByteBuf?, responseSender: PacketSender?) {
        removeEnergy(player as IEntityDataSaver, 100)
    }}

object syncEnergyS2CPacket {
    fun receive(client: MinecraftClient, handler: ClientPlayNetworkHandler?, buf: PacketByteBuf, responseSender: PacketSender?) {
        (client.player as IEntityDataSaver).persistentData!!.putInt("energy", buf.readInt())
    }}
