import com.mojang.blaze3d.platform.InputUtil
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBind
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents


object keyBindHandler {

    val KEY_CATEGORY_FORTS = "key.category.forts.forts"
    val KEY_ADD_ENERGY = "key.forts.add.energy"

    val glfw = GLFW.GLFW_KEY_0

    val addEnergyKey: KeyBind? = registerKeyBind(KEY_CATEGORY_FORTS, KEY_ADD_ENERGY, 72)

    fun registerKeyInputs() {
        ClientTickEvents.END.register { client ->
            if (addEnergyKey!!.wasPressed()) {
                client.player?.sendMessage(Text.literal("added energy"), false)
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
