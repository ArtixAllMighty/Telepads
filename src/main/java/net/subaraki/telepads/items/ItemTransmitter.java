package net.subaraki.telepads.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTransmitter extends Item {
    
    public ItemTransmitter() {
        this.setUnlocalizedName("telepad.transmitter");
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister iconRegister) {
        
        this.itemIcon = iconRegister.registerIcon("telepads:transmitter");
    }
}