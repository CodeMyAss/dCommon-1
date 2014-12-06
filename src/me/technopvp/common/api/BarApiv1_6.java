package me.technopvp.common.api;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

/**
* This is the FakeDragon class for BarAPI.
* It is based on the code by SoThatsIt.
*
* http://forums.bukkit.org/threads/tutorial-utilizing-the-boss-health-bar.158018/page-2#post-1760928
*
* @author James Mortemore
*/

public class BarApiv1_6 extends BarApiFakeDragon {
	private static final Integer EntityID = 6000;

	public BarApiv1_6(String name, Location loc) {
		super(name, loc);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object getSpawnPacket() {
		Class<?> mob_class = BarApiUtil.getCraftClass("Packet24MobSpawn");
		Object mobPacket = null;
		try {
			mobPacket = mob_class.newInstance();

			Field a = BarApiUtil.getField(mob_class, "a");
			a.setAccessible(true);
			a.set(mobPacket, EntityID);// Entity ID
			Field b = BarApiUtil.getField(mob_class, "b");
			b.setAccessible(true);
			b.set(mobPacket, EntityType.ENDER_DRAGON.getTypeId());// Mob type
																	// (ID: 64)
			Field c = BarApiUtil.getField(mob_class, "c");
			c.setAccessible(true);
			c.set(mobPacket, getX());// X position
			Field d = BarApiUtil.getField(mob_class, "d");
			d.setAccessible(true);
			d.set(mobPacket, getY());// Y position
			Field e = BarApiUtil.getField(mob_class, "e");
			e.setAccessible(true);
			e.set(mobPacket, getZ());// Z position
			Field f = BarApiUtil.getField(mob_class, "f");
			f.setAccessible(true);
			f.set(mobPacket, (byte) ((int) (getPitch() * 256.0F / 360.0F)));// Pitch
			Field g = BarApiUtil.getField(mob_class, "g");
			g.setAccessible(true);
			g.set(mobPacket, (byte) ((int) 0));// Head
												// Pitch
			Field h = BarApiUtil.getField(mob_class, "h");
			h.setAccessible(true);
			h.set(mobPacket, (byte) ((int) (getYaw() * 256.0F / 360.0F)));// Yaw
			Field i = BarApiUtil.getField(mob_class, "i");
			i.setAccessible(true);
			i.set(mobPacket, getXvel());// X velocity
			Field j = BarApiUtil.getField(mob_class, "j");
			j.setAccessible(true);
			j.set(mobPacket, getYvel());// Y velocity
			Field k = BarApiUtil.getField(mob_class, "k");
			k.setAccessible(true);
			k.set(mobPacket, getZvel());// Z velocity

			Object watcher = getWatcher();
			Field t = BarApiUtil.getField(mob_class, "t");
			t.setAccessible(true);
			t.set(mobPacket, watcher);
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}

		return mobPacket;
	}

	@Override
	public Object getDestroyPacket() {
		Class<?> packet_class = BarApiUtil.getCraftClass("Packet29DestroyEntity");
		Object packet = null;
		try {
			packet = packet_class.newInstance();

			Field a = BarApiUtil.getField(packet_class, "a");
			a.setAccessible(true);
			a.set(packet, new int[] { EntityID });
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return packet;
	}

	@Override
	public Object getMetaPacket(Object watcher) {
		Class<?> packet_class = BarApiUtil.getCraftClass("Packet40EntityMetadata");
		Object packet = null;
		try {
			packet = packet_class.newInstance();

			Field a = BarApiUtil.getField(packet_class, "a");
			a.setAccessible(true);
			a.set(packet, EntityID);

			Method watcher_c = BarApiUtil.getMethod(watcher.getClass(), "c");
			Field b = BarApiUtil.getField(packet_class, "b");
			b.setAccessible(true);
			b.set(packet, watcher_c.invoke(watcher));
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return packet;
	}

	@Override
	public Object getTeleportPacket(Location loc) {
		Class<?> packet_class = BarApiUtil.getCraftClass("Packet34EntityTeleport");
		Object packet = null;
		try {
			packet = packet_class.newInstance();

			Field a = BarApiUtil.getField(packet_class, "a");
			a.setAccessible(true);
			a.set(packet, EntityID);
			Field b = BarApiUtil.getField(packet_class, "b");
			b.setAccessible(true);
			b.set(packet, (int) Math.floor(loc.getX() * 32.0D));
			Field c = BarApiUtil.getField(packet_class, "c");
			c.setAccessible(true);
			c.set(packet, (int) Math.floor(loc.getY() * 32.0D));
			Field d = BarApiUtil.getField(packet_class, "d");
			d.setAccessible(true);
			d.set(packet, (int) Math.floor(loc.getZ() * 32.0D));
			Field e = BarApiUtil.getField(packet_class, "e");
			e.setAccessible(true);
			e.set(packet, (byte) ((int) (loc.getYaw() * 256.0F / 360.0F)));
			Field f = BarApiUtil.getField(packet_class, "f");
			f.setAccessible(true);
			f.set(packet, (byte) ((int) (loc.getPitch() * 256.0F / 360.0F)));
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return packet;
	}

	@Override
	public Object getWatcher() {
		Class<?> watcher_class = BarApiUtil.getCraftClass("DataWatcher");
		Object watcher = null;
		try {
			watcher = watcher_class.newInstance();

			Method a = BarApiUtil.getMethod(watcher_class, "a", new Class<?>[] { int.class, Object.class });
			a.setAccessible(true);

			a.invoke(watcher, 0, isVisible() ? (byte) 0 : (byte) 0x20);
			a.invoke(watcher, 6, (Float) (float) health);
			a.invoke(watcher, 7, (Integer) (int) 0);
			a.invoke(watcher, 8, (Byte) (byte) 0);
			a.invoke(watcher, 10, (String) name);
			a.invoke(watcher, 11, (Byte) (byte) 1);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return watcher;
	}

}
