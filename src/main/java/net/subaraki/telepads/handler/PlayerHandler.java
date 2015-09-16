package net.subaraki.telepads.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.darkhax.bookshelf.util.Utilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.subaraki.telepads.blocks.BlockTelepad;
import net.subaraki.telepads.tileentity.TileEntityTelepad;

public class PlayerHandler {

	@SubscribeEvent
	public void onPlayerClone (PlayerEvent.Clone event) {

		PlayerLocations.getProperties(event.entityPlayer).copy(PlayerLocations.getProperties(event.original));
	}

	@SubscribeEvent
	public void onEntityConstructing (EntityConstructing event) {

		if (event.entity instanceof EntityPlayer && !PlayerLocations.hasProperties((EntityPlayer) event.entity))
			PlayerLocations.setProperties((EntityPlayer) event.entity);
	}

	@SubscribeEvent
	public void onEntityJoinWorld (EntityJoinWorldEvent event) {

		if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote && PlayerLocations.hasProperties((EntityPlayer) event.entity))
			PlayerLocations.getProperties((EntityPlayer) event.entity).sync();
	}

	@SubscribeEvent

	public void onPlayerInteractiong (PlayerInteractEvent event) {

		if (event.action.equals(Action.RIGHT_CLICK_BLOCK) && event.world.getBlock(event.x, event.y, event.z) instanceof BlockTelepad) {

			TileEntityTelepad telepad = (TileEntityTelepad) event.world.getTileEntity(event.x, event.y, event.z);
			int itemColor = Utilities.getItemColor(event.entityPlayer.getHeldItem());

			if (itemColor != -1337) {

				if (event.entityPlayer.isSneaking())
					telepad.colorBase = itemColor;

				else
					telepad.colorFrame = itemColor;

				telepad.markDirty();
				if(!event.entityPlayer.capabilities.isCreativeMode)
					event.entityPlayer.getHeldItem().stackSize--;
				event.entityPlayer.swingItem();
			}
		}
	}
}