package io.github.tomtom2211.tomsaddons.features;

import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class StarredMobESP {
    public void init(WorldRenderContext context){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null || !Config.config.starredMobESP) return;

        MatrixStack matrices = context.matrixStack(); // Gives a current game view state (yaw/distance etc.)
        VertexConsumerProvider consumers = context.consumers(); // Create a consumer to let minecraft know you want to render something
        Vec3d cameraPos = context.camera().getPos(); // Camera position
        for (Entity entity : client.world.getEntities()) {
            if (entity instanceof ArmorStandEntity armorStand && client.player.canSee(armorStand) && armorStand.getName().getString().contains("✯") && armorStand.isCustomNameVisible())
            {
                int mobId = armorStand.getId() - 1;
                Entity mob = client.world.getEntityById(mobId);
                Box box = Objects.requireNonNull(mob).getBoundingBox()
                        .offset(-cameraPos.x, -cameraPos.y, -cameraPos.z); // Absolute => Relative coordinates
                VertexConsumer buffer = Objects.requireNonNull(consumers).getBuffer(RenderLayer.getLines());// Something, you can actually write into
                VertexRendering.drawBox(
                        Objects.requireNonNull(matrices),
                        buffer,
                        box,
                        1.0f, 0.0f, 0.0f, 1.0f // RGBA = red
                );
            }
        }
    }
}
