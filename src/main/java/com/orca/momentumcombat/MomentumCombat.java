package com.orca.momentumcombat;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MomentumCombat implements ModInitializer {
    public static final String MOD_ID = "momentum-combat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Momentum Combat initialized! Movement affects your damage!");

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient && entity instanceof LivingEntity) {
                double multiplier = calculateDamageMultiplier(player);
                MomentumDamageTracker.setMultiplier(player, multiplier);

                String multiplierText = String.format("%.1fx", multiplier);
                Formatting color = getMultiplierColor(multiplier);
                player.sendMessage(Text.literal("Momentum: " + multiplierText + " damage").formatted(color), true);
            }
            return ActionResult.PASS;
        });
    }

    public static double calculateDamageMultiplier(PlayerEntity player) {
        double multiplier = 1.0;

        Vec3d velocity = player.getVelocity();
        double horizontalSpeed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);

        boolean isOnGround = player.isOnGround();
        boolean isSprinting = player.isSprinting();
        boolean isFalling = velocity.y < -0.1 && !isOnGround;
        boolean isJumping = velocity.y > 0.1 && !isOnGround;

        if (isOnGround && horizontalSpeed < 0.01) {
            multiplier = 0.5;
        }
        else if (isFalling) {
            double fallIntensity = Math.abs(velocity.y);
            multiplier = Math.min(3.0, 1.0 + (fallIntensity * 4.0));
        }
        else if (isJumping) {
            multiplier = 1.5;
        }
        else if (isSprinting) {
            multiplier = 1.3;
        }
        else if (horizontalSpeed > 0.1) {
            multiplier = 1.0 + (horizontalSpeed * 0.5);
            multiplier = Math.min(multiplier, 1.2);
        }

        return multiplier;
    }

    private static Formatting getMultiplierColor(double multiplier) {
        if (multiplier >= 2.5) return Formatting.DARK_RED;
        if (multiplier >= 2.0) return Formatting.RED;
        if (multiplier >= 1.5) return Formatting.GOLD;
        if (multiplier >= 1.3) return Formatting.YELLOW;
        if (multiplier >= 1.0) return Formatting.WHITE;
        return Formatting.GRAY;
    }
}
