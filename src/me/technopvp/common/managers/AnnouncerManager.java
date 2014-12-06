package me.technopvp.common.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.technopvp.common.dCommon;

import org.bukkit.scheduler.BukkitRunnable;

public class AnnouncerManager {
	static dCommon plugin = dCommon.instance;

	    private static final List<String> ANNOUNCEMENTS = new ArrayList<String>();
	    private static boolean enabled;
	    private static long interval;
	    private static String prefix;
	    private static BukkitRunnable announcer;

	    private AnnouncerManager() {
	        throw new AssertionError();
	    }

	    public static boolean isEnabled() {
	        return enabled;
	    }

	    public static List<String> getAnnouncements() {
	        return Collections.unmodifiableList(ANNOUNCEMENTS);
	    }

	    public static long getTickInterval() {
	        return interval;
	    }

	    public static String getPrefix() {
	        return prefix;
	    }

	    public static void startAnnouncements() {
	        stop();

	        ANNOUNCEMENTS.clear();

	        for (Object announcement : plugin.getConfig().getList("announcements")) {
	            ANNOUNCEMENTS.add((String) announcement);
	        }

	        enabled = plugin.getConfig().getBoolean("enabled");
	        interval = plugin.getConfig().getInt("interval") * 20L;
	        prefix = plugin.getConfig().getString("prefix");

	        if (enabled) {
	            start();
	        }
	    }

	    public static boolean isStarted() {
	        return announcer != null;
	    }

	    public static void start() {
	        if (isStarted()) {
	            return;
	        }

	        announcer = new BukkitRunnable() {
	            private int current = 0;

	            @Override
	            public void run() {
	                current++;

	                if (current >= ANNOUNCEMENTS.size()) {
	                    current = 0;
	                }

	                MessageManager.broadcast(null, prefix + ANNOUNCEMENTS.get(current));
	            }
	        };

	        announcer.runTaskTimer(plugin, interval, interval);
	    }

	    public static void stop() {
	        if (announcer == null) {
	            return;
	        }

	        try {
	            announcer.cancel();
	        }
	        finally {
	            announcer = null;
	        }
	    }
	}