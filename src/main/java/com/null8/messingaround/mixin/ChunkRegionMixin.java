package com.null8.messingaround.mixin;

import net.minecraft.util.Util;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.chunk.AquiferSampler;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.annotation.Target;
import java.util.List;
import java.util.Locale;

@Mixin(ChunkRegion.class)
public abstract class ChunkRegionMixin implements WorldView {


    @Shadow @Final private ChunkPos lowerCorner;

    @Shadow @Final private List<Chunk> chunks;

    @Shadow @Final private int width;

    @Shadow @Final private static Logger LOGGER;

    @Shadow @Final private ChunkPos upperCorner;

    @Inject(method = "getChunk(II)Lnet/minecraft/world/chunk/Chunk;", at = @At("HEAD"), cancellable = true)
    public void getChunk(int chunkX, int chunkZ, CallbackInfoReturnable<Chunk> cir) {
        try {
            cir.setReturnValue(this.getChunk(chunkX, chunkZ, ChunkStatus.EMPTY));
        } catch (Exception ignored) {
            cir.setReturnValue(this.getChunk(chunkX, chunkZ, ChunkStatus.EMPTY));
        }
    }

    @Overwrite
    @Nullable
    public Chunk getChunk(int chunkX, int chunkZ, ChunkStatus leastStatus, boolean create) {
        Chunk chunk;
        if (this.isChunkLoaded(chunkX, chunkZ)) {
            int i = chunkX - this.lowerCorner.x;
            int j = chunkZ - this.lowerCorner.z;
            chunk = (Chunk)this.chunks.get(i + j * this.width);
            if (chunk.getStatus().isAtLeast(leastStatus)) {
                return chunk;
            }
     //   else {
            chunk = null;
        }

        if (!create) {
            return null;
        } else {
          //  LOGGER.error((String)"Requested chunk : {} {}", (Object)chunkX, (Object)chunkZ);
          //  LOGGER.error("Region bounds : {} {} | {} {}", this.lowerCorner.x, this.lowerCorner.z, this.upperCorner.x, this.upperCorner.z);
//            if (chunk != null) {
//                throw (RuntimeException) Util.throwOrPause(new RuntimeException(String.format(Locale.ROOT, "Chunk is not of correct status. Expecting %s, got %s | %s %s", leastStatus, chunk.getStatus(), chunkX, chunkZ)));
//            } else {
//                throw (RuntimeException)Util.throwOrPause(new RuntimeException(String.format(Locale.ROOT, "We are asking a region for a chunk out of bound | %s %s", chunkX, chunkZ)));
//            }
        }
        return null;
    }
}
