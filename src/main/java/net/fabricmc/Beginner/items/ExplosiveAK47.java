package net.fabricmc.Beginner.items;

import net.fabricmc.Beginner.entities.LoicFishBulletEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ExplosiveAK47 extends BowItem {

    public ExplosiveAK47(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)user;
            boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemStack = playerEntity.getArrowType(stack);
            if (!itemStack.isEmpty() || bl) {
                if (itemStack.isEmpty()) {
                    itemStack = new ItemStack(Items.ARROW);
                }

                float yaw = playerEntity.getYaw();
                float pitch = playerEntity.getPitch();
                float roll = playerEntity.getRoll();
                if (!world.isClient)
                {
                    Vec3d playerPos = playerEntity.getPos();
                    LoicFishBulletEntity fishBullet = new LoicFishBulletEntity(world);
                    fishBullet.setNoGravity(true);
                    fishBullet.setProperties(playerEntity, pitch, yaw, 0.0F, 0.5f * 7.0F, 0.0F); // modifierZ arg is the power
                    fishBullet.setPosition(playerPos.x, playerPos.y + 1, playerPos.z);
                    world.spawnEntity(fishBullet);

                    /*float xg = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
                    float yg = -MathHelper.sin((playerEntity.getPitch() + roll) * 0.017453292F);
                    float zh = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
                    Vec3d playerVelocity = playerEntity.getVelocity();
                    Vec3d newVelocity = new Vec3d(playerVelocity.getY() + xg, playerVelocity.getY() + yg, playerVelocity.getZ() + zh);
                    playerEntity.move(MovementType.PLAYER, newVelocity);*/
                }

                boolean bl2 = bl && itemStack.getItem() == Items.ARROW;
                world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.BLOCK_METAL_STEP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                if (!bl2 && !playerEntity.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                    if (itemStack.isEmpty()) {
                        playerEntity.getInventory().removeOne(itemStack);
                    }
                }

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
        }
    }
}
