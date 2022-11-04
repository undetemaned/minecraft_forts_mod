package net.undetemaned.forts.util.energy

import net.minecraft.server.network.ServerPlayerEntity
import net.undetemaned.forts.fortsMod.perSecEnergyCount
import net.undetemaned.forts.fortsMod.tickCounter
import net.undetemaned.forts.util.IEntityDataSaver
import net.undetemaned.forts.util.syncEnergy

private var amountFor20: Int = 0
private var amountFor10: Int = 0
private var amountFor5: Int = 0
private var amountFor2: Int = 0
private var leftover: Int = 0
fun addingEnergy(player: IEntityDataSaver) {
    //values
    val nbt = player.persistentData
    val cap = nbt.getInt("energy cap")
    val rate = nbt.getInt("energy rate")

    //variables
    var energy = nbt.getInt("energy")

    energy += amountFor20

    if (tickCounter % 10 == 0) {
        energy += amountFor10
    }
    if (tickCounter % 5 == 0) {
        energy += amountFor5
    }
    if (tickCounter % 2 == 0) {
        energy += amountFor2
    }


    if (tickCounter >= 20) {
        energy = leftover
        tickCounter = 0
        perSecEnergyCount = rate

        amountFor20 = perSecEnergyCount/20
        perSecEnergyCount -= amountFor20*20

        amountFor10 = perSecEnergyCount/10
        perSecEnergyCount -= amountFor20*10

        amountFor5 = perSecEnergyCount/5
        perSecEnergyCount -= amountFor20*5

        amountFor2 = perSecEnergyCount/2
        perSecEnergyCount -= amountFor20*2

        leftover = perSecEnergyCount
    }
    if (energy > cap) {
        energy = cap
    }
    nbt.putInt("energy", energy)
    syncEnergy(energy, player as ServerPlayerEntity)
    tickCounter = tickCounter++
}
