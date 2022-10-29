package net.undetemaned.forts.util

import net.minecraft.nbt.NbtCompound


interface IEntityDataSaver {
    val persistentData: NbtCompound?
}
