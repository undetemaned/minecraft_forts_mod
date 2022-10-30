package net.undetemaned.forts.networking

import net.minecraft.util.Identifier
import net.undetemaned.forts.fortsMod
import net.undetemaned.forts.networking.packets.*
import org.quiltmc.qsl.networking.api.ServerPlayNetworking
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking

object packetManager {
    //energy packets
    val ADD_ENERGY_ID: Identifier = Identifier(fortsMod.MOD_ID, "add.energy.id")
    val REMOVE_ENERGY_ID: Identifier = Identifier(fortsMod.MOD_ID, "remove.energy.id")
    val SYNC_ENERGY_ID: Identifier = Identifier(fortsMod.MOD_ID, "sync.energy.id")
    //energy cap packets
    val ADD_ENERGY_CAP_ID: Identifier = Identifier(fortsMod.MOD_ID, "add.energy.cap.id")
    val REMOVE_ENERGY_CAP_ID: Identifier = Identifier(fortsMod.MOD_ID, "remove.energy.cap.id")
    val SYNC_ENERGY_CAP_ID: Identifier = Identifier(fortsMod.MOD_ID, "sync.energy.cap.id")
    //energy rate packets
    val ADD_ENERGY_RATE_ID: Identifier = Identifier(fortsMod.MOD_ID, "add.energy.rate.id")
    val REMOVE_ENERGY_RATE_ID: Identifier = Identifier(fortsMod.MOD_ID, "remove.energy.rate.id")
    val SYNC_ENERGY_RATE_ID: Identifier = Identifier(fortsMod.MOD_ID, "sync.energy.rate.id")

    fun registerC2SPackets() {
        //energy packets
        ServerPlayNetworking.registerGlobalReceiver(ADD_ENERGY_ID, addEnergyC2SPacket::receive)
        ServerPlayNetworking.registerGlobalReceiver(REMOVE_ENERGY_ID, removeEnergyC2SPacket::receive)
        //energy cap packets
        ServerPlayNetworking.registerGlobalReceiver(ADD_ENERGY_CAP_ID, addEnergyCapC2SPacket::receive)
        ServerPlayNetworking.registerGlobalReceiver(REMOVE_ENERGY_CAP_ID, removeEnergyCapC2SPacket::receive)
        //energy rate packets
        ServerPlayNetworking.registerGlobalReceiver(ADD_ENERGY_RATE_ID, addEnergyRateC2SPacket::receive)
        ServerPlayNetworking.registerGlobalReceiver(REMOVE_ENERGY_RATE_ID, removeEnergyRateC2SPacket::receive)

    }

    fun registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(SYNC_ENERGY_ID, syncEnergyS2CPacket::receive)
        ClientPlayNetworking.registerGlobalReceiver(SYNC_ENERGY_CAP_ID, syncEnergyCapS2CPacket::receive)
        ClientPlayNetworking.registerGlobalReceiver(SYNC_ENERGY_RATE_ID, syncEnergyRateS2CPacket::receive)
    }

    fun registerPacketManager() {
        registerC2SPackets()
        registerS2CPackets()
    }
}
