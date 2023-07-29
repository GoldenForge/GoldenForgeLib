package org.bukkit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.bukkit.util.NumberConversions;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class Location {
    private Reference<Level> world;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;

    /**
     * Constructs a new Location with the given coordinates
     *
     * @param world The world in which this location resides
     * @param x The x-coordinate of this new location
     * @param y The y-coordinate of this new location
     * @param z The z-coordinate of this new location
     */
    public Location(final Level world, final double x, final double y, final double z) { // Paper
        this(world, x, y, z, 0, 0);
    }

    /**
     * Constructs a new Location with the given coordinates and direction
     *
     * @param world The world in which this location resides
     * @param x The x-coordinate of this new location
     * @param y The y-coordinate of this new location
     * @param z The z-coordinate of this new location
     * @param yaw The absolute rotation on the x-plane, in degrees
     * @param pitch The absolute rotation on the y-plane, in degrees
     */
    public Location(final Level world, final double x, final double y, final double z, final float yaw, final float pitch) { // Paper
        if (world != null) {
            this.world = new WeakReference<>(world);
        }

        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Sets the world that this location resides in
     *
     * @param world New world that this location resides in
     */
    public void setWorld(Level world) {
        this.world = (world == null) ? null : new WeakReference<>(world);
    }

    public Level getWorld() {
        if (this.world == null) {
            return null;
        }

        return this.world.get();
    }

    /**
     * Sets the x-coordinate of this location
     *
     * @param x X-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the x-coordinate of this location
     *
     * @return x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the floored value of the X component, indicating the block that
     * this location is contained with.
     *
     * @return block X
     */
    public int getBlockX() {
        return locToBlock(x);
    }

    /**
     * Sets the y-coordinate of this location
     *
     * @param y y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the y-coordinate of this location
     *
     * @return y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the floored value of the Y component, indicating the block that
     * this location is contained with.
     *
     * @return block y
     */
    public int getBlockY() {
        return locToBlock(y);
    }

    /**
     * Sets the z-coordinate of this location
     *
     * @param z z-coordinate
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Gets the z-coordinate of this location
     *
     * @return z-coordinate
     */
    public double getZ() {
        return z;
    }

    /**
     * Gets the floored value of the Z component, indicating the block that
     * this location is contained with.
     *
     * @return block z
     */
    public int getBlockZ() {
        return locToBlock(z);
    }

    /**
     * Sets the yaw of this location, measured in degrees.
     * <ul>
     * <li>A yaw of 0 or 360 represents the positive z direction.
     * <li>A yaw of 180 represents the negative z direction.
     * <li>A yaw of 90 represents the negative x direction.
     * <li>A yaw of 270 represents the positive x direction.
     * </ul>
     * Increasing yaw values are the equivalent of turning to your
     * right-facing, increasing the scale of the next respective axis, and
     * decreasing the scale of the previous axis.
     *
     * @param yaw new rotation's yaw
     */
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
     * Gets the yaw of this location, measured in degrees.
     * <ul>
     * <li>A yaw of 0 or 360 represents the positive z direction.
     * <li>A yaw of 180 represents the negative z direction.
     * <li>A yaw of 90 represents the negative x direction.
     * <li>A yaw of 270 represents the positive x direction.
     * </ul>
     * Increasing yaw values are the equivalent of turning to your
     * right-facing, increasing the scale of the next respective axis, and
     * decreasing the scale of the previous axis.
     *
     * @return the rotation's yaw
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * Sets the pitch of this location, measured in degrees.
     * <ul>
     * <li>A pitch of 0 represents level forward facing.
     * <li>A pitch of 90 represents downward facing, or negative y
     *     direction.
     * <li>A pitch of -90 represents upward facing, or positive y direction.
     * </ul>
     * Increasing pitch values the equivalent of looking down.
     *
     * @param pitch new incline's pitch
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * Gets the pitch of this location, measured in degrees.
     * <ul>
     * <li>A pitch of 0 represents level forward facing.
     * <li>A pitch of 90 represents downward facing, or negative y
     *     direction.
     * <li>A pitch of -90 represents upward facing, or positive y direction.
     * </ul>
     * Increasing pitch values the equivalent of looking down.
     *
     * @return the incline's pitch
     */
    public float getPitch() {
        return pitch;
    }

    public static int locToBlock(double loc) {
        return NumberConversions.floor(loc);
    }

    public void checkFinite() throws IllegalArgumentException {
        NumberConversions.checkFinite(x, "x not finite");
        NumberConversions.checkFinite(y, "y not finite");
        NumberConversions.checkFinite(z, "z not finite");
        NumberConversions.checkFinite(pitch, "pitch not finite");
        NumberConversions.checkFinite(yaw, "yaw not finite");
    }

    public static Location toLocation(Level world, BlockPos pos) {
        return new Location(world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static Location toLocation(Level world, Vec3 pos) {
        return new Location(world, pos.x(), pos.y(), pos.z());
    }
}
