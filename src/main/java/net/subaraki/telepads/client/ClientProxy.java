package net.subaraki.telepads.client;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.subaraki.telepads.Telepads;
import net.subaraki.telepads.blocks.BlockHandling;
import net.subaraki.telepads.blocks.RenderItemBlock;
import net.subaraki.telepads.blocks.TelePadTESR;
import net.subaraki.telepads.blocks.TelePadTileEntity;
import net.subaraki.telepads.common.CommonProxy;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void preInit () {
    
    }
    
    @Override
    public void init () {
    	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockHandling.blockPad), new RenderItemBlock());
    	ClientRegistry.bindTileEntitySpecialRenderer(TelePadTileEntity.class, new TelePadTESR());
    }
    
    @Override
    public void postInit () {
    
    }
}
