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
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT) // To make sure everything is client side

public class TomsAddons implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("TomsAddons");
    public static final MinecraftClient client = MinecraftClient.getInstance();
    public static String side;
    @Override
    public void onInitialize() {

        // Load saved configs
        Config.load();

        // Load HUD
        ImmunityTimers.immunityTimersHUD();
        MiningTimers.miningTimersHUD();

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_10"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (side != null) {
                        context.drawText(
                                client.textRenderer,
                                side,
                                (client.getWindow().getScaledWidth() / 2)+10,
                                client.getWindow().getScaledHeight() / 2-10,
                                0xffff0000,
                                true
                        );
                    }
                }
        );

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
        WorldRenderEvents.AFTER_ENTITIES.register(context -> {
                starredMobESP.init(context);
                if(client.world != null) {
                    for (Entity entity : client.world.getEntities()) {
                        if (entity instanceof MagmaCubeEntity magmaCube && magmaCube.getPos().y<35 && magmaCube.getSize() > 20){
                            if(magmaCube.getPos().x < -128){
                                side = "RIGHT";
                            }
                            else if(magmaCube.getPos().x > -72){
                                side = "LEFT";
                            }
                            else if(magmaCube.getPos().z > -84){
                                side = "FRONT";
                            }
                            else if(magmaCube.getPos().z < -132){
                                side = "BACK";
                            }
                        }

                    }
                }
        });

        // World change event
        ClientWorldEvents.AFTER_CLIENT_WORLD_CHANGE.register((client1, world) -> {
            ImmunityTimers.unload();
            MiningTimers.unload();
        });

        // For debugging purposes
        LOGGER.info("Tom's Addons have been initialized!");
    }
}
