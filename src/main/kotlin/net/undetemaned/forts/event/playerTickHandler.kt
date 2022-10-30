package net.undetemaned.forts.event

import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import net.undetemaned.forts.networking.packetManager
import net.undetemaned.forts.util.IEntityDataSaver
import net.undetemaned.forts.util.addEnergy
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking

class PlayerTickHandler: ClientTickEvents.Start {
    override fun startClientTick(client: MinecraftClient?) {
        ClientPlayNetworking.send(packetManager.ADD_ENERGY_RATE_ID, PacketByteBufs.create())
    }
}
