package com.null8.messingaround.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
/*
    @Inject(method = "<init>", at = @At("HEAD"))
    private static void Block(AbstractBlock.Settings settings, CallbackInfo ci) {

    }
*/

    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        entity.addVelocity(0.0, 0.1, 0.0);
    }

}
