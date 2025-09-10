package io.github.tomtom2211.tomsaddons;

import io.github.tomtom2211.tomsaddons.features.Jokes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class TomsAddons implements ModInitializer {

    @Override
    public void onInitialize() {
        // Jokes.java object (for the initialization of joke mechanic)
        Jokes jokes = new Jokes();

        // Keybinds
        KeyBinding joke = KeyBindingHelper.registerKeyBinding(new KeyBinding("Funny healer joke", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "Tom's Addons"));

        //  End client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
                jokes.jokeMechanic(joke);
        });
    }
}
