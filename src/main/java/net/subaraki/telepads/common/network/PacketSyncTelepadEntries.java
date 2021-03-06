package net.subaraki.telepads.common.network;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.darkhax.bookshelf.lib.util.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.subaraki.telepads.handler.PlayerLocations;
import net.subaraki.telepads.handler.PlayerLocations.TelepadEntry;

public class PacketSyncTelepadEntries implements IMessage {
    
    /**
     * The unique identifier of the player sync this data to.
     */
    private UUID playerUUID;
    
    /**
     * The list of entries to sync to the player.
     */
    private List<TelepadEntry> entries;
    
    /**
     * A packet to sync entries to a player, on the client side. This packet must only be sent
     * from a server thread. The entries on the client side will be overridden with the
     * provided list of entries.
     * 
     * @param playerUUID : The unique identifier of the player sync this data to.
     * @param entries : The list of entries to sync to the player.
     */
    public PacketSyncTelepadEntries(UUID playerUUID, List<TelepadEntry> entries) {
        
        this.playerUUID = playerUUID;
        this.entries = entries;
    }
    
    @Override
    public void fromBytes (ByteBuf buf) {
        
        List<TelepadEntry> entryList = new ArrayList<TelepadEntry>();
        this.playerUUID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        int size = buf.readInt();
        
        if (!(size > 0))
            return;
            
        for (int index = 0; index < size; index++)
            entryList.add(new TelepadEntry(buf));
            
        this.entries = entryList;
    }
    
    @Override
    public void toBytes (ByteBuf buf) {
        
        ByteBufUtils.writeUTF8String(buf, playerUUID.toString());
        buf.writeInt(this.entries.size());
        
        for (TelepadEntry entry : this.entries)
            entry.writeToByteBuf(buf);
    }
    
    public PacketSyncTelepadEntries() {
    
    }
    
    public static class PacketSyncTelepadEntriesHandler implements IMessageHandler<PacketSyncTelepadEntries, IMessage> {
        
        @Override
        public IMessage onMessage (PacketSyncTelepadEntries packet, MessageContext ctx) {
            
            EntityPlayer player = PlayerUtils.getPlayerFromUUID(PlayerUtils.getClientPlayer().worldObj, packet.playerUUID);
            PlayerLocations.getProperties(player).overrideEntries(packet.entries);
            return null;
        }
    }
}