package net.fabricmc.Beginner.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class LoicReactiveBulletEntity extends PersistentProjectileEntity {

    public LoicReactiveBulletEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        setNoGravity(true);
    }

    @Override
    public void onBlockHit(BlockHitResult blockHitResult){
        explode();
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 1.0F, Explosion.DestructionType.DESTROY);
        this.remove(Entity.RemovalReason.DISCARDED);
    }
}
