package org.bukkitcontrib.packet.listener;

import java.util.Arrays;
import net.minecraft.server.Packet;

/**
 * Keeps track of packet listeners. Multiple listeners can be registered for a
 * given packet ID, and multiple packet IDs can contain the same listener.
 * 
 * @see Listener
 */
public class Listeners {
	/**
	 * Private constructor to avoid initialization
	 */
	private Listeners() {
	}

	private static Listener[][] listeners = new Listener[256][0];

	/**
	 * Checks with the {@link #addListener(int, Listener) added listeners} if a
	 * packet can be sent. If any {@link Listener listener} returns false, this
	 * function will immediately return false. Otherwise, true will be returned.
	 */
	public static boolean canSend(Packet packet) {
		for (Listener listener : listeners[packet.b()]) {
			if (!listener.checkPacket(packet))
				return false;
		}
		return true;
	}

	/**
	 * Adds a {@link Listener packet listener} for the specified packet ID. This
	 * should only be called during plugin initialization to avoid concurrency
	 * issues.
	 */
	public static void addListener(int packetId, Listener listener) {
		if (packetId < 0 || packetId > 255)
			return;

		listeners[packetId] = Arrays.copyOf(listeners[packetId], listeners[packetId].length + 1);
		listeners[packetId][listeners[packetId].length - 1] = listener;
	}

	/**
	 * Removes a {@link Listener packet listener} from the specified packet ID's
	 * listener array. This should only be called during plugin shutdown to
	 * avoid concurrency issues.
	 * 
	 * @return true if the listener was removed, false if there was an error
	 *         (usually unregistered listeners or invalid arguments)
	 */
	public static boolean removeListener(int packetId, Listener listener) {
		if (packetId < 0 || packetId > 255)
			return false;

		int index = -1;
		for (int i = 0; i < listeners[packetId].length; i++) {
			if (listeners[packetId][i] == listener) {
				index = i;
				break;
			}
		}
		if (index == -1)
			return false;

		Listener[] oldListeners = listeners[packetId];
		listeners[packetId] = new Listener[oldListeners.length - 1];
		System.arraycopy(oldListeners, 0, listeners[packetId], 0, index);
		System.arraycopy(oldListeners, index + 1, listeners[packetId], index, oldListeners.length
				- 1 - index);
		return true;
	}

	/**
	 * Returns true if there are more than zero listeners registered for the
	 * specified packet ID.
	 */
	public static boolean hasListeners(int packetId) {
		if (packetId < 0 || packetId > 255)
			return false;
		return listeners[packetId].length > 0;
	}

	/**
	 * Returns true if {@link #hasListeners(int)} would return true for any
	 * number.
	 */
	public static boolean hasListeners() {
		for (Listener[] packetListeners : listeners) {
			if (packetListeners.length > 0)
				return true;
		}
		return false;
	}

	/**
	 * Returns true if the specified listener has been
	 * {@link #addListener(int, Listener) registered} for the specified packet
	 * ID.
	 */
	public static boolean hasListener(int packetId, Listener listener) {
		if (packetId < 0 || packetId > 255)
			return false;

		for (Listener packetListener : listeners[packetId]) {
			if (packetListener == listener)
				return true;
		}
		return false;
	}

	/**
	 * Unregister all listeners very quickly. This should not usually be called
	 * by plugins.
	 */
	public static void clearAllListeners() {
		listeners = new Listener[256][0];
	}
}
