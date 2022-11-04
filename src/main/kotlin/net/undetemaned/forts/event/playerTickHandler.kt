package net.undetemaned.forts.event


import net.undetemaned.forts.fortsMod.perSecEnergyCount
import net.undetemaned.forts.fortsMod.tickCounter
import net.undetemaned.forts.util.IEntityDataSaver
import net.undetemaned.forts.networking.packetManager
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking
import net.minecraft.client.MinecraftClient
import net.minecraft.server.MinecraftServer
import net.undetemaned.forts.util.addEnergy
import net.undetemaned.forts.util.energy.addingEnergy

class PlayerTickHandler : ServerTickEvents.Start {

    override fun startServerTick(server: MinecraftServer?) {
        for (player in server?.playerManager!!.playerList) {
            val playerData = player as IEntityDataSaver
            addingEnergy(playerData)

        }
    }
}
