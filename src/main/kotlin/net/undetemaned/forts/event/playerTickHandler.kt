package net.undetemaned.forts.event

import net.minecraft.server.MinecraftServer
import net.undetemaned.forts.util.IEntityDataSaver
import net.undetemaned.forts.util.addEnergy
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents


class PlayerTickHandler : ServerTickEvents.Start {
    override fun startServerTick(server: MinecraftServer?) {
        for (player in server?.playerManager!!.playerList) {
            val dataPlayer = player as IEntityDataSaver
            addEnergy(dataPlayer)
        }
    }
}
