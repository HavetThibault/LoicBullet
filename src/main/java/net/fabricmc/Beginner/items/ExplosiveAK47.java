package net.fabricmc.Beginner.items;

import net.fabricmc.Beginner.Main;
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
import net.minecraft.util.math.BlockPos;
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
                    world.playSound(
                            null, // Player - if non-null, will play sound for every nearby player *except* the specified player
                            new BlockPos(playerPos), // The position of where the sound will come from
                            Main.LOIC_FISHBULLET_SOUND_EVENT, // The sound that will play
                            SoundCategory.BLOCKS, // This determines which of the volume sliders affect this sound
                            1f, //Volume multiplier, 1 is normal, 0.5 is half volume, etc
                            1f // Pitch multiplier, 1 is normal, 0.5 is half pitch, etc
                    );
                    LoicFishBulletEntity fishBullet = new LoicFishBulletEntity(world);
                    fishBullet.setNoGravity(true);
                    fishBullet.setProperties(playerEntity, pitch, yaw, 0.0F, 0.5f * 7.0F, 0.0F); // modifierZ arg is the power
                    fishBullet.setPosition(playerPos.x, playerPos.y + 1, playerPos.z);
                    world.spawnEntity(fishBullet);
                }

                boolean bl2 = bl && itemStack.getItem() == Items.ARROW;

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
