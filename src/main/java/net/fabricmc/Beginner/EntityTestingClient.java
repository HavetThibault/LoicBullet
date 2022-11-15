package net.fabricmc.Beginner;

import net.fabricmc.Beginner.models.LoicFishBulletModel;
import net.fabricmc.Beginner.renderers.LoicFishBulletRenderer;
import net.fabricmc.Beginner.renderers.LoicReactiveBulletRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EntityTestingClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_LOIC_FISHBULLET_LAYER = new EntityModelLayer(new Identifier("beginnermod", "loic_fishbullet"), "main");

    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */

        EntityRendererRegistry.register(Main.LOIC_FISHBULLET, (context) -> new LoicFishBulletRenderer(context));
        EntityRendererRegistry.register(Main.LOIC_REACTIVEBULLET, (context) -> new LoicReactiveBulletRenderer(context));

        EntityModelLayerRegistry.registerModelLayer(MODEL_LOIC_FISHBULLET_LAYER, LoicFishBulletModel::getTexturedModelData);
    }
}
