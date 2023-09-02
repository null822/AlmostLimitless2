package com.null8.messingaround.mixin;

import net.minecraft.world.ChunkRegion;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.chunk.AquiferSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.annotation.Target;

@Mixin(ChunkRegion.class)
public abstract class ChunkRegionMixin implements WorldView {


    @Inject(method = "getChunk(II)Lnet/minecraft/world/chunk/Chunk;", at = @At("HEAD"), cancellable = true)
    public void getChunk(int chunkX, int chunkZ, CallbackInfoReturnable<Chunk> cir) {
        try {
            cir.setReturnValue(this.getChunk(chunkX, chunkZ, ChunkStatus.EMPTY));
        } catch (Exception ignored) {
            cir.setReturnValue(this.getChunk(0, 0));
        }
    }
}
