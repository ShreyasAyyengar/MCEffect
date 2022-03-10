package me.shreyasayyengar.mceffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public final class MCEffect extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("MCEffect has been enabled!");

        startTask();
    }

    private void startTask() {
        new BukkitRunnable() {
            @Override
            public void run() {

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    for (PotionEffect activePotionEffect : onlinePlayer.getActivePotionEffects()) {
                        onlinePlayer.removePotionEffect(activePotionEffect.getType());
                    }
                    
                    PotionEffectType[] potionEffectTypes = PotionEffectType.values();
                    int randomPotionEffect = (int) (Math.random() * potionEffectTypes.length);
                    onlinePlayer.addPotionEffect(new PotionEffect(potionEffectTypes[randomPotionEffect], 1000000, 1, false, false, false));
                    onlinePlayer.sendMessage(ChatColor.GREEN + "You have been given a random potion effect!");
                }
            }
        }.runTaskTimer(this, 0, 1200);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
