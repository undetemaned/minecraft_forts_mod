package net.undetemaned.forts.util

import net.minecraft.network.PacketByteBuf

import net.minecraft.server.network.ServerPlayerEntity
import net.undetemaned.forts.networking.packetManager
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.ServerPlayNetworking


fun addEnergyCap(player: IEntityDataSaver, amount: Int): Int {
    val nbt = player.persistentData
    var energyCap = nbt!!.getInt("energy cap")

    when(energyCap + amount >= 10000) {
        true -> energyCap = 10000
        false -> energyCap += amount
    }

    nbt.putInt("energy cap", energyCap)
    syncEnergyCap(energyCap, player as ServerPlayerEntity)
    return energyCap
}

fun removeEnergyCap(player: IEntityDataSaver, amount: Int): Int {
    val nbt = player.persistentData
    var energyCap = nbt!!.getInt("energy cap")
    var energy = nbt.getInt("energy")

    when(energyCap - amount <= 0) {
        true -> energyCap = 0
        false -> energyCap -= amount
    }

    if (energy > energyCap) {
        energy = energyCap
    }

    nbt.putInt("energy cap", energyCap)
    nbt.putInt("energy", energy)
    syncEnergyCap(energyCap, player as ServerPlayerEntity)
    syncEnergy(energy, player as ServerPlayerEntity)
    return energyCap
}

fun syncEnergyCap(energy: Int, player: ServerPlayerEntity?) {
    val buffer: PacketByteBuf = PacketByteBufs.create()
    buffer.writeInt(energy)
    ServerPlayNetworking.send(player, packetManager.SYNC_ENERGY_CAP_ID, buffer)
}
