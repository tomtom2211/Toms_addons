package io.github.tomtom2211.tomsaddons;

import io.github.tomtom2211.tomsaddons.features.Jokes;
import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.*;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

@Environment(EnvType.CLIENT) // To make sure everything is client side

public class TomsAddons implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("TomsAddons");

    @Override
    public void onInitialize() {
        // Load saved configs
        Config.load();

        // Jokes.java object (for the initialization of jokeKey mechanic)
        Jokes jokes = new Jokes();
        // Keybinds
        KeyBinding jokeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Funny healer jokeKey", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "Tom's Addons"));

        //  End client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
                jokes.jokeLogic(jokeKey);
        });

        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.world == null) return;

            MatrixStack matrices = context.matrixStack(); // Gives a current game view state (yaw/distance etc.)
            VertexConsumerProvider consumers = context.consumers(); // Create a consumer to let minecraft know you want to render something
            Vec3d cameraPos = context.camera().getPos(); // Camera position
            for (Entity entity : client.world.getEntities()) {
                if (entity instanceof ArmorStandEntity armorStand && client.player.canSee(armorStand) && armorStand.getName().getString().contains("✯") && armorStand.isCustomNameVisible())
                {
                    Box box = armorStand.getBoundingBox()
                            .expand(0.3,1,0.3)
                            .offset(-cameraPos.x, -(cameraPos.y+1), -cameraPos.z); // Absolute => Relative coordinates
                    VertexConsumer buffer = Objects.requireNonNull(consumers).getBuffer(RenderLayer.getLines());// Something, you can actually write into
                    VertexRendering.drawBox(
                            Objects.requireNonNull(matrices),
                            buffer,
                            box,
                            1.0f, 0.0f, 0.0f, 1.0f // RGBA = red
                    );
                }
            }
        });


        // For debugging purposes
        LOGGER.info("Tom's Addons have been initialized!");
    }
}
