package ru.ender.aitemss.Utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {

    public static String format(String message) {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        int subVersion = Integer.parseInt(version.replace("1_", "").replaceAll("_R\\d", "").replace("v", ""));

        if(subVersion >= 16) {
            Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");
            Matcher matcher = pattern.matcher(message);

            while (matcher.find()) {
                String color = message.substring(matcher.start() + 1, matcher.end());
                message = message.replace("&" + color, ChatColor.of(color) + "");
                matcher = pattern.matcher(message);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> format(List<String> messages) {
        List<String> formattedMessages = new ArrayList<>();
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        int subVersion = Integer.parseInt(version.replace("1_", "").replaceAll("_R\\d", "").replace("v", ""));

        for (String message : messages) {
            if (subVersion >= 16) {
                Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");
                Matcher matcher = pattern.matcher(message);

                while (matcher.find()) {
                    String color = message.substring(matcher.start() + 1, matcher.end());
                    message = message.replace("&" + color, ChatColor.of(color) + "");
                    matcher = pattern.matcher(message);
                }
            }
            formattedMessages.add(ChatColor.translateAlternateColorCodes('&', message));
        }

        return formattedMessages;
    }
}
