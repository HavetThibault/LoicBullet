package net.fabricmc.Beginner.Threads;

import net.minecraft.block.AirBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AnnoyingThread extends Thread{

    public static AnnoyingThread MainThread = null;

    private World world;
    private PlayerEntity playerEntity;

    public AnnoyingThread(World world, PlayerEntity playerEntity)
    {
        this.world = world;
        this.playerEntity = playerEntity;
    }

    @Override
    public void run()
    {
        int iterations = 30, i = 0;
        while(!isInterrupted() && i < iterations)
        {
            try
            {
                sleep(5000);

                Vec3d playerPos = playerEntity.getPos();

                if(world.getBlockState(new BlockPos(playerPos.x, playerPos.y - 1, playerPos.z)).getBlock() instanceof AirBlock)
                    playerEntity.sendMessage(new LiteralText("Stay on the floor please..."), false);
                else
                    playerEntity.sendMessage(new LiteralText("You are a good boy..."), false);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
