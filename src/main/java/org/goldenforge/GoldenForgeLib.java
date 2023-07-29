package org.goldenforge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import org.bukkit.Location;

import java.util.concurrent.CompletableFuture;

public class GoldenForgeLib {

    private GoldenForgeLib() {

    }

    public static boolean isGoldenForge() {
        return hasClass("org.goldenforge.GoldenForge");
    }

    private static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static CompletableFuture<LevelChunk> getChunkAtAsyncUrgently(Level world, int x, int z, boolean gen) {
        return world.getChunkAtAsync(x, z, gen, true);
    }

    public static CompletableFuture<Boolean> teleportAsync(ServerPlayer player, Location location) {
        int x = location.getBlockX() >> 4;
        int z = location.getBlockZ() >> 4;
        return getChunkAtAsyncUrgently(location.getWorld(), x, z, true).thenApply(chunk -> player.teleport(location));
    }
}
