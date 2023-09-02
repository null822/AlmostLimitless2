package com.null8.messingaround.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.chunk.AquiferSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AquiferSampler.Impl.class)
public abstract class AquiferSamplerMixin {
    @Shadow private final int startX;
    @Shadow private final int startY;
    @Shadow private final int startZ;
    @Shadow private final int sizeZ;
    @Shadow private final int sizeX;
    private final AquiferSampler.FluidLevel[] waterLevels;

    protected AquiferSamplerMixin(int startX, int startY, int startZ, int sizeZ, int sizeX, AquiferSampler.FluidLevel[] waterLevels) {
        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        this.sizeZ = sizeZ;
        this.sizeX = sizeX;
        this.waterLevels = waterLevels;
    }

    @Inject(method = "index", at = @At("HEAD"), cancellable = true)
    private void index(int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
        int i = x - this.startX;
        int j = y - this.startY;
        int k = z - this.startZ;
        cir.setReturnValue(Math.max(Math.min(j * this.sizeZ + k * this.sizeX + i, this.waterLevels.length-1), 0));
    }
}
