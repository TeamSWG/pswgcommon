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
package com.projectswg.common.network.packets.swg.login;

import com.projectswg.common.network.NetBuffer;
import com.projectswg.common.network.packets.SWGPacket;


public class LoginClientToken extends SWGPacket {
	public static final int CRC = getCrc("LoginClientToken");

	private byte [] sessionKey;
	private int userId;
	private String username;
	
	public LoginClientToken() {
		
	}
	
	public LoginClientToken(NetBuffer data) {
		decode(data);
	}
	
	public LoginClientToken(byte [] sessionKey, int userId, String username) {
		this.sessionKey = sessionKey;
		this.userId = userId;
		this.username = username;
	}
	
	@Override
	public void decode(NetBuffer data) {
		if (!super.checkDecode(data, CRC))
			return;
		sessionKey = data.getArrayLarge();
		userId = data.getInt();
		username = data.getAscii();
	}
	
	@Override
	public NetBuffer encode() {
		int length = 16 + sessionKey.length + username.length();
		NetBuffer data = NetBuffer.allocate(length);
		data.addShort(4);
		data.addInt(CRC);
		data.addArrayLarge(sessionKey);
		data.addInt(userId);
		data.addAscii(username);
		return data;
	}
	
	public byte [] getSessionKey() {
		return sessionKey;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getUsername() {
		return username;
	}
	
}
