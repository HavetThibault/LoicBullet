package net.fabricmc.Beginner.renderers;

import net.fabricmc.Beginner.EntityTestingClient;
import net.fabricmc.Beginner.entities.LoicFishBulletEntity;
import net.fabricmc.Beginner.models.LoicFishBulletModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class LoicFishBulletRenderer extends MobEntityRenderer<LoicFishBulletEntity, LoicFishBulletModel> {

    public LoicFishBulletRenderer(EntityRendererFactory.Context context) {
        super(context, new LoicFishBulletModel(context.getPart(EntityTestingClient.MODEL_LOIC_FISHBULLET_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(LoicFishBulletEntity entity) {
        return new Identifier("beginnermod", "/textures/entity/loic_fishbullet_model.png");
    }
}