package me.nbtc.armorStandPacket.v1_20.wrapper.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import me.nbtc.armorStandPacket.v1_20.AbstractPacket;


public class WrapperPlayServerEntityDestroy extends AbstractPacket {
	public static final PacketType TYPE = PacketType.Play.Server.ENTITY_DESTROY;

	public WrapperPlayServerEntityDestroy() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayServerEntityDestroy(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve Count.
	 * <p>
	 * Notes: length of following array
	 *
	 * @return The current Count
	 */
	public int getCount() {
		return handle.getIntegerArrays().read(0).length;
	}

	/**
	 * Retrieve Entity IDs.
	 * <p>
	 * Notes: the list of entities of destroy
	 *
	 * @return The current Entity IDs
	 */
	public int[] getEntityIDs() {
		return handle.getIntegerArrays().read(0);
	}

	/**
	 * Set Entity IDs.
	 *
	 * @param value - new value.
	 */
	public void setEntityIds(int[] value) {
		handle.getModifier().write(0, new IntArrayList(value));
	}
}