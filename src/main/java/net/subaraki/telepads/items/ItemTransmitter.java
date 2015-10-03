package net.subaraki.telepads.items;

import net.darkhax.bookshelf.util.Utilities;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
