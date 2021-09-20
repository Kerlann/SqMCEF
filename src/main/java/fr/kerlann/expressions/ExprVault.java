package fr.kerlann.expressions;

import fr.kerlann.SqVault;
import fr.nico.sqript.expressions.ScriptExpression;
import fr.nico.sqript.structures.ScriptContext;
import fr.nico.sqript.types.ScriptType;
import fr.nico.sqript.types.primitive.TypeNumber;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.Bukkit;

public class ExprVault extends ScriptExpression {

    @Override
    public ScriptType get(ScriptContext context, ScriptType[] parameters) {
        switch(getMatchedName()) {
            case "Player Money":
                EntityPlayer player = (EntityPlayer) parameters[0].getObject();
                return new TypeNumber(SqVault.getEconomy().getBalance(Bukkit.getOfflinePlayer(player.getUniqueID())));
        }
        return null;
    }

    @Override
    public boolean set(ScriptContext context, ScriptType to, ScriptType[] parameters) {
        return false;
    }
}
