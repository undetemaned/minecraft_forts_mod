package net.undetemaned.forts.event

import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.undetemaned.forts.networking.packetManager
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.PacketSender
import org.quiltmc.qsl.networking.api.client.ClientPlayConnectionEvents
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking

object ClientPlayConnectionJoin: ClientPlayConnectionEvents.Join {
    override fun onPlayReady(handler: ClientPlayNetworkHandler?, sender: PacketSender?, client: MinecraftClient?) {
        ClientPlayNetworking.send(packetManager.ADD_ENERGY_CAP_ID, PacketByteBufs.create())
        ClientPlayNetworking.send(packetManager.ADD_ENERGY_ID, PacketByteBufs.create())
        ClientPlayNetworking.send(packetManager.ADD_ENERGY_RATE_ID, PacketByteBufs.create())
        ServerTickEvents.START.register(PlayerTickHandler())
    }
}
