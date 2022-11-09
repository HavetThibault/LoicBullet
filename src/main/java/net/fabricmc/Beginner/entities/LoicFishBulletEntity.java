package net.fabricmc.Beginner.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.Iterator;

public class LoicFishBulletEntity extends PathAwareEntity {

    public LoicFishBulletEntity(EntityType<? extends LoicFishBulletEntity> entityType, World world) {
        super(entityType, world);
        setInvulnerable(true);
    }

    public LoicFishBulletEntity(World world) {
        super((EntityType<? extends LoicFishBulletEntity>)EntityType.get("beginnermod:loic_fishbullet").get(), world);
        setInvulnerable(true);
    }

    public void setProperties(Entity user, float pitch, float yaw, float roll, float modifierZ, float modifierXYZ) {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float g = -MathHelper.sin((pitch + roll) * 0.017453292F);
        float h = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.setVelocity((double)f, (double)g, (double)h, modifierZ, modifierXYZ);
        Vec3d vec3d = user.getVelocity();
        this.setVelocity(this.getVelocity().add(vec3d.x, user.isOnGround() ? 0.0D : vec3d.y, vec3d.z));
        setPitch(pitch);
        setYaw(yaw);
    }

    public void setVelocity(double x, double y, double z, float speed, float divergence) {
        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.random.nextGaussian() * 0.007499999832361937D * (double)divergence, this.random.nextGaussian() * 0.007499999832361937D * (double)divergence, this.random.nextGaussian() * 0.007499999832361937D * (double)divergence).multiply((double)speed);
        this.setVelocity(vec3d);
        double d = vec3d.horizontalLength();
        this.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D));
        this.setPitch((float)(MathHelper.atan2(vec3d.y, d) * 57.2957763671875D));
        this.prevYaw = this.getYaw();
        this.prevPitch = this.getPitch();
    }

    @Override
    public void tick()
    {
        super.tick();
        BlockPos blockPos = this.getBlockPos();
        BlockState upblockState = this.world.getBlockState(blockPos.add(0,0,1));
        BlockState downblockState = this.world.getBlockState(blockPos.add(0,0,-1));
        BlockState rightblockState = this.world.getBlockState(blockPos.add(1,0,0));
        BlockState leftblockState = this.world.getBlockState(blockPos.add(0,1,0));
        BlockState forwardblockState = this.world.getBlockState(blockPos.add(-1,0,0));
        BlockState backwardblockState = this.world.getBlockState(blockPos.add(0,-1,0));
        if (!(upblockState.isAir() && downblockState.isAir() && rightblockState.isAir() && leftblockState.isAir() && forwardblockState.isAir() && backwardblockState.isAir())) {
            explode();
        }
    }

    private void explode() {
        this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), 1.5F, Explosion.DestructionType.BREAK);
        this.remove(Entity.RemovalReason.DISCARDED);
    }
}
