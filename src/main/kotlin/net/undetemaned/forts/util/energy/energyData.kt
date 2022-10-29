package net.undetemaned.forts.util

import net.minecraft.network.PacketByteBuf

import net.minecraft.server.network.ServerPlayerEntity
import net.undetemaned.forts.networking.packetManager
import net.undetemaned.forts.networking.packets.syncEnergyS2CPacket
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.ServerPlayNetworking


fun addEnergy(player: IEntityDataSaver, amount: Int): Int {
    val nbt = player.persistentData
    var energy = nbt!!.getInt("energy")
    var cap = nbt!!.getInt("energy cap")

    when(energy + amount >= cap) {
        true -> energy = cap
        false -> energy += amount
    }

    nbt.putInt("energy", energy)
    syncEnergy(energy, player as ServerPlayerEntity)
    return energy
}

fun removeEnergy(player: IEntityDataSaver, amount: Int): Int {
    val nbt = player.persistentData
    var energy = nbt!!.getInt("energy")

    when(energy - amount <= 0) {
        true -> energy = 0
        false -> energy -= amount
    }

    nbt.putInt("energy", energy)
    syncEnergy(energy, player as ServerPlayerEntity)
    return energy
}

fun syncEnergy(energy: Int, player: ServerPlayerEntity?) {
    val buffer: PacketByteBuf = PacketByteBufs.create()
    buffer.writeInt(energy)
    ServerPlayNetworking.send(player, packetManager.SYNC_ENERGY_ID, buffer)
}
