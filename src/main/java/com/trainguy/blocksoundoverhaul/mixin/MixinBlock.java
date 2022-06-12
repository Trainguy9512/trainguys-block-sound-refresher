package com.trainguy.blocksoundoverhaul.mixin;

import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class MixinBlock {
	@Shadow protected abstract Block asBlock();

	private static final SoundType STONE_ORE = new SoundType(1.0F, 0.9F, SoundEvents.NETHER_ORE_BREAK, SoundEvents.NETHER_ORE_STEP, SoundEvents.NETHER_ORE_PLACE, SoundEvents.NETHER_ORE_HIT, SoundEvents.NETHER_ORE_FALL);
	private static final SoundType OBSIDIAN = new SoundType(1.0F, 0.7F, SoundEvents.DEEPSLATE_BREAK, SoundEvents.DEEPSLATE_STEP, SoundEvents.DEEPSLATE_PLACE, SoundEvents.DEEPSLATE_HIT, SoundEvents.DEEPSLATE_FALL);
	private static final SoundType GRASS_BLOCK = new SoundType(1.0F, 1.0F, SoundEvents.ROOTED_DIRT_BREAK, SoundEvents.GRASS_STEP, SoundEvents.ROOTED_DIRT_BREAK, SoundEvents.ROOTED_DIRT_BREAK, SoundEvents.GRASS_FALL);
	private static final SoundType TERRACOTTA = new SoundType(1.0F, 0.6F, SoundEvents.CALCITE_BREAK, SoundEvents.CALCITE_STEP, SoundEvents.CALCITE_PLACE, SoundEvents.CALCITE_HIT, SoundEvents.CALCITE_FALL);
	private static final SoundType STONE_BRICK = new SoundType(1.0F, 0.6F, SoundEvents.DEEPSLATE_TILES_BREAK, SoundEvents.DEEPSLATE_TILES_STEP, SoundEvents.DEEPSLATE_TILES_PLACE, SoundEvents.DEEPSLATE_TILES_HIT, SoundEvents.DEEPSLATE_TILES_FALL);
	private static final SoundType CLAY_BRICK = new SoundType(1.0F, 1.3F, SoundEvents.NETHER_BRICKS_BREAK, SoundEvents.NETHER_BRICKS_STEP, SoundEvents.NETHER_BRICKS_PLACE, SoundEvents.NETHER_BRICKS_HIT, SoundEvents.NETHER_BRICKS_FALL);
	private static final SoundType METAL = new SoundType(1.0F, 0.6F, SoundEvents.COPPER_BREAK, SoundEvents.COPPER_STEP, SoundEvents.COPPER_PLACE, SoundEvents.COPPER_HIT, SoundEvents.COPPER_FALL);
	private static final SoundType LILY_PAD = new SoundType(1.0F, 1.0F, SoundEvents.BIG_DRIPLEAF_BREAK, SoundEvents.BIG_DRIPLEAF_STEP, SoundEvents.LILY_PAD_PLACE, SoundEvents.BIG_DRIPLEAF_HIT, SoundEvents.BIG_DRIPLEAF_FALL);
	private static final SoundType SMALL_OBJECT = new SoundType(1.0F, 0.8F, SoundEvents.CANDLE_BREAK, SoundEvents.CANDLE_STEP, SoundEvents.CANDLE_PLACE, SoundEvents.CANDLE_HIT, SoundEvents.CANDLE_FALL);

	@Inject(method = "getSoundType", at = @At("TAIL"), cancellable = true)
	private void getSoundGroupMixin(CallbackInfoReturnable<SoundType> cir){
		String blockId = Registry.BLOCK.getKey(this.asBlock()).getPath();
		cir.setReturnValue(
				switch (blockId){
					case
							"coal_ore",
							"copper_ore",
							"iron_ore",
							"gold_ore",
							"emerald_ore",
							"diamond_ore",
							"redstone_ore"
							-> STONE_ORE;
					case
							"raw_copper_block",
							"raw_iron_block",
							"raw_gold_block",
							"coal_block"
							-> SoundType.NETHER_GOLD_ORE;
					case
							"oak_leaves",
							"birch_leaves",
							"spruce_leaves",
							"jungle_leaves",
							"acacia_leaves",
							"dark_oak_leaves",
							"mangrove_leaves",
							"oak_sapling",
							"birch_sapling",
							"spruce_sapling",
							"jungle_sapling",
							"acacia_sapling",
							"dark_oak_sapling",
							"mangrove_propagule"
							-> SoundType.AZALEA;
					case
							"lily_pad"
							-> LILY_PAD;
					case
							"end_stone",
							"andesite",
							"andesite_wall",
							"andesite_slab",
							"andesite_stairs",
							"diorite",
							"diorite_wall",
							"diorite_stairs",
							"diorite_slab",
							"granite",
							"granite_wall",
							"granite_slab",
							"granite_stairs"
							-> SoundType.BASALT;
					case
							"obsidian",
							"crying_obsidian",
							"respawn_anchor"
							-> OBSIDIAN;
					case
							"grass_block",
							"podzol",
							"mycelium",
							"dirt_path"
							-> GRASS_BLOCK;
					case
							"grass",
							"dead_bush",
							"tall_grass",
							"fern",
							"tall_fern",
							"dandelion",
							"poppy",
							"blue_orchid",
							"allium",
							"azure_bluet",
							"red_tulip",
							"orange_tulip",
							"white_tulip",
							"pink_tulip",
							"oxeye_daisy",
							"cornflower",
							"lily_of_the_valley",
							"wither_rose",
							"brown_mushroom",
							"red_mushroom"
							-> SoundType.HANGING_ROOTS;
					case
							"cobweb",
							"glow_lichen"
							-> SoundType.VINE;
					case
							"white_terracotta",
							"orange_terracotta",
							"magenta_terracotta",
							"light_blue_terracotta",
							"yellow_terracotta",
							"lime_terracotta",
							"pink_terracotta",
							"gray_terracotta",
							"light_gray_terracotta",
							"cyan_terracotta",
							"purple_terracotta",
							"blue_terracotta",
							"brown_terracotta",
							"green_terracotta",
							"red_terracotta",
							"black_terracotta",
							"terracotta"
							-> TERRACOTTA;
					case
							"stone_brick_slab",
							"infested_stone_bricks",
							"infested_mossy_stone_bricks",
							"infested_cracked_stone_bricks",
							"infested_chiseled_stone_bricks",
							"stone_bricks", "mossy_stone_bricks",
							"cracked_stone_bricks",
							"chiseled_stone_bricks",
							"stone_brick_stairs",
							"mossy_stone_brick_wall",
							"stone_brick_wall",
							"mossy_stone_brick_stairs",
							"mossy_stone_brick_slab"
							-> STONE_BRICK;
					case
							"brick_slab",
							"bricks",
							"brick_stairs",
							"brick_wall"
							-> CLAY_BRICK;
					case
							"gold_block",
							"diamond_block",
							"emerald_block",
							"iron_bars",
							"iron_door",
							"iron_block",
							"observer",
							"dropper",
							"dispenser"
							-> SoundType.NETHERITE_BLOCK;
					case
							"gravel",
							"dirt",
							"coarse_dirt"
							-> SoundType.ROOTED_DIRT;
					case
							"rooted_dirt"
							-> SoundType.WART_BLOCK;
					case
							"flower_pot",
							"torch",
							"soul_torch",
							"redstone_torch",
							"wall_torch",
							"soul_wall_torch",
							"redstone_wall_torch"
							-> SMALL_OBJECT;
					default -> cir.getReturnValue();
				}
		);
		/*
		Block currentBlock = this.asBlock();
		if (currentBlock == Blocks.RAW_COPPER_BLOCK) {
			cir.setReturnValue(SoundType.NETHER_ORE);
		} else if(currentBlock == Blocks.COAL_ORE || currentBlock == Blocks.IRON_ORE || currentBlock == Blocks.COPPER_ORE || currentBlock == Blocks.GOLD_ORE || currentBlock == Blocks.LAPIS_ORE || currentBlock == Blocks.REDSTONE_ORE || currentBlock == Blocks.EMERALD_ORE || currentBlock == Blocks.DIAMOND_ORE){
			cir.setReturnValue(STONE_ORE);
		} else if(currentBlock.getDefaultState().isIn(BlockTags.STONE_BRICKS)){
			cir.setReturnValue(SoundType.NETHER_BRICKS);
		} else if(currentBlock.getDefaultState().isIn(BlockTags.LEAVES) && currentBlock != Blocks.AZALEA_LEAVES && currentBlock != Blocks.FLOWERING_AZALEA_LEAVES){
			cir.setReturnValue(SoundType.AZALEA);
		}


		 */
	}
}
