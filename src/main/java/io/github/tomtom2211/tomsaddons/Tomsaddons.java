package io.github.tomtom2211.tomsaddons;

import io.github.tomtom2211.tomsaddons.features.jokes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Tomsaddons implements ModInitializer {

    @Override
    public void onInitialize() {
        Random rand = new Random();
        jokes Jokes = new jokes();
        KeyBinding joke = KeyBindingHelper.registerKeyBinding(new KeyBinding("Funny healer joke", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "Tom's Addons"));
        MinecraftClient mcClient = MinecraftClient.getInstance();
        AtomicLong buttonPressTime = new AtomicLong(System.currentTimeMillis());
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(joke.wasPressed()&&client != null&&System.currentTimeMillis()- buttonPressTime.get() >=1000){
                int randomNum = rand.nextInt(50);
                Objects.requireNonNull(mcClient.getNetworkHandler()).sendChatMessage(Jokes.jokes[randomNum]);
                buttonPressTime.set(System.currentTimeMillis());
            }
        });
    }
}
