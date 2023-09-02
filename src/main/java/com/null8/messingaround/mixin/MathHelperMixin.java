package com.null8.messingaround.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Mixin(MathHelper.class)
public abstract class MathHelperMixin {

    @Inject(method = "clamp(DDD)D", at = @At("HEAD"), cancellable = true)
    private static void clamp(double value, double min, double max, CallbackInfoReturnable<Double> cir) {
        if ((max == 2.9999999E7 && min == -2.9999999E7) || (max == 3E7 && min == -3E7)) {

            cir.setReturnValue(value);

        } else {
            cir.setReturnValue(Math.min(Math.max(value, min), max));
        }
    }

}
