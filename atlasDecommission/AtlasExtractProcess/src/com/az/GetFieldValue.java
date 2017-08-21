package com.az;

import java.lang.reflect.Field;

public class GetFieldValue {
	public static int name (String environment, String field) {
		int value = 0;
		Class<?> sqlFieldsClass = null;
		try {
			sqlFieldsClass = Class.forName(("EMEA".equals(environment)?"com.az.EMEAsqlFields":"com.az.APACsqlFields"));
			Object sqlField = sqlFieldsClass.newInstance();
			Field prj = sqlFieldsClass.getDeclaredField(field);
			Object obj = prj.get(sqlField);
			value = Integer.parseInt(obj.toString());

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}
}
