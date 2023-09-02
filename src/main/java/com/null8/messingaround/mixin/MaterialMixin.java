package com.null8.messingaround.mixin;

import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Contract;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Material.Builder.class)
public class MaterialMixin {
    @Shadow
    private PistonBehavior pistonBehavior;
    @Shadow
    private boolean blocksMovement;
    @Shadow
    private boolean burnable;
    @Shadow
    private boolean liquid;
    @Shadow
    private boolean replaceable;
    @Shadow
    private boolean solid;
    @Shadow
    private boolean blocksLight;



    @Contract(mutates = "this")
    @Inject(method = "build", at = @At("HEAD"), cancellable = true)
    private void build(CallbackInfoReturnable<Material> cir) {
        cir.setReturnValue(new Material(MapColor.get(Random.create().nextBetween(0, 63)), this.liquid, this.solid, this.blocksMovement, this.blocksLight, true, this.replaceable, this.pistonBehavior));
    }


}
