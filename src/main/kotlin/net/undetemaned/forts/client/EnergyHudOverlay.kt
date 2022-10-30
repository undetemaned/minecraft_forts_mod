package net.undetemaned.forts.client

import com.mojang.blaze3d.systems.RenderSystem
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.undetemaned.forts.fortsMod
import net.undetemaned.forts.util.IEntityDataSaver


object EnergyHudOverlay : HudRenderCallback {
    private val FILLED_ENERGY: Identifier = Identifier(fortsMod.MOD_ID, "textures/energy/full.png")
    private val EMPTY_ENERGY: Identifier = Identifier(fortsMod.MOD_ID, "textures/energy/empty.png")

    override fun onHudRender(matrixStack: MatrixStack?, tickDelta: Float) {
        val client = MinecraftClient.getInstance() ?: return
        val window = client.window

        val x = window.scaledWidth / 2
        val y = window.scaledHeight

        val currentEnergy = (MinecraftClient.getInstance().player as IEntityDataSaver).persistentData!!.getInt("energy")
        val currentEnergyCap = (MinecraftClient.getInstance().player as IEntityDataSaver).persistentData!!.getInt("energy cap")
        val currentEnergyRate = (MinecraftClient.getInstance().player as IEntityDataSaver).persistentData!!.getInt("energy rate")

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        //RenderSystem.setShaderTexture(0, EMPTY_ENERGY);
        //DrawableHelper.drawTexture(matrixStack,x + 250,y - 12 * 13, 0F, 0F,10,80, 10,80);

        //RenderSystem.setShaderTexture(0, FILLED_ENERGY);
        //DrawableHelper.drawTexture(matrixStack,x + 250,y - 12 * 13, 0F, 0F, 10,80,10,80);
        //https://www.digminecraft.com/lists/color_list_pc.php
        DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, currentEnergy.toString(), x + 100, y - 10, 16777215)
        DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, currentEnergyCap.toString(), x + 130, y - 10, 11184810)
        DrawableHelper.drawCenteredText(matrixStack, MinecraftClient.getInstance().textRenderer, currentEnergyRate.toString(), x + 160, y - 10, 11184810)


    }

}
