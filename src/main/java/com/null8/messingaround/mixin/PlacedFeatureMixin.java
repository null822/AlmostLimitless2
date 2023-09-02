package com.null8.messingaround.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;
import java.util.Optional;

@Mixin(PlacedFeature.class)
public abstract class PlacedFeatureMixin {
    @Shadow protected abstract boolean generate(FeaturePlacementContext context, Random random, BlockPos pos);

    @Overwrite

    public boolean generate(StructureWorldAccess world, ChunkGenerator generator, Random random, BlockPos pos) {
        try {
            return this.generate(new FeaturePlacementContext(world, generator, Optional.of((PlacedFeature) (Object) this)), random, pos);
        } catch (Throwable t) {
            return false;
        }
    }
}
