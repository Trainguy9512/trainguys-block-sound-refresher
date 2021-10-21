package com.trainguy.blocksoundoverhaul.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class MixinBlock {
	@Shadow protected abstract Block asBlock();

	private static final BlockSoundGroup STONE_ORE = new BlockSoundGroup(1.0F, 0.8F, SoundEvents.BLOCK_NETHER_ORE_BREAK, SoundEvents.BLOCK_NETHER_ORE_STEP, SoundEvents.BLOCK_NETHER_ORE_PLACE, SoundEvents.BLOCK_NETHER_ORE_HIT, SoundEvents.BLOCK_NETHER_ORE_FALL);
	private static final BlockSoundGroup OBSIDIAN = new BlockSoundGroup(1.0F, 0.7F, SoundEvents.BLOCK_DEEPSLATE_BREAK, SoundEvents.BLOCK_DEEPSLATE_STEP, SoundEvents.BLOCK_DEEPSLATE_PLACE, SoundEvents.BLOCK_DEEPSLATE_HIT, SoundEvents.BLOCK_DEEPSLATE_FALL);
	private static final BlockSoundGroup GRASS_BLOCK = new BlockSoundGroup(1.0F, 1.0F, SoundEvents.BLOCK_ROOTED_DIRT_BREAK, SoundEvents.BLOCK_GRASS_STEP, SoundEvents.BLOCK_ROOTED_DIRT_BREAK, SoundEvents.BLOCK_ROOTED_DIRT_BREAK, SoundEvents.BLOCK_GRASS_FALL);
	private static final BlockSoundGroup TERRACOTTA = new BlockSoundGroup(1.0F, 0.6F, SoundEvents.BLOCK_CALCITE_BREAK, SoundEvents.BLOCK_CALCITE_STEP, SoundEvents.BLOCK_CALCITE_PLACE, SoundEvents.BLOCK_CALCITE_HIT, SoundEvents.BLOCK_CALCITE_FALL);
	private static final BlockSoundGroup STONE_BRICK = new BlockSoundGroup(1.0F, 0.6F, SoundEvents.BLOCK_DEEPSLATE_TILES_BREAK, SoundEvents.BLOCK_DEEPSLATE_TILES_STEP, SoundEvents.BLOCK_DEEPSLATE_TILES_PLACE, SoundEvents.BLOCK_DEEPSLATE_TILES_HIT, SoundEvents.BLOCK_DEEPSLATE_TILES_FALL);
	private static final BlockSoundGroup CLAY_BRICK = new BlockSoundGroup(1.0F, 1.2F, SoundEvents.BLOCK_NETHER_BRICKS_BREAK, SoundEvents.BLOCK_NETHER_BRICKS_STEP, SoundEvents.BLOCK_NETHER_BRICKS_PLACE, SoundEvents.BLOCK_NETHER_BRICKS_HIT, SoundEvents.BLOCK_NETHER_BRICKS_FALL);
	private static final BlockSoundGroup METAL = new BlockSoundGroup(1.0F, 0.8F, SoundEvents.BLOCK_COPPER_BREAK, SoundEvents.BLOCK_COPPER_STEP, SoundEvents.BLOCK_COPPER_PLACE, SoundEvents.BLOCK_COPPER_HIT, SoundEvents.BLOCK_COPPER_FALL);
	private static final BlockSoundGroup LILY_PAD = new BlockSoundGroup(1.0F, 1.0F, SoundEvents.BLOCK_BIG_DRIPLEAF_BREAK, SoundEvents.BLOCK_BIG_DRIPLEAF_STEP, SoundEvents.BLOCK_LILY_PAD_PLACE, SoundEvents.BLOCK_BIG_DRIPLEAF_HIT, SoundEvents.BLOCK_BIG_DRIPLEAF_FALL);

	@Inject(method = "getSoundGroup", at = @At("TAIL"), cancellable = true)
	private void getSoundGroupMixin(CallbackInfoReturnable<BlockSoundGroup> cir){
		String blockId = Registry.BLOCK.getId(this.asBlock()).getPath();
		cir.setReturnValue(
				switch (blockId){
					case "coal_ore", "copper_ore", "iron_ore", "gold_ore", "emerald_ore", "diamond_ore", "redstone_ore" -> STONE_ORE;
					case "raw_copper_block", "redstone_block", "raw_iron_block", "raw_gold_block", "coal_block" -> BlockSoundGroup.ANCIENT_DEBRIS;
					case "oak_leaves", "birch_leaves", "spruce_leaves", "jungle_leaves", "acacia_leaves", "dark_oak_leaves", "oak_sapling", "birch_sapling", "spruce_sapling", "jungle_sapling", "acacia_sapling", "dark_oak_sapling" -> BlockSoundGroup.AZALEA;
					case "lily_pad" -> LILY_PAD;
					case "obsidian", "crying_obsidian", "respawn_anchor" -> OBSIDIAN;
					case "grass_block", "podzol", "mycelium", "dirt_path" -> GRASS_BLOCK;
					case "grass", "dead_bush", "tall_grass", "fern", "tall_fern", "dandelion", "poppy", "blue_orchid", "allium", "azure_bluet", "red_tulip", "orange_tulip", "white_tulip", "pink_tulip", "oxeye_daisy", "cornflower", "lily_of_the_valley", "wither_rose", "brown_mushroom", "red_mushroom" -> BlockSoundGroup.HANGING_ROOTS;
					case "cobweb", "glow_lichen" -> BlockSoundGroup.VINE;
					case "white_terracotta", "orange_terracotta", "magenta_terracotta", "light_blue_terracotta", "yellow_terracotta", "lime_terracotta", "pink_terracotta", "gray_terracotta", "light_gray_terracotta", "cyan_terracotta", "purple_terracotta", "blue_terracotta", "brown_terracotta", "green_terracotta", "red_terracotta", "black_terracotta", "terracotta" -> TERRACOTTA;
					case "stone_brick_slab", "infested_stone_bricks", "infested_mossy_stone_bricks", "infested_cracked_stone_bricks", "infested_chiseled_stone_bricks", "stone_bricks", "mossy_stone_bricks", "cracked_stone_bricks", "chiseled_stone_bricks", "stone_brick_stairs", "mossy_stone_brick_wall", "stone_brick_wall", "mossy_stone_brick_stairs", "mossy_stone_brick_slab" -> STONE_BRICK;
					case "brick_slab", "bricks", "brick_stairs", "brick_wall" -> CLAY_BRICK;
					case "iron_block", "gold_block", "diamond_block", "emerald_block", "iron_bars" -> METAL;
					case "gravel" -> BlockSoundGroup.SOUL_SOIL;
					case "flower_pot" -> BlockSoundGroup.CANDLE;
					case "end_stone" -> BlockSoundGroup.DRIPSTONE_BLOCK;
					default -> cir.getReturnValue();
				}
		);
		/*
		Block currentBlock = this.asBlock();
		if (currentBlock == Blocks.RAW_COPPER_BLOCK) {
			cir.setReturnValue(BlockSoundGroup.NETHER_ORE);
		} else if(currentBlock == Blocks.COAL_ORE || currentBlock == Blocks.IRON_ORE || currentBlock == Blocks.COPPER_ORE || currentBlock == Blocks.GOLD_ORE || currentBlock == Blocks.LAPIS_ORE || currentBlock == Blocks.REDSTONE_ORE || currentBlock == Blocks.EMERALD_ORE || currentBlock == Blocks.DIAMOND_ORE){
			cir.setReturnValue(STONE_ORE);
		} else if(currentBlock.getDefaultState().isIn(BlockTags.STONE_BRICKS)){
			cir.setReturnValue(BlockSoundGroup.NETHER_BRICKS);
		} else if(currentBlock.getDefaultState().isIn(BlockTags.LEAVES) && currentBlock != Blocks.AZALEA_LEAVES && currentBlock != Blocks.FLOWERING_AZALEA_LEAVES){
			cir.setReturnValue(BlockSoundGroup.AZALEA);
		}


		 */
	}
}
