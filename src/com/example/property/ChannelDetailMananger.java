package com.example.property;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChannelDetailMananger {
	private static ChannelDetailMananger objManager = null;
	private ArrayList<ChannelDetialProperty> channelList = null;
	private ChannelDetailMananger(){
		channelList = new ArrayList<ChannelDetialProperty>();
	}
	public static ChannelDetailMananger getObjManager() {
		if (objManager == null) {
			objManager = new ChannelDetailMananger();
		}
		return objManager;
	}
	
	public ArrayList<ChannelDetialProperty> getChannelList() {
		
		return channelList;
	}
	
	public void setChannelList(List<Map<String, Object>> list) {
		
		for (Map<String , Object > map:list) {
			Iterator<Entry<String, Object>> it = map.entrySet().iterator();
			
			Entry<String, Object> entry ;
			ChannelDetialProperty roperty = new ChannelDetialProperty();//roperty.setChannel(entry.getValue().toString());
			do {
				entry = it.next();
				String value = entry.getValue().toString();
				switch (entry.getKey()) {
				case "id":
					roperty.setId(value);
					break;
				case "tvProgram":
					roperty.setTvProgram(value);
					break;
				case "tvTime":
					roperty.setTvTime(value);
					break;
				case "weekDay":
					roperty.setWeekDay(value);
					break;
				case "description":
					roperty.setDescription(value);
					break;
				case "tvType":
					roperty.setTvType(value);
					break;
				default:
					break;
				}
			} while (it.hasNext());
			
			channelList.add(roperty);
		}	
	}

	public void clear() {
		if (channelList!=null) {
			if (channelList.size() > 0) {
				channelList.clear();
			}
		}
	}

}
