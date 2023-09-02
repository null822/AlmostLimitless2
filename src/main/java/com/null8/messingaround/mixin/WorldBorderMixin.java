package com.null8.messingaround.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldBorder.class)
public abstract class WorldBorderMixin {

    //@Contract(mutates = "this")
    @Inject(method = "canCollide", at = @At("HEAD"), cancellable = true)
    private void canCollide(Entity entity, Box box, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(method = "contains(Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    private void contains(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method = "contains(Lnet/minecraft/util/math/Box;)Z", at = @At("HEAD"), cancellable = true)
    private void contains(Box box, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method = "contains(Lnet/minecraft/util/math/ChunkPos;)Z", at = @At("HEAD"), cancellable = true)
    private void contains(ChunkPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(method = "getDistanceInsideBorder(DD)D", at = @At("HEAD"), cancellable = true)
    private void getDistanceInsideBorder(double x, double z, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(Double.MAX_VALUE);
    }


    @Inject(method = "getMaxRadius", at = @At("HEAD"), cancellable = true)
    private void getMaxRadius(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Integer.MAX_VALUE);
    }







}
