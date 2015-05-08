package com.example.property;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class ChannelListManager {
	private static ChannelListManager objManager = null;
	private ArrayList<ChannnelBasicProperty> channelList = null;
	private ChannelListManager(){
		channelList = new ArrayList<ChannnelBasicProperty>();
	}
	public static ChannelListManager getObjManager() {
		if (objManager == null) {
			objManager = new ChannelListManager();
		}
		return objManager;
	}
	
	public ArrayList<ChannnelBasicProperty> getChannelList() {
		
		return channelList;
	}
	
	public void setChannelList(List<Map<String, Object>> list) {
		
		for (Map<String , Object > map:list) {
			Iterator<Entry<String, Object>> it = map.entrySet().iterator();
			
			Entry<String, Object> entry = it.next();
			ChannnelBasicProperty roperty = new ChannnelBasicProperty();
			roperty.setChannel(entry.getValue().toString());
			if (it.hasNext()) {
				Entry<String, Object> entryid = it.next();
				roperty.setId(entryid.getValue().toString());
			}
			channelList.add(roperty);
		}	
	}
	public ArrayList<String> getChannleNameList() {
		ArrayList<String> list = new ArrayList<String>();
		for (ChannnelBasicProperty property:channelList) {
			list.add(property.getChannel());
		}
		return list;
	}
	public ArrayList<String> getChannleIdList() {
		ArrayList<String> list = new ArrayList<String>();
		for (ChannnelBasicProperty property:channelList) {
			list.add(property.getId());
		}
		return list;
	}
	public void clear() {
		if (channelList!=null) {
			if (channelList.size() > 0) {
				channelList.clear();
			}
		}
	}

}
