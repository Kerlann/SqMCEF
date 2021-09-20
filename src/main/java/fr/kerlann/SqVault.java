package fr.kerlann;

import fr.nico.sqript.ScriptManager;
import net.milkbowl.vault.economy.Economy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/*
    Code Source de : https://github.com/abvadabra/forge-vault
 */
@Mod(modid = "sqvault", name = "SqVault", dependencies = "required-after:sqript@${version}", version = "1.0.0", acceptableRemoteVersions = "*")
public class SqVault {

    @SideOnly(Side.SERVER)
    private static Economy economy;

    @SideOnly(Side.SERVER)
    public static Economy getEconomy(){
        return economy;
    }

    @SideOnly(Side.SERVER)
    private static void setupEconomy() throws ClassNotFoundException{
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null){
            ScriptManager.log.info("Cannot find Vault!");
            return;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if(rsp == null){
            ScriptManager.log.info("Registered Service Provider for Economy.class not found");
            return;
        }
        economy = rsp.getProvider();
        ScriptManager.log.info("Economy successfully hooked up");
    }


    @SideOnly(Side.SERVER)
    @Mod.EventHandler
    public static void serverLoad(FMLServerStartingEvent event){
        try {
            setupEconomy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
