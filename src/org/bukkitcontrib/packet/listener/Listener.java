package org.bukkitcontrib.packet.listener;

import net.minecraft.server.Packet;

/**
 * A packet listener
 */
public interface Listener {
	/**
	 * @param packet The packet to check
	 * @return false if the packet should be stopped, true otherwise.
	 */
	public boolean checkPacket(Packet packet);
}
