/***********************************************************************************
 * Copyright (c) 2018 /// Project SWG /// www.projectswg.com                       *
 *                                                                                 *
 * ProjectSWG is the first NGE emulator for Star Wars Galaxies founded on          *
 * July 7th, 2011 after SOE announced the official shutdown of Star Wars Galaxies. *
 * Our goal is to create an emulator which will provide a server for players to    *
 * continue playing a game similar to the one they used to play. We are basing     *
 * it on the final publish of the game prior to end-game events.                   *
 *                                                                                 *
 * This file is part of PSWGCommon.                                                *
 *                                                                                 *
 * --------------------------------------------------------------------------------*
 *                                                                                 *
 * PSWGCommon is free software: you can redistribute it and/or modify              *
 * it under the terms of the GNU Affero General Public License as                  *
 * published by the Free Software Foundation, either version 3 of the              *
 * License, or (at your option) any later version.                                 *
 *                                                                                 *
 * PSWGCommon is distributed in the hope that it will be useful,                   *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                  *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                   *
 * GNU Affero General Public License for more details.                             *
 *                                                                                 *
 * You should have received a copy of the GNU Affero General Public License        *
 * along with PSWGCommon.  If not, see <http://www.gnu.org/licenses/>.             *
 ***********************************************************************************/
package com.projectswg.common.network.packets.swg.zone.chat;

import com.projectswg.common.data.encodables.chat.ChatAvatar;
import com.projectswg.common.network.NetBuffer;
import com.projectswg.common.network.packets.SWGPacket;

public class ChatOnLeaveRoom extends SWGPacket {
	public static final int CRC = getCrc("ChatOnLeaveRoom");
	
	private ChatAvatar avatar;
	private int result;
	private int chatRoomId;
	private int sequence;
	
	public ChatOnLeaveRoom() {
		
	}
	
	public ChatOnLeaveRoom(ChatAvatar avatar, int result, int chatRoomId, int sequence) {
		this.avatar = avatar;
		this.result = result;
		this.chatRoomId = chatRoomId;
		this.sequence = sequence;
	}
	
	public ChatOnLeaveRoom(NetBuffer data) {
		decode(data);
	}
	
	public void decode(NetBuffer data) {
		if (!super.checkDecode(data, CRC))
			return;
		avatar 		= data.getEncodable(ChatAvatar.class);
		result 		= data.getInt();
		chatRoomId 	= data.getInt();
		sequence 	= data.getInt();
	}
	
	public NetBuffer encode() {
		NetBuffer data = NetBuffer.allocate(18 + avatar.getLength());
		data.addShort(5);
		data.addInt(CRC);
		data.addEncodable(avatar);
		data.addInt(result);
		data.addInt(chatRoomId);
		data.addInt(sequence);
		return data;
	}
	
}
