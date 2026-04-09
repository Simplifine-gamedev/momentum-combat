package com.orca.momentumcombat;

import net.minecraft.entity.player.PlayerEntity;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MomentumDamageTracker {
    private static final Map<UUID, Double> playerMultipliers = new ConcurrentHashMap<>();
    private static final Map<UUID, Long> lastAttackTime = new ConcurrentHashMap<>();
    private static final long MULTIPLIER_EXPIRY_MS = 500;

    public static void setMultiplier(PlayerEntity player, double multiplier) {
        playerMultipliers.put(player.getUuid(), multiplier);
        lastAttackTime.put(player.getUuid(), System.currentTimeMillis());
    }

    public static double getMultiplier(PlayerEntity player) {
        UUID uuid = player.getUuid();
        Long attackTime = lastAttackTime.get(uuid);

        if (attackTime != null && System.currentTimeMillis() - attackTime < MULTIPLIER_EXPIRY_MS) {
            Double mult = playerMultipliers.get(uuid);
            return mult != null ? mult : 1.0;
        }

        return 1.0;
    }

    public static void clearMultiplier(PlayerEntity player) {
        playerMultipliers.remove(player.getUuid());
        lastAttackTime.remove(player.getUuid());
    }
}
