package net.undetemaned.forts

import keyBindHandler
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.undetemaned.forts.client.EnergyHudOverlay
import net.undetemaned.forts.event.ClientPlayConnectionJoin
import net.undetemaned.forts.networking.packetManager
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.networking.api.client.ClientPlayConnectionEvents
import org.slf4j.Logger
import org.slf4j.LoggerFactory


object fortsMod : ModInitializer {

    const val MOD_ID: String = "forts"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize(mod: ModContainer) {
        keyBindHandler.registerKeyInputs()
        packetManager.registerPacketManager()
        HudRenderCallback.EVENT.register(EnergyHudOverlay)
        ClientPlayConnectionEvents.JOIN.register(ClientPlayConnectionJoin)

    }
}
