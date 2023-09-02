package com.null8.messingaround.mixin;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void register(String id, Block block, CallbackInfoReturnable<Block> cir) {

        if (Objects.equals(id, "grass_block")) {
            block = new GrassBlock(AbstractBlock.Settings.of(Material.LAVA).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.ANCIENT_DEBRIS));
        }

    }
}
