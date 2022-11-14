package net.fabricmc.Beginner.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class LoicBulletEntity extends PersistentProjectileEntity
{
    private int life;

    public LoicBulletEntity(EntityType<? extends LoicBulletEntity> entityType, World world) {
        super(entityType, world);
    }

    public LoicBulletEntity(double x, double y, double z, World world) {
        super(EntityType.ARROW, x, y, z, world);
        setNoGravity(true);
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
    }

    public void tick()
    {
        super.tick();

        if(inGround)
        {
            this.explode();
            this.kill();
        }
    }

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 4.0F, Explosion.DestructionType.BREAK);
    }
}