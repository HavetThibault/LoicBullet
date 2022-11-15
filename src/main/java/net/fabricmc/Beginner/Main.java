package net.fabricmc.Beginner;

import net.fabricmc.Beginner.entities.LoicFishBulletEntity;
import net.fabricmc.Beginner.entities.LoicReactiveBulletEntity;
import net.fabricmc.Beginner.items.ExplosiveAK47;
import net.fabricmc.Beginner.items.ExplosiveBow;
import net.fabricmc.Beginner.items.ShotgunHomemade;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {

	static public final Item SHOTGUN_HOMEMADE = new ShotgunHomemade(new FabricItemSettings().group(ItemGroup.MISC));
	static public final Item EXPLOSIVE_BOW = new ExplosiveBow(new FabricItemSettings().group(ItemGroup.MISC));
	static public final Item EXPLOSIVE_AK47 = new ExplosiveAK47(new FabricItemSettings().group(ItemGroup.MISC));

	public static final EntityType<LoicFishBulletEntity> LOIC_FISHBULLET = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("beginnermod", "loic_fishbullet"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, (EntityType.EntityFactory<LoicFishBulletEntity>) LoicFishBulletEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build()
	);
	public static final EntityType<LoicReactiveBulletEntity> LOIC_REACTIVEBULLET = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("beginnermod", "loic_reactivebullet"),
			FabricEntityTypeBuilder.create(SpawnGroup.MISC, (EntityType.EntityFactory<LoicReactiveBulletEntity>) LoicReactiveBulletEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build()
	);

	public static final Identifier LOIC_FISHBULLET_SOUND_ID = new Identifier("beginnermod:loic_fishbullet_sound");
	public static SoundEvent LOIC_FISHBULLET_SOUND_EVENT = new SoundEvent(LOIC_FISHBULLET_SOUND_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		FabricDefaultAttributeRegistry.register(LOIC_FISHBULLET, LoicFishBulletEntity.createMobAttributes());

		Registry.register(Registry.ITEM, new Identifier("beginnermod", "shotgun_homemade"), SHOTGUN_HOMEMADE);
		Registry.register(Registry.ITEM, new Identifier("beginnermod", "explosive_bow"), EXPLOSIVE_BOW);
		Registry.register(Registry.ITEM, new Identifier("beginnermod", "explosive_ak47"), EXPLOSIVE_AK47);

		// Register sound
		Registry.register(Registry.SOUND_EVENT, LOIC_FISHBULLET_SOUND_ID, LOIC_FISHBULLET_SOUND_EVENT);
	}
}
