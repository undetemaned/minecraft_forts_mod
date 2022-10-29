import com.mojang.blaze3d.platform.InputUtil
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.option.KeyBind
import net.minecraft.predicate.entity.DistancePredicate.y
import net.minecraft.text.Text
import net.undetemaned.forts.networking.packetManager
import net.undetemaned.forts.util.IEntityDataSaver
import org.lwjgl.glfw.GLFW
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking


object keyBindHandler {
    val glfw = GLFW.GLFW_KEY_0

    val KEY_CATEGORY_FORTS = "key.category.forts.forts"

    //energy keyBinds
    val KEY_ADD_ENERGY = "key.forts.add.energy"
    val addEnergyKey: KeyBind? = registerKeyBind(KEY_CATEGORY_FORTS, KEY_ADD_ENERGY, 334)

    val KEY_REMOVE_ENERGY = "key.forts.remove.energy"
    val removeEnergyKey: KeyBind? = registerKeyBind(KEY_CATEGORY_FORTS, KEY_REMOVE_ENERGY, 333)


    //energy cap keyBinds
    val KEY_ADD_ENERGY_CAP = "key.forts.add.energy.cap"
    val addEnergyCapKey: KeyBind? = registerKeyBind(KEY_CATEGORY_FORTS, KEY_ADD_ENERGY_CAP, 334)

    val KEY_REMOVE_ENERGY_CAP = "key.forts.remove.energy.cap"
    val removeEnergyCapKey: KeyBind? = registerKeyBind(KEY_CATEGORY_FORTS, KEY_REMOVE_ENERGY_CAP, 333)

    fun registerKeyInputs() {
        ClientTickEvents.END.register { client ->
            if (addEnergyKey!!.wasPressed()) {
                ClientPlayNetworking.send(packetManager.ADD_ENERGY_ID, PacketByteBufs.create());
            }
            if (removeEnergyKey!!.wasPressed()) {
                ClientPlayNetworking.send(packetManager.REMOVE_ENERGY_ID, PacketByteBufs.create());
            }
            if (addEnergyCapKey!!.wasPressed()) {
                ClientPlayNetworking.send(packetManager.ADD_ENERGY_CAP_ID, PacketByteBufs.create());
            }
            if (removeEnergyCapKey!!.wasPressed()) {
                ClientPlayNetworking.send(packetManager.REMOVE_ENERGY_CAP_ID, PacketByteBufs.create());
            }

        }
    }

    fun registerKeyBind(keyBindCategory: String, keyBindKey: String, inputKey: Int): KeyBind? {
        return KeyBindingHelper.registerKeyBinding(
            KeyBind(
                keyBindKey,
                InputUtil.Type.KEYSYM,
                inputKey,
                keyBindCategory
            )
        )
    }

}
