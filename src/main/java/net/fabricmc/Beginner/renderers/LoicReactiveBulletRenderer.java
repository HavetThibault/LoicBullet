package net.fabricmc.Beginner.renderers;

import net.fabricmc.Beginner.entities.LoicReactiveBulletEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LoicReactiveBulletRenderer extends ProjectileEntityRenderer<LoicReactiveBulletEntity> {

    public static final Identifier TEXTURE = new Identifier("beginnermod","textures/entity/loic_reactive_bullet.png");

    public LoicReactiveBulletRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(LoicReactiveBulletEntity persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(LoicReactiveBulletEntity arrowEntity) {
        return TEXTURE;
    }
}
