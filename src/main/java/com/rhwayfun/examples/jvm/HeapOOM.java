package com.rhwayfun.examples.jvm;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {

	/*
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -
XX:SurvivorRatio=8
	 * */
	
	static class OOMObject{
		
	}
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		
		while(true){
			list.add(new OOMObject());
		}
	}
}
