package me.technopvp.common.utilities.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import me.technopvp.common.dCommon;
import me.technopvp.common.commands.CommonCommand;
import me.technopvp.common.managers.MessageManager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Retention(RetentionPolicy.RUNTIME)
public @interface Permissions {

    Permission value() default Permission.ANYONE;

    public static enum Permission {

        // Permission levels
        ANYONE(""),
        HELPER("dcommon.helper"),
        MOD("dcommon.mod"),
        ADMIN("dcommon.admin"),
        OWNER("dcommon.owner");

        private String permission;

        public String getPermission() {
            return permission;
        }

        private Permission(String permission) {
            this.permission = permission;
        }
    }

    public static class PermissionUtils {

        public static boolean hasPermission(CommandSender sender, Permission permissionLevel) {
            if (!(sender instanceof Player) || permissionLevel == Permission.ANYONE) {
                return true;
            }

            return sender.hasPermission(permissionLevel.getPermission());
        }

        public static boolean hasPermission(CommandSender sender, Class<? extends CommonCommand> commandClass, dCommon plugin) {
            Permissions permissions;
            try {
                permissions = commandClass.getAnnotation(Permissions.class);
            } catch (NullPointerException e) {
                MessageManager.log(Level.HIGH, "Command " + commandClass.getName() + " doesn't have permissions set!");
                return true;
            }

            return hasPermission(sender, permissions.value());
        }
    }
}