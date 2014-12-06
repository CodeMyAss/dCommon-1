package me.technopvp.common.api;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Achievement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.Statistic.Type;
import org.bukkit.craftbukkit.libs.com.google.gson.stream.JsonWriter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FancyMessage {
	
	private final List<FancyApiMessagePart> messageParts;
	private String jsonString;
	private boolean dirty;
	
	private Class<?> nmsChatSerializer = ParticalApiReflection.getNMSClass("ChatSerializer");
	private Class<?> nmsTagCompound = ParticalApiReflection.getNMSClass("NBTTagCompound");
	private Class<?> nmsPacketPlayOutChat = ParticalApiReflection.getNMSClass("PacketPlayOutChat");
	private Class<?> nmsAchievement = ParticalApiReflection.getNMSClass("Achievement");
	private Class<?> nmsStatistic = ParticalApiReflection.getNMSClass("Statistic");
	private Class<?> nmsItemStack = ParticalApiReflection.getNMSClass("ItemStack");

	private Class<?> obcStatistic = ParticalApiReflection.getOBCClass("CraftStatistic");
	private Class<?> obcItemStack = ParticalApiReflection.getOBCClass("inventory.CraftItemStack");
	
	public FancyMessage(final String firstPartText) {
		messageParts = new ArrayList<FancyApiMessagePart>();
		messageParts.add(new FancyApiMessagePart(firstPartText));
		jsonString = null;
		dirty = false;
	}
	
	public FancyMessage() {
		messageParts = new ArrayList<FancyApiMessagePart>();
		messageParts.add(new FancyApiMessagePart());
		jsonString = null;
		dirty = false;
	}
	
	public FancyMessage text(String text) {
		FancyApiMessagePart latest = latest();
		if (latest.hasText()) {
			throw new IllegalStateException("text for this message part is already set");
		}
		latest.text = text;
		dirty = true;
		return this;
	}
	
	public FancyMessage color(final ChatColor color) {
		if (!color.isColor()) {
			throw new IllegalArgumentException(color.name() + " is not a color");
		}
		latest().color = color;
		dirty = true;
		return this;
	}
	
	public FancyMessage style(ChatColor... styles) {
		for (final ChatColor style : styles) {
			if (!style.isFormat()) {
				throw new IllegalArgumentException(style.name() + " is not a style");
			}
		}
		latest().styles.addAll(Arrays.asList(styles));
		dirty = true;
		return this;
	}
	
	public FancyMessage file(final String path) {
		onClick("open_file", path);
		return this;
	}
	
	public FancyMessage link(final String url) {
		onClick("open_url", url);
		return this;
	}
	
	public FancyMessage suggest(final String command) {
		onClick("suggest_command", command);
		return this;
	}
	
	public FancyMessage command(final String command) {
		onClick("run_command", command);
		return this;
	}
	
	public FancyMessage achievementTooltip(final String name) {
		onHover("show_achievement", "achievement." + name);
		return this;
	}
	
	public FancyMessage achievementTooltip(final Achievement which) {
		try {
			Object achievement = ParticalApiReflection.getMethod(obcStatistic, "getNMSAchievement").invoke(null, which);
			return achievementTooltip((String) ParticalApiReflection.getField(nmsAchievement, "name").get(achievement));
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
	}
	
	public FancyMessage statisticTooltip(final Statistic which) {
		Type type = which.getType();
		if (type != Type.UNTYPED) {
			throw new IllegalArgumentException("That statistic requires an additional " + type + " parameter!");
		}
		try {
			Object statistic = ParticalApiReflection.getMethod(obcStatistic, "getNMSStatistic").invoke(null, which);
			return achievementTooltip((String) ParticalApiReflection.getField(nmsStatistic, "name").get(statistic));
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
	}
	
	public FancyMessage statisticTooltip(final Statistic which, Material item) {
		Type type = which.getType();
		if (type == Type.UNTYPED) {
			throw new IllegalArgumentException("That statistic needs no additional parameter!");
		}
		if ((type == Type.BLOCK && item.isBlock()) || type == Type.ENTITY) {
			throw new IllegalArgumentException("Wrong parameter type for that statistic - needs " + type + "!");
		}
		try {
			Object statistic = ParticalApiReflection.getMethod(obcStatistic, "getMaterialStatistic").invoke(null, which, item);
			return achievementTooltip((String) ParticalApiReflection.getField(nmsStatistic, "name").get(statistic));
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
	}
	
	public FancyMessage statisticTooltip(final Statistic which, EntityType entity) {
		Type type = which.getType();
		if (type == Type.UNTYPED) {
			throw new IllegalArgumentException("That statistic needs no additional parameter!");
		}
		if (type != Type.ENTITY) {
			throw new IllegalArgumentException("Wrong parameter type for that statistic - needs " + type + "!");
		}
		try {
			Object statistic = ParticalApiReflection.getMethod(obcStatistic, "getEntityStatistic").invoke(null, which, entity);
			return achievementTooltip((String) ParticalApiReflection.getField(nmsStatistic, "name").get(statistic));
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
	}
	
	public FancyMessage itemTooltip(final String itemJSON) {
		onHover("show_item", itemJSON);
		return this;
	}
	
	public FancyMessage itemTooltip(final ItemStack itemStack) {
		try {
			Object nmsItem = ParticalApiReflection.getMethod(obcItemStack, "asNMSCopy", ItemStack.class).invoke(null, itemStack);
			return itemTooltip(ParticalApiReflection.getMethod(nmsItemStack, "save").invoke(nmsItem, nmsTagCompound.newInstance()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
	}
	
	public FancyMessage tooltip(final String text) {
		return tooltip(text.split("\\n"));
	}
	
	public FancyMessage tooltip(final List<String> lines) {
		return tooltip((String[])lines.toArray());
	}
	
	public FancyMessage tooltip(final String... lines) {
		if (lines.length == 1) {
			onHover("show_text", lines[0]);
		} else {
			itemTooltip(makeMultilineTooltip(lines));
		}
		return this;
	}
	
	public FancyMessage then(final Object obj) {
		if (!latest().hasText()) {
			throw new IllegalStateException("previous message part has no text");
		}
		messageParts.add(new FancyApiMessagePart(obj.toString()));
		dirty = true;
		return this;
	}
	
	public FancyMessage then() {
		if (!latest().hasText()) {
			throw new IllegalStateException("previous message part has no text");
		}
		messageParts.add(new FancyApiMessagePart());
		dirty = true;
		return this;
	}
	
	public String toJSONString() {
		if (!dirty && jsonString != null) {
			return jsonString;
		}
		StringWriter string = new StringWriter();
		JsonWriter json = new JsonWriter(string);
		try {
			if (messageParts.size() == 1) {
				latest().writeJson(json);
			} else {
				json.beginObject().name("text").value("").name("extra").beginArray();
				for (final FancyApiMessagePart part : messageParts) {
					part.writeJson(json);
				}
				json.endArray().endObject();
				json.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("invalid message");
		}
		jsonString = string.toString();
		dirty = false;
		return jsonString;
	}
	
	public void send(Player player){
		try {
			Object handle = ParticalApiReflection.getHandle(player);
			Object connection = ParticalApiReflection.getField(handle.getClass(), "playerConnection").get(handle);
			Object serialized = ParticalApiReflection.getMethod(nmsChatSerializer, "a", String.class).invoke(null, toJSONString());
			Object packet = nmsPacketPlayOutChat.getConstructor(ParticalApiReflection.getNMSClass("IChatBaseComponent")).newInstance(serialized);
			ParticalApiReflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(final Iterable<Player> players) {
		for (final Player player : players) {
			send(player);
		}
	}
	
	public String toOldMessageFormat() {
		StringBuilder result = new StringBuilder();
		for (FancyApiMessagePart part : messageParts) {
			result.append(part.color).append(part.text);
		}
		return result.toString();
	}
	
	private FancyApiMessagePart latest() {
		return messageParts.get(messageParts.size() - 1);
	}
	
	private String makeMultilineTooltip(final String[] lines) {
		StringWriter string = new StringWriter();
		JsonWriter json = new JsonWriter(string);
		try {
			json.beginObject().name("id").value(1);
			json.name("tag").beginObject().name("display").beginObject();
			json.name("Name").value("\\u00A7f" + lines[0].replace("\"", "\\\""));
			json.name("Lore").beginArray();
			for (int i = 1; i < lines.length; i++) {
				final String line = lines[i];
				json.value(line.isEmpty() ? " " : line.replace("\"", "\\\""));
			}
			json.endArray().endObject().endObject().endObject();
			json.close();
		} catch (Exception e) {
			throw new RuntimeException("invalid tooltip");
		}
		return string.toString();
	}
	
	private void onClick(final String name, final String data) {
		final FancyApiMessagePart latest = latest();
		latest.clickActionName = name;
		latest.clickActionData = data;
		dirty = true;
	}
	
	private void onHover(final String name, final String data) {
		final FancyApiMessagePart latest = latest();
		latest.hoverActionName = name;
		latest.hoverActionData = data;
		dirty = true;
	}
	
}
