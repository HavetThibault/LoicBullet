package net.fabricmc.Beginner.renderers;

import net.fabricmc.Beginner.entities.LoicBulletEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.*;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class LoicBulletRenderer extends ProjectileEntityRenderer<LoicBulletEntity> {

    public LoicBulletRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(LoicBulletEntity entity) {
        return new Identifier("beginnermod", "D:\\MyJavaMods\\LoicBulletAndCo_v17\\src\\main\\resources\\assets\\beginnermod\\textures\\item\\LoicBulletModel.png");
    }
}
