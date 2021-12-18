package psseraw.elements;

import java.util.ArrayList;

import psseraw.elements.AuxMethod;

public class AuxPsseRaw {

	
	public static int determineBusIndex(BusList buses, String token)
	{
		int busIdxTmp = (token.startsWith("'")) ? buses.getBusIndex(token) : buses.getIdx(Integer.parseInt(token));
		return busIdxTmp;
	}
	
	public static int determineBusIndex(BusList buses, String[] tokens, int idx)
	{
		String token = tokens[idx];
		return determineBusIndex(buses, token);
	}
	
	public static void readOneParam(ArrayList<Integer> array, String[] tokens, int idx, int defaultValue)
	{
		if (AuxMethod.isExceedStrLength(idx, tokens.length) || AuxMethod.isNoInfo(tokens[idx]))
			{array.add(defaultValue); idx++;}
		else array.add(Integer.parseInt(tokens[idx++]));
	}
	
	public static void readOneParam(ArrayList<Double> array, String[] tokens, int idx, double defaultValue)
	{
		if (AuxMethod.isExceedStrLength(idx, tokens.length) || AuxMethod.isNoInfo(tokens[idx]))
			{array.add(defaultValue); idx++;}
		else array.add(Double.parseDouble(tokens[idx++]));
	}
	
	public static void readOneParam(ArrayList<String> array, String[] tokens, int idx, String defaultValue)
	{
		if (AuxMethod.isExceedStrLength(idx, tokens.length) || AuxMethod.isNoInfo(tokens[idx]))
			{array.add(defaultValue); idx++;}
		else array.add(tokens[idx++]);
	}
	

}
