package net.fabricmc.Beginner;

import net.fabricmc.Beginner.renderers.LoicBulletRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EntityTestingClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("beginnermode", "loic_bullet"), "main");

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(Main.LOIC_BULLET, (context) -> new LoicBulletRenderer(context));
    }
}
