package ru.ender.aitemss.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import ru.ender.aitemss.Utils.Color;
import ru.ender.aitemss.Utils.ItemsConfig;

import java.util.Arrays;
import java.util.List;

public class ItemsGive implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            Bukkit.getLogger().info(Color.format("Вы не игрок!"));
        }

        Player player = (Player) commandSender;
        player.getInventory().addItem(ItemsConfig.getItem());
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        return args.length == 1 ? ItemsConfig.getNames() : Arrays.asList("NoItems");
    }
}
