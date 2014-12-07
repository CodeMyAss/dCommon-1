package me.technopvp.common.utilities.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LocationUtils {

	public static String serializeLocation(Location l) {
		String s = "";
		s += "@w;" + l.getWorld().getName();

		s += ":@x;" + l.getX();

		s += ":@y;" + l.getY();

		s += ":@z;" + l.getZ();

		s += ":@p;" + l.getPitch();

		s += ":@ya;" + l.getYaw();
		return s;
	}

	public static Location deserializeLocation(String s) {
		Location l = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
		String[] att = s.split(":");
		for(String attribute : att) {
			String[] split = attribute.split(";");
			if(split[0].equalsIgnoreCase("@w")) l.setWorld(Bukkit.getWorld(split[1]));

			if(split[0].equalsIgnoreCase("@x")) l.setX(Double.parseDouble(split[1]));

			if(split[0].equalsIgnoreCase("@y")) l.setY(Double.parseDouble(split[1]));

			if(split[0].equalsIgnoreCase("@z")) l.setZ(Double.parseDouble(split[1]));

			if(split[0].equalsIgnoreCase("@p")) l.setPitch(Float.parseFloat(split[1]));

			if(split[0].equalsIgnoreCase("@ya")) l.setYaw(Float.parseFloat(split[1]));
		}
		return l;
	}

	public static Boolean isInside(Location loc, Location corner1, Location corner2) {
        double xMin = 0;
        double xMax = 0;
        double yMin = 0;
        double yMax = 0;
        double zMin = 0;
        double zMax = 0;
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();

        xMin = Math.min(corner1.getX(), corner2.getX());
        xMax = Math.max(corner1.getX(), corner2.getX());

        yMin = Math.min(corner1.getY(), corner2.getY());
        yMax = Math.max(corner1.getY(), corner2.getY());

        zMin = Math.min(corner1.getZ(), corner2.getZ());
        zMax = Math.max(corner1.getZ(), corner2.getZ());

        return (x >= xMin && x <= xMax && y >= yMin && y <= yMax && z >= zMin && z <= zMax);
    }

	public ArrayList<Location> getCircle(Location center, double radius, int amount){
        World world = center.getWorld();
        double increment = (2*Math.PI)/amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for(int i = 0;i < amount; i++){
	        double angle = i*increment;
	        double x = center.getX() + (radius * Math.cos(angle));
	        double z = center.getZ() + (radius * Math.sin(angle));
	        locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

	/**
	 * @param position1
	 *            - First position
	 * @param position2
	 *            - Second position
	 * @return Cuboid
	 * @note - it will return all the blocks from "position1" till "position2".
	 * @credits - CaptainBern
	 */
	public static List<Location> getCuboid(Location position1,
			Location position2) {

		if (position1.getWorld().getName() != position2.getWorld().getName()) {
			throw new UnsupportedOperationException(
					"'Position1' and 'Position2' location need to be in the same world!");
		}

		List<Location> cube = new ArrayList<Location>();

		int minX = (int) Math.min(position1.getX(), position2.getX());
		int maxX = (int) Math.max(position1.getX(), position2.getX());

		int minY = (int) Math.min(position1.getY(), position2.getY());
		int maxY = (int) Math.max(position1.getY(), position2.getY());

		int minZ = (int) Math.min(position1.getZ(), position2.getZ());
		int maxZ = (int) Math.max(position1.getZ(), position2.getZ());

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					cube.add(new Location(position1.getWorld(), x, y, z));
				}
			}
		}
		return cube;
	}

	public static boolean insideLocation(Location location, int radiusOut) {
		return (location.getBlockX() <= radiusOut && location.getBlockZ() <= radiusOut && location.getBlockX() >= (radiusOut-(radiusOut*2)) && location.getBlockZ() >= (radiusOut-(radiusOut*2)));
	}


	public static boolean inArea(Location targetLocation, Location inAreaLocation1, Location inAreaLocation2){
	    if(inAreaLocation1.getWorld().getName() == inAreaLocation2.getWorld().getName()){
	        if(targetLocation.getWorld().getName() == inAreaLocation1.getWorld().getName()){
	            if((targetLocation.getBlockX() >= inAreaLocation1.getBlockX() && targetLocation.getBlockX() <= inAreaLocation2.getBlockX()) || (targetLocation.getBlockX() <= inAreaLocation1.getBlockX() && targetLocation.getBlockX() >= inAreaLocation2.getBlockX())){
	                if((targetLocation.getBlockZ() >= inAreaLocation1.getBlockZ() && targetLocation.getBlockZ() <= inAreaLocation2.getBlockZ()) || (targetLocation.getBlockZ() <= inAreaLocation1.getBlockZ() && targetLocation.getBlockZ() >= inAreaLocation2.getBlockZ())){
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}

    public static void gotoWorld(CommandSender sender, String targetworld) {
        Player player = (Player) sender;
        if (player.getWorld().getName().equalsIgnoreCase(targetworld)) {
            sender.sendMessage(ChatColor.GRAY +  "Going to main world.");
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            return;
        }
        for (World world : Bukkit.getWorlds()) {
            if (world.getName().equalsIgnoreCase(targetworld)) {
                sender.sendMessage(ChatColor.GRAY + "Going to world: " + targetworld);
                player.teleport(world.getSpawnLocation());
                return;
            }
        }
        sender.sendMessage(ChatColor.GRAY + "World " + targetworld + " not found.");
    }

	/**
	 * @param position1
	 *            - First position
	 * @param position2
	 *            - Second position
	 * @return Plain Cuboid
	 * @note - it will return only the blocks that are in the same level as the
	 *       "position1" and till the "position2".
	 */
	public static List<Location> getPlain(Location position1, Location position2) {
		List<Location> plain = new ArrayList<Location>();
		if (position1 == null)
			return plain;
		if (position2 == null)
			return plain;
		for (int x = Math.min(position1.getBlockX(), position2.getBlockX()); x <= Math
				.max(position1.getBlockX(), position2.getBlockX()); x++)
			for (int z = Math.min(position1.getBlockZ(), position2.getBlockZ()); z <= Math
					.max(position1.getBlockZ(), position2.getBlockZ()); z++)
				plain.add(new Location(position1.getWorld(), x, position1
						.getBlockY(), z));
		return plain;
	}

	/**
	 * @param position1
	 *            - First position
	 * @param position2
	 *            - Second position
	 * @param getOnlyAboveGround
	 *            - boolean (see the notes);
	 * @return Cuboid
	 * @note1 - if "land" is activated, it will return air blocks only one block
	 *        above the ground;
	 * @note2 - if "land" is deactivated, it will return only the air blocks in
	 *        the cuboid.
	 */
	public static List<Location> getBlocks(Location position1,
			Location position2, boolean getOnlyAboveGround) {
		List<Location> blocks = new ArrayList<Location>();
		if (position1 == null)
			return blocks;
		if (position2 == null)
			return blocks;

		for (int x = Math.min(position1.getBlockX(), position2.getBlockX()); x <= Math
				.max(position1.getBlockX(), position2.getBlockX()); x++)
			for (int z = Math.min(position1.getBlockZ(), position2.getBlockZ()); z <= Math
					.max(position1.getBlockZ(), position2.getBlockZ()); z++)
				for (int y = Math.min(position1.getBlockY(),
						position2.getBlockY()); y <= Math.max(
						position1.getBlockY(), position2.getBlockY()); y++) {
					Block b = position1.getWorld().getBlockAt(x, y, z);
					if ((b.getType() == Material.AIR)
							&& ((!getOnlyAboveGround) || (b.getRelative(
									BlockFace.DOWN).getType() != Material.AIR)))
						blocks.add(b.getLocation());
				}
		return blocks;
	}


	/**
	 * @param position1
	 *            - First position
	 * @param position2
	 *            - Second position
	 * @return Line
	 * @note - it will return only the blocks that are in diagonal from
	 *       "position1" till "position2".
	 */
	public static List<Location> getLine(Location position1, Location position2) {
		List<Location> line = new ArrayList<Location>();
		int dx = Math.max(position1.getBlockX(), position2.getBlockX())
				- Math.min(position1.getBlockX(), position2.getBlockX());
		int dy = Math.max(position1.getBlockY(), position2.getBlockY())
				- Math.min(position1.getBlockY(), position2.getBlockY());
		int dz = Math.max(position1.getBlockZ(), position2.getBlockZ())
				- Math.min(position1.getBlockZ(), position2.getBlockZ());
		int x1 = position1.getBlockX();
		int x2 = position2.getBlockX();
		int y1 = position1.getBlockY();
		int y2 = position2.getBlockY();
		int z1 = position1.getBlockZ();
		int z2 = position2.getBlockZ();
		int x = 0;
		int y = 0;
		int z = 0;
		int i = 0;
		int d = 1;
		switch (getHighest(dx, dy, dz)) {
		case 1:
			i = 0;
			d = 1;
			if (x1 > x2)
				d = -1;
			x = position1.getBlockX();
			do {
				i++;
				y = y1 + (x - x1) * (y2 - y1) / (x2 - x1);
				z = z1 + (x - x1) * (z2 - z1) / (x2 - x1);
				line.add(new Location(position1.getWorld(), x, y, z));
				x += d;
			} while (i <= Math.max(x1, x2) - Math.min(x1, x2));
			break;
		case 2:
			i = 0;
			d = 1;
			if (y1 > y2)
				d = -1;
			y = position1.getBlockY();
			do {
				i++;
				x = x1 + (y - y1) * (x2 - x1) / (y2 - y1);
				z = z1 + (y - y1) * (z2 - z1) / (y2 - y1);
				line.add(new Location(position1.getWorld(), x, y, z));
				y += d;
			} while (i <= Math.max(y1, y2) - Math.min(y1, y2));
			break;
		case 3:
			i = 0;
			d = 1;
			if (z1 > z2)
				d = -1;
			z = position1.getBlockZ();
			do {
				i++;
				y = y1 + (z - z1) * (y2 - y1) / (z2 - z1);
				x = x1 + (z - z1) * (x2 - x1) / (z2 - z1);
				line.add(new Location(position1.getWorld(), x, y, z));
				z += d;
			} while (i <= Math.max(z1, z2) - Math.min(z1, z2));
		}

		return line;
	}

	// support
	private static int getHighest(int x, int y, int z) {
		if ((x >= y) && (x >= z))
			return 1;
		if ((y >= x) && (y >= z))
			return 2;
		return 3;
	}

	/**
	 * @param location
	 *            - Initial location
	 * @param radius
	 *            - distance from the "location" that will return all the
	 *            entities from each block;
	 * @return HashSet(LivingEntity)
	 * @note - it will return only Living Entities in a radius, such as players,
	 *       mobs and animals.
	 * @credits - skore87 (little modification by me)
	 */
	public static HashSet<LivingEntity> getNearbyEntities(Location location,
			int radius) {
		int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
		HashSet<LivingEntity> radiusEntities = new HashSet<LivingEntity>();

		for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
			for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
				int x = (int) location.getX(), y = (int) location.getY(), z = (int) location
						.getZ();
				for (Entity e : new Location(location.getWorld(), x
						+ (chX * 16), y, z + (chZ * 16)).getChunk()
						.getEntities()) {
					if (e.getLocation().distance(location) <= radius
							&& e.getLocation().getBlock() != location
									.getBlock())
						if (e instanceof LivingEntity) {
							radiusEntities.add((LivingEntity) e);
						}
				}
			}
		}
		return radiusEntities;
	}

	/**
	 * @param center
	 * @param radiusX
	 * @param radiusZ
	 * @param height
	 * @param filled
	 * @credits bobacadodl
	 * @return
	 */

	public static List<Location> getCylinder(Location center, double radiusX,
			double radiusZ, int height, boolean filled) {
		Vector pos = center.toVector();
		World world = center.getWorld();
		List<Location> blocks = new ArrayList<Location>();
		radiusX += 0.5;
		radiusZ += 0.5;

		if (height == 0) {
			return blocks;
		} else if (height < 0) {
			height = -height;
			pos = pos.subtract(new Vector(0, height, 0));
		}

		if (pos.getBlockY() < 0) {
			pos = pos.setY(0);
		} else if (pos.getBlockY() + height - 1 > world.getMaxHeight()) {
			height = world.getMaxHeight() - pos.getBlockY() + 1;
		}

		final double invRadiusX = 1 / radiusX;
		final double invRadiusZ = 1 / radiusZ;

		final int ceilRadiusX = (int) Math.ceil(radiusX);
		final int ceilRadiusZ = (int) Math.ceil(radiusZ);

		double nextXn = 0;
		forX: for (int x = 0; x <= ceilRadiusX; ++x) {
			final double xn = nextXn;
			nextXn = (x + 1) * invRadiusX;
			double nextZn = 0;
			forZ: for (int z = 0; z <= ceilRadiusZ; ++z) {
				final double zn = nextZn;
				nextZn = (z + 1) * invRadiusZ;

				double distanceSq = lengthSq(xn, zn);
				if (distanceSq > 1) {
					if (z == 0) {
						break forX;
					}
					break forZ;
				}

				if (!filled) {
					if (lengthSq(nextXn, zn) <= 1 && lengthSq(xn, nextZn) <= 1) {
						continue;
					}
				}

				for (int y = 0; y < height; ++y) {

					blocks.add(pos.add(new Vector(x, y, z)).toLocation(world));
					blocks.add(pos.add(new Vector(-x, y, z)).toLocation(world));
					blocks.add(pos.add(new Vector(x, y, -z)).toLocation(world));
					blocks.add(pos.add(new Vector(-x, y, -z)).toLocation(world));
				}
			}
		}

		return blocks;
	}

	public static void generateHollowCube(Location location, int length, Material material) {
        final Block center = location.getBlock();
        for (int xOffset = -length; xOffset <= length; xOffset++) {
            for (int yOffset = -length; yOffset <= length; yOffset++) {
                for (int zOffset = -length; zOffset <= length; zOffset++) {
                    // Hollow
                    if (Math.abs(xOffset) != length && Math.abs(yOffset) != length && Math.abs(zOffset) != length) {
                        continue;
                    }
                    final Block block = center.getRelative(xOffset, yOffset, zOffset);
                    if (material != Material.SKULL) {
                        // Glowstone light
                        if (material != Material.GLASS && xOffset == 0 && yOffset == 2 && zOffset == 0) {
                            block.setType(Material.GLOWSTONE);
                            continue;
                        }
                        block.setType(material);
                    }else {
                        if (Math.abs(xOffset) == length && Math.abs(yOffset) == length && Math.abs(zOffset) == length) {
                            block.setType(Material.GLOWSTONE);
                            continue;
                        }
                        block.setType(Material.SKULL);
                        final Skull skull = (Skull) block.getState();
                        skull.setSkullType(SkullType.PLAYER);
                        skull.setOwner("tpvp");
                        skull.update();
                    }
                }
            }
        }
    }

    public static int replaceBlocks(Location center, Material fromMaterial, Material toMaterial, int radius)
    {
        int affected = 0;

        Block centerBlock = center.getBlock();
        for (int xOffset = -radius; xOffset <= radius; xOffset++)
        {
            for (int yOffset = -radius; yOffset <= radius; yOffset++)
            {
                for (int zOffset = -radius; zOffset <= radius; zOffset++)
                {
                    Block block = centerBlock.getRelative(xOffset, yOffset, zOffset);

                    if (block.getType().equals(fromMaterial))
                    {
                        if (block.getLocation().distanceSquared(center) < (radius * radius))
                        {
                            block.setType(toMaterial);
                            affected++;
                        }
                    }
                }
            }
        }

        return affected;
    }

	 public static String locationToString(Location loc) {
	        return loc.getWorld().getName() + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch();
	    }

	/**
	 * @param center
	 * @param radiusX
	 * @param radiusY
	 * @param radiusZ
	 * @param filled
	 * @credits bobacadodl
	 * @return
	 */

	public static List<Location> getSphere(Location center, double radiusX,
			double radiusY, double radiusZ, boolean filled) {
		Vector pos = center.toVector();
		World world = center.getWorld();
		List<Location> blocks = new ArrayList<Location>();

		radiusX += 0.5;
		radiusY += 0.5;
		radiusZ += 0.5;

		final double invRadiusX = 1 / radiusX;
		final double invRadiusY = 1 / radiusY;
		final double invRadiusZ = 1 / radiusZ;

		final int ceilRadiusX = (int) Math.ceil(radiusX);
		final int ceilRadiusY = (int) Math.ceil(radiusY);
		final int ceilRadiusZ = (int) Math.ceil(radiusZ);

		double nextXn = 0;
		forX: for (int x = 0; x <= ceilRadiusX; ++x) {
			final double xn = nextXn;
			nextXn = (x + 1) * invRadiusX;
			double nextYn = 0;
			forY: for (int y = 0; y <= ceilRadiusY; ++y) {
				final double yn = nextYn;
				nextYn = (y + 1) * invRadiusY;
				double nextZn = 0;
				forZ: for (int z = 0; z <= ceilRadiusZ; ++z) {
					final double zn = nextZn;
					nextZn = (z + 1) * invRadiusZ;

					double distanceSq = lengthSq(xn, yn, zn);
					if (distanceSq > 1) {
						if (z == 0) {
							if (y == 0) {
								break forX;
							}
							break forY;
						}
						break forZ;
					}
					if (!filled) {
						if (lengthSq(nextXn, yn, zn) <= 1
								&& lengthSq(xn, nextYn, zn) <= 1
								&& lengthSq(xn, yn, nextZn) <= 1) {
							continue;
						}
					}
					blocks.add(pos.add(new Vector(x, y, z)).toLocation(world));
					blocks.add(pos.add(new Vector(-x, y, z)).toLocation(world));
					blocks.add(pos.add(new Vector(x, -y, z)).toLocation(world));
					blocks.add(pos.add(new Vector(x, y, -z)).toLocation(world));
					blocks.add(pos.add(new Vector(-x, -y, z)).toLocation(world));
					blocks.add(pos.add(new Vector(x, -y, -z)).toLocation(world));
					blocks.add(pos.add(new Vector(-x, y, -z)).toLocation(world));
					blocks.add(pos.add(new Vector(-x, -y, -z))
							.toLocation(world));
				}
			}
		}

		return blocks;
	}

	/**
	 * @param x
	 * @param y
	 * @param z b
	 * @return
	 */

	private static final double lengthSq(double x, double y, double z) {
		return (x * x) + (y * y) + (z * z);
	}

	/**
	 * @param x
	 * @param z
	 * @return
	 */

	private static final double lengthSq(double x, double z) {
		return (x * x) + (z * z);
	}

}