package de.sdavids.util;

import java.awt.Dimension;

public class DimensionUtils {

	public static Dimension combine(
		Dimension aDimension,
		Dimension anotherDimension) {

		if (null == aDimension)
			throw new IllegalArgumentException("aDimension null");
		if (null == anotherDimension)
			throw new IllegalArgumentException("anotherDimension null");
			
		if (anotherDimension == aDimension)
			return anotherDimension;
		if (anotherDimension.equals(aDimension))
			return aDimension;

		int aWidth = aDimension.width;

		if (0 > aWidth)
			throw new IllegalArgumentException("aDimension.width " + aWidth);

		int anotherWidth = anotherDimension.width;

		if (0 > anotherWidth)
			throw new IllegalArgumentException("anotherDimension.width " + anotherWidth);

		int aHeight = aDimension.height;

		if (0 > aHeight)
			throw new IllegalArgumentException("aDimension.height " + aHeight);

		int anotherHeight = anotherDimension.height;

		if (0 > anotherHeight)
			throw new IllegalArgumentException(
				"anotherDimension.height " + anotherHeight);

		boolean anotherGreaterWidth = (anotherWidth > aWidth);
		boolean anotherGreaterHeight = (anotherHeight > aHeight);

		if (anotherGreaterWidth && anotherGreaterHeight)
			return anotherDimension;

		if (!(anotherGreaterWidth || anotherGreaterHeight))
			return aDimension;

		return new Dimension(
			((anotherGreaterWidth) ? anotherWidth : aWidth),
			((anotherGreaterHeight) ? anotherHeight : aHeight));
	}
}