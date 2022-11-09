package net.fabricmc.Beginner.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LoicFishBulletEntity extends PathAwareEntity {

    public LoicFishBulletEntity(EntityType<? extends LoicFishBulletEntity> entityType, World world) {
        super(entityType, world);
    }

    public LoicFishBulletEntity(World world) {
        super((EntityType<? extends LoicFishBulletEntity>)EntityType.get("beginnermod:loic_fishbullet").get(), world);
    }

    public LoicFishBulletEntity(double x, double y, double z, World world){
        super((EntityType<? extends LoicFishBulletEntity>)EntityType.get("beginnermod:loic_fishbullet").get(), world);
        this.setPosition(x, y, z);
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
}
