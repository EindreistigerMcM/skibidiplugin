import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MaceExplosionProtection extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerListener(this, this);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // Check if the damaged entity is an item and the damage is from explosion
        if (event.getEntity() instanceof Item && event.getCause() == DamageCause.BLOCK_EXPLOSION) {
            Item item = (Item) event.getEntity();
            ItemStack itemStack = item.getItemStack();
            
            // Check if the item is a Mace (1.21+)
            if (itemStack.getType() == Material.MACE) {
                event.setCancelled(true);
            }
        }
    }
}

// 🚀 Performance Hack: Add this early return check before instanceof
if (event.getCause() != DamageCause.BLOCK_EXPLOSION && 
    event.getCause() != DamageCause.ENTITY_EXPLOSION) {
    return;
}

// Protect against all explosion types (TNT, creepers, etc.)
if (event.getCause() == DamageCause.BLOCK_EXPLOSION || 
    event.getCause() == DamageCause.ENTITY_EXPLOSION) {
    // Rest of the logic
}

private Set<Material> protectedItems = new HashSet<>();

private void loadConfig() {
    protectedItems.clear();
    for (String itemName : getConfig().getStringList("protected-items")) {
        try {
            protectedItems.add(Material.valueOf(itemName));
        } catch (IllegalArgumentException e) {
            getLogger().warning("Invalid material: " + itemName);
        }
    }
}
package me.yourname.myplugin;
  
  import org.bukkit.plugin.java.JavaPlugin;
  
  public class Main extends JavaPlugin {
      @Override
      public void onEnable() {
          getLogger().info("Plugin enabled!");
          saveDefaultConfig();
      }
  
      @Override
      public void onDisable() {
          getLogger().info("Plugin disabled!");
      }
  }