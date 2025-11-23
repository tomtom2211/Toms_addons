package io.github.tomtom2211.tomsaddons;

import io.github.tomtom2211.tomsaddons.features.*;
import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientWorldEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
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

        // Load HUD
        ImmunityTimers.immunityTimersHUD();
        MiningTimers.miningTimersHUD();

        // Jokes.java object (for the initialization of jokeKey mechanic)
        Jokes jokes = new Jokes();
        StarredMobESP starredMobESP = new StarredMobESP();

        // Keybinds
        KeyBinding jokeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Funny healer joke", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "Tom's Addons"));

        //  End client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> jokes.init(jokeKey));

        ClientSendMessageEvents.COMMAND.register(CommandAliases::init);

        // Client Receive Message event
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            ImmunityTimers.init(message);
            MiningTimers.init(message);
            InstantRequeue.init(message);
        });
        ClientReceiveMessageEvents.ALLOW_GAME.register((message, overlay) -> CommandAliases.supressUnknownCommand(message));

        // World render event
        WorldRenderEvents.AFTER_ENTITIES.register(starredMobESP::init);

        // World change event
        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((client1, world) -> {
            ImmunityTimers.unload();
            MiningTimers.unload();
        });

        // For debugging purposes
        LOGGER.info("Tom's Addons have been initialized!");
    }
}
