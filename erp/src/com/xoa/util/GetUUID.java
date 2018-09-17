package com.xoa.util;

import java.util.UUID;

public class GetUUID {
public static String getUuid(){
	UUID  uuidString=UUID.randomUUID();
	return uuidString.toString().replace("-", "");
}
}
