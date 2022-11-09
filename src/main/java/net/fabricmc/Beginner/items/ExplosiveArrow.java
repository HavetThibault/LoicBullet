package net.fabricmc.Beginner.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;


public class ExplosiveArrow extends PersistentProjectileEntity
{
    public ExplosiveArrow(World world, double x, double y, double z) {
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
