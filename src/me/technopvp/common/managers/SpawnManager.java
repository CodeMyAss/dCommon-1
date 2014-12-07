package me.technopvp.common.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public abstract class SpawnManager extends BukkitRunnable {

	private int repeatableTaskID = 0;
	private long repeats = 0;
	private long maxRepeats = 0;

	/**
	 * Create a new Repeatable Runnable that runs until the maximum amount of repeats. This will automatically schedule a repeating task.
	 *
	 * @param scheduler - The Scheduler of the server.
	 * @param yourPlugin - Your plugin object.
	 * @param delay - Delay in server ticks before executing first repeat.
	 * @param period - Period in server ticks of the task.
	 * @param amountOfTimes - The amount of times to call the onRun method.
	 */
	public SpawnManager(BukkitScheduler scheduler, Plugin plugin, long delay, long period, long amountOfTimes) {
		this.repeatableTaskID = scheduler.scheduleSyncRepeatingTask(plugin, this, delay, period);
		this.maxRepeats = amountOfTimes > 0 ? amountOfTimes : 1;
	}

	@Override
	public void run() {
		if (this.repeats < this.maxRepeats) {
			this.repeats++;
			this.onRun();
		} else {
			try {
				if (Bukkit.getScheduler().isCurrentlyRunning(this.repeatableTaskID) || Bukkit.getScheduler().isQueued(this.repeatableTaskID)) Bukkit.getScheduler().cancelTask(this.repeatableTaskID);
			} catch (Exception ex) {
				try {
					this.cancel();
				} catch (Exception ex2) {
				}
			}
		}
	}

	public abstract void onRun();

	public long getRepeats() {
		return this.repeats;
	}

	public long getMaxRepeats() {
		return this.maxRepeats;
	}

	public void setTaskID(int taskID) {
		this.repeatableTaskID = taskID;
	}

}