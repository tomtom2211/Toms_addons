package io.github.tomtom2211.tomsaddons;

import io.github.tomtom2211.tomsaddons.features.Jokes;
import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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

        // For debugging purposes
        LOGGER.info("Tom's Addons has been initialized!");
    }
}
