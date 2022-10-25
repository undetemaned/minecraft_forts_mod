package net.undetemaned.forts

import keyBindHandler
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object fortsMod : ModInitializer {

    const val MOD_ID: String = "forts"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize(mod: ModContainer) {
    keyBindHandler.registerKeyInputs()
    }
}
