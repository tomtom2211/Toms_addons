package io.github.tomtom2211.tomsaddons.features;

import io.github.tomtom2211.tomsaddons.modconfig.Config;
import io.github.tomtom2211.tomsaddons.utils.LocationUtils;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
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

public class ShurikenMobESP{

    public static void init(WorldRenderContext context) {

        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.world == null || !Config.shurikenMobESP || LocationUtils.inDungeon || client.getCameraEntity() == null) return;

        MatrixStack matrices = context.matrices(); // Gives a current game view state (yaw/distance etc.)
        VertexConsumerProvider consumers = context.consumers(); // Create a consumer to let minecraft know you want to render something
        Vec3d cameraPos = client.getCameraEntity().getEyePos(); // Camera position

        for (Entity entity : client.world.getEntities()) {
            if (entity instanceof ArmorStandEntity armorStand && armorStand.getName().getString().replaceAll("✯", "").length() + 1 == armorStand.getName().getString().length() && !client.world.getBiome(client.player.getBlockPos()).getIdAsString().toLowerCase().contains("minecraft:badlands")) {
                int mobId = armorStand.getId();
                Entity mob = client.world.getEntityById(mobId);
                if (mob != null && consumers != null && matrices != null) {
                    matrices.push();

                    matrices.translate(
                            -cameraPos.x,
                            -cameraPos.y-1,
                            -cameraPos.z
                    );


                    Box box = mob.getBoundingBox()
                            .expand(
                                    Config.shurikenMobESPScale * 0.5,
                                    1,
                                    Config.shurikenMobESPScale * 0.5
                            );

                    VertexConsumer buffer = consumers.getBuffer(RenderLayer.getLines());

                    VertexRendering.drawBox(
                            matrices.peek(),
                            buffer,
                            box,
                            Config.shurikenMobESPColor.getRed() / 255f,
                            Config.shurikenMobESPColor.getGreen() / 255f,
                            Config.shurikenMobESPColor.getBlue() / 255f,
                            Config.shurikenMobESPColor.getAlpha() / 255f
                    );

                    matrices.pop();
                }
            }
        }
    }
}
