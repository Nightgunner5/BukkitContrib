package org.bukkitcontrib.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.Packet;

public abstract class PacketUtil {
	public static final int maxString = 32767;
	
	public static void writeString(DataOutputStream output, String string) {
		try {
			Packet.a(string, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readString(DataInputStream input) {
		return readString(input, maxString);
	}
	
	public static int getNumBytes(String str) {
		return 2 + str.length() * 2;
	}
	
	public static String readString(DataInputStream input, int maxSize) {
		try {
			return Packet.a(input, maxSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
