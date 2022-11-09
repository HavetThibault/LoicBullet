package net.fabricmc.Beginner;

import net.fabricmc.Beginner.entities.LoicBulletEntity;
import net.fabricmc.Beginner.entities.LoicFishBulletEntity;
import net.fabricmc.Beginner.items.ExplosiveBow;
import net.fabricmc.Beginner.items.ShotgunHomemade;
import net.fabricmc.Beginner.renderers.LoicBulletRenderer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Main implements ModInitializer {

	static public final Item SHOTGUN_HOMEMADE = new ShotgunHomemade(new FabricItemSettings().group(ItemGroup.MISC));
	static public final Item EXPLOSIVE_BOW = new ExplosiveBow(new FabricItemSettings().group(ItemGroup.MISC));
	public static final EntityType<LoicBulletEntity> LOIC_BULLET = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("beginnermod", "loic_bullet"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,  (EntityType.EntityFactory<LoicBulletEntity>) LoicBulletEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);
	public static final EntityType<LoicFishBulletEntity> LOIC_FISHBULLET = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("beginnermod", "loic_fishbullet"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory<LoicFishBulletEntity>) LoicFishBulletEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		FabricDefaultAttributeRegistry.register(LOIC_FISHBULLET, LoicFishBulletEntity.createMobAttributes());

		Registry.register(Registry.ITEM, new Identifier("beginnermod", "shotgun_homemade"), SHOTGUN_HOMEMADE);
		Registry.register(Registry.ITEM, new Identifier("beginnermod", "explosive_bow"), EXPLOSIVE_BOW);
	}
}
