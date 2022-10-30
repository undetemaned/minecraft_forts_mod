package net.undetemaned.forts.util

import net.minecraft.network.PacketByteBuf

import net.minecraft.server.network.ServerPlayerEntity
import net.undetemaned.forts.networking.packetManager
import net.undetemaned.forts.networking.packets.syncEnergyS2CPacket
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.ServerPlayNetworking


fun addEnergyRate(player: IEntityDataSaver, amount: Int): Int {
    val nbt = player.persistentData
    var energyRate = nbt!!.getInt("energy rate")

    when(energyRate + amount >= 1000) {
        true -> energyRate = 1000
        false -> energyRate += amount
    }

    nbt.putInt("energy rate", energyRate)
    syncEnergyRate(energyRate, player as ServerPlayerEntity)
    return energyRate
}

fun removeEnergyRate(player: IEntityDataSaver, amount: Int): Int {
    val nbt = player.persistentData
    var energyRate = nbt!!.getInt("energy rate")

    when(energyRate - amount <= 0) {
        true -> energyRate = 0
        false -> energyRate -= amount
    }

    nbt.putInt("energy rate", energyRate)
    syncEnergyRate(energyRate, player as ServerPlayerEntity)
    return energyRate
}

fun syncEnergyRate(energyRate: Int, player: ServerPlayerEntity?) {
    val buffer: PacketByteBuf = PacketByteBufs.create()
    buffer.writeInt(energyRate)
    ServerPlayNetworking.send(player, packetManager.SYNC_ENERGY_RATE_ID, buffer)
}
