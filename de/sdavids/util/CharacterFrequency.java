package de.sdavids.util;

public class CharacterFrequency {
	private char fChar;
	private int  fFrequency;

	public CharacterFrequency(char c)	{
		this(c, 0);
	}
	
	public CharacterFrequency(char c, int frequency)	{
		fChar = c;
		fFrequency = frequency;
	}
	
	public void incFrequency() {
		fFrequency++;
	}

	public char getChar() {
		return fChar;
	}
		
	public int getFrequency() {
		return fFrequency;
	}
	
	public String toString() {
		return getChar() + "-" + getFrequency();
	}
}

