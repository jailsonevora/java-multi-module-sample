package com.ine.common.util;

import com.ine.common.exception.ResourceNotFoundException;

import java.lang.reflect.Field;

public class common {

	// cast class A to B with reflection
	public static void copyObject(Object src, Object dest) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		for (Field field : src.getClass().getFields()) {
			dest.getClass().getField(field.getName()).set(dest, field.get(src));
		}
	}
}
