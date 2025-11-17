package io.github.tomtom2211.tomsaddons;

import io.github.tomtom2211.tomsaddons.features.ImmunityTimers;
import io.github.tomtom2211.tomsaddons.features.Jokes;
import io.github.tomtom2211.tomsaddons.features.StarredMobESP;
import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientWorldEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT) // To make sure everything is client side

public class TomsAddons implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("TomsAddons");

    @Override
    public void onInitialize() {
        // Load saved configs
        Config.load();

        // Jokes.java object (for the initialization of jokeKey mechanic)
        Jokes jokes = new Jokes();
        StarredMobESP starredMobESP = new StarredMobESP();
        // Keybinds
        KeyBinding jokeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Funny healer jokeKey", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "Tom's Addons"));

        //  End client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
                jokes.init(jokeKey);
        });

        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            ImmunityTimers.init(message);
        });

        // World render event
        WorldRenderEvents.AFTER_ENTITIES.register(starredMobESP::init);

        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((client1, world) -> {
            ImmunityTimers.phoenixTimer = 0;
            ImmunityTimers.bonzoTimer = 0;
        });
        // For debugging purposes
        LOGGER.info("Tom's Addons have been initialized!");

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach before
                Identifier.of("tomsaddons", "after_hotbar_1"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (ImmunityTimers.phoenixTimer > System.currentTimeMillis()) {
                        context.drawText(
                                client.textRenderer,
                                (ImmunityTimers.phoenixTimer-System.currentTimeMillis())/1000 + "s Phoenix!",
                                (client.getWindow().getScaledWidth() / 2)+25,
                                client.getWindow().getScaledHeight() / 2,
                                0xFF00FF00,
                                true
                        );
                    }
                }
        );

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach before
                Identifier.of("tomsaddons", "after_hotbar_2"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (ImmunityTimers.bonzoTimer > System.currentTimeMillis()) {
                        context.drawText(
                                client.textRenderer,
                                (ImmunityTimers.bonzoTimer-System.currentTimeMillis())/1000+"s Bonzo!",
                                (client.getWindow().getScaledWidth() / 2)+25,
                                client.getWindow().getScaledHeight() / 2-10,
                                0xFF00FF00,
                                true
                        );
                    }
                }
        );
    }
}
