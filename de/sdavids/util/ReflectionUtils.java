/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */
package de.sdavids.util;

import java.lang.reflect.InvocationTargetException;

/**
 * @author	Sebastian Davids
 * @version	1.00	02--
 */
public class ReflectionUtils {
	public static Object newInstance(String className, Object arg1)
		throws
			ClassNotFoundException,
			ClassCastException,
			IllegalAccessException,
			InvocationTargetException,
			InstantiationException,
			NoSuchMethodException {

		Class[] argTypes = { arg1.getClass()};

		Object[] args = { arg1 };

		return newInstance(className, argTypes, args);
	}

	public static Object newInstance(String className, Object arg1, Object arg2)
		throws
			ClassNotFoundException,
			ClassCastException,
			IllegalAccessException,
			InvocationTargetException,
			InstantiationException,
			NoSuchMethodException {

		Class[] argTypes = { arg1.getClass(), arg2.getClass()};

		Object[] args = { arg1, arg2 };
//System.out.println("@i " +(arg2 instanceof IApplication));
//System.out.println("@t " +StringUtils.toString(argTypes));
//System.out.println("@a " +StringUtils.toString(args));
//System.out.println("@n " +className);
//System.out.println("@ " +StringUtils.toString(Class.forName(className).getConstructors()));
		return newInstance(className, argTypes, args);
	}

	public static Object newInstance(
		String className,
		Object arg1,
		Object arg2,
		Object arg3)
		throws
			ClassNotFoundException,
			ClassCastException,
			IllegalAccessException,
			InvocationTargetException,
			InstantiationException,
			NoSuchMethodException {

		Class[] argTypes = { arg1.getClass(), arg2.getClass(), arg3.getClass()};

		Object[] args = { arg1, arg2, arg3 };

		return newInstance(className, argTypes, args);
	}

	public static Object newInstance(
		String className,
		Object arg1,
		Object arg2,
		Object arg3,
		Object arg4)
		throws
			ClassNotFoundException,
			ClassCastException,
			IllegalAccessException,
			InvocationTargetException,
			InstantiationException,
			NoSuchMethodException {

		Class[] argTypes =
			{ arg1.getClass(), arg2.getClass(), arg3.getClass(), arg4.getClass()};

		Object[] args = { arg1, arg2, arg3, arg4 };

		return newInstance(className, argTypes, args);
	}

	public static Object newInstance(
		String className,
		Object arg1,
		Object arg2,
		Object arg3,
		Object arg4,
		Object arg5)
		throws
			ClassNotFoundException,
			ClassCastException,
			IllegalAccessException,
			InvocationTargetException,
			InstantiationException,
			NoSuchMethodException {

		Class[] argTypes =
			{
				arg1.getClass(),
				arg2.getClass(),
				arg3.getClass(),
				arg4.getClass(),
				arg5.getClass()};

		Object[] args = { arg1, arg2, arg3, arg4, arg5 };

		return newInstance(className, argTypes, args);
	}

	protected static Object newInstance(
		String className,
		Class[] argTypes,
		Object[] args)
		throws
			ClassNotFoundException,
			ClassCastException,
			IllegalAccessException,
			InvocationTargetException,
			InstantiationException,
			NoSuchMethodException {

		return Class.forName(className).getConstructor(argTypes).newInstance(args);
	}
}