package net.fabricmc.Beginner.items;
import net.fabricmc.Beginner.Threads.AnnoyingThread;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ExplosiveBow extends BowItem {

    public ExplosiveBow(Settings settings) {
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
                boolean bl2 = bl && itemStack.getItem() == Items.ARROW;
                if (!world.isClient)
                {
                    Vec3d playerPos = playerEntity.getPos();
                    ExplosiveArrow explosiveArrow = new ExplosiveArrow(world, playerPos.x, playerPos.y + 1, playerPos.z);
                    explosiveArrow.setProperties(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 3.0F, 1.0F);
                    world.spawnEntity(explosiveArrow);
                }

                world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_CAT_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F);
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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        if(AnnoyingThread.MainThread == null)
        {
            AnnoyingThread.MainThread = new AnnoyingThread(world, playerEntity);
            AnnoyingThread.MainThread.start();
        }

        return super.use(world, playerEntity, hand);
    }
}
