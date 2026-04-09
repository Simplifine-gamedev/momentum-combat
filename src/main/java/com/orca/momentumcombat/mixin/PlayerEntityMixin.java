package com.orca.momentumcombat.mixin;

import com.orca.momentumcombat.MomentumDamageTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @ModifyVariable(
        method = "attack",
        at = @At(value = "STORE", ordinal = 0),
        ordinal = 0
    )
    private float modifyAttackDamage(float originalDamage) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        double multiplier = MomentumDamageTracker.getMultiplier(player);
        float newDamage = (float) (originalDamage * multiplier);
        return newDamage;
    }
}
