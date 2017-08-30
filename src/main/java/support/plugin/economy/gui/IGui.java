package support.plugin.economy.gui;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by eric on 30/08/2017.
 */
public interface IGui {

    String getTitle();

    HashMap<Integer, ItemStack> getContents();

}
