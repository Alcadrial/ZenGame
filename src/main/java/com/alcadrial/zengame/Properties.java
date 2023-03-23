package com.alcadrial.zengame;

import java.util.LinkedList;
import java.util.List;

import com.alcadrial.kroperties.Property;
import com.alcadrial.kroperties.StringProperty;

public class Properties {
	
	private Properties()
	{}
	
	public static final List<Property<?>> PROPERTIES = new LinkedList<>();
	
	public static final Property<String> ASSETS_PATH = register(new StringProperty("assets", "src/main/resources"));
	public static final Property<String> SCRIPTS_PATH = register(new StringProperty("scripts", "src/main/scripts"));
	
	private static <P extends Property<?>> P register(P property)
	{
		PROPERTIES.add(property);
		return property;
	}
}
