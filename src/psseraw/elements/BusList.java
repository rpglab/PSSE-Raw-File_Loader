package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Bus entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class BusList {

	PsseRawModel _model;
	int _size;
	
	/** Match bus number (_i) with re-index numbers (0 .. _size).
	Key is bus number, value is bus index. */
	HashMap<Integer, Integer> _numToIdx;
	String[] _extName;

	// base value from raw file
	int[] _i;
	String[] _name;
	double _basekv[];
	int _ide[];
	int _area[];
	int _zone[];
	int _owner[];
	double _vm[];    // in per unit
	double _va[];    // in degree !!!
	double[] _nvhi, _nvlo, _evhi, _evlo;

	// default value
	final int _nParam = 13; 
	final int _defaultIDE = 1, _defaultAREA = 1, 
			_defaultZONE = 1, _defaultOWNER = 1;
	final String _defaultNAME = "            ";
	final double _defaultBASKV = 0.0, _defaultVM = 1.0, _defaultVA = 0.0;
	final double _defaultNVHI = 1.1, _defaultNVLO = 0.9, _defaultEVHI = 1.1, _defaultEVLO = 0.9;
	
	public BusList(PsseRawModel model) {_model = model;}
	
	public void readData(BufferedReader reader) throws IOException
	{
		HashMap<Integer, Integer> numToIdx = new HashMap<Integer, Integer>();
		ArrayList<Integer> i = new ArrayList<Integer>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<Double> basekv = new ArrayList<Double>();
		ArrayList<Integer> ide = new ArrayList<Integer>();
		ArrayList<Integer> area = new ArrayList<Integer>();
		ArrayList<Integer> zone = new ArrayList<Integer>();
		ArrayList<Integer> owner = new ArrayList<Integer>();
		ArrayList<Double> va = new ArrayList<Double>();
		ArrayList<Double> vm = new ArrayList<Double>();
		ArrayList<Double> nvhi = new ArrayList<Double>();
		ArrayList<Double> nvlo = new ArrayList<Double>();
		ArrayList<Double> evhi = new ArrayList<Double>();
		ArrayList<Double> evlo = new ArrayList<Double>();
		String line;
		_size = 0;
		System.out.println("Start reading bus data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			
			int busNum = Integer.parseInt(tokens[idx++]);
			i.add(busNum);
			numToIdx.put(busNum, _size++);
			
			AuxPsseRaw.readOneParam(name, tokens, idx++, _defaultNAME);
			AuxPsseRaw.readOneParam(basekv, tokens, idx++, _defaultBASKV);
			AuxPsseRaw.readOneParam(ide, tokens, idx++, _defaultIDE);
			AuxPsseRaw.readOneParam(area, tokens, idx++, _defaultAREA);
			AuxPsseRaw.readOneParam(zone, tokens, idx++, _defaultZONE);
			AuxPsseRaw.readOneParam(owner, tokens, idx++, _defaultOWNER);
			AuxPsseRaw.readOneParam(va, tokens, idx++, _defaultVA);
			AuxPsseRaw.readOneParam(vm, tokens, idx++, _defaultVM);
			AuxPsseRaw.readOneParam(nvhi, tokens, idx++, _defaultNVHI);
			AuxPsseRaw.readOneParam(nvlo, tokens, idx++, _defaultNVLO);
			AuxPsseRaw.readOneParam(evhi, tokens, idx++, _defaultEVHI);
			AuxPsseRaw.readOneParam(evlo, tokens, idx++, _defaultEVLO);
			
			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}
		
		// save data in member variables of this class - field
		_numToIdx = numToIdx;
		_i = AuxMethod.convtArrayListToInt(i);
		_name = AuxMethod.convtArrayListToStr(name);
		_basekv = AuxMethod.convtArrayListToDouble(basekv);
		_ide = AuxMethod.convtArrayListToInt(ide);
		_area = AuxMethod.convtArrayListToInt(area);
		_zone = AuxMethod.convtArrayListToInt(zone);
		_owner = AuxMethod.convtArrayListToInt(owner);
		_vm = AuxMethod.convtArrayListToDouble(vm);
		_va = AuxMethod.convtArrayListToDouble(va);
		_nvhi = AuxMethod.convtArrayListToDouble(nvhi);
		_nvlo = AuxMethod.convtArrayListToDouble(nvlo);
		_evhi = AuxMethod.convtArrayListToDouble(evhi);
		_evlo = AuxMethod.convtArrayListToDouble(evlo);

		// remove single or double quotes for _names if any
		AuxMethod.removeBoundaryDoubleQuotes(_name);
		AuxMethod.removeBoundarySingleQuotes(_name);
		
		checkEntryData();
		formExtentedBusName();
		System.out.println("   Finish reading bus data");
	}

	// data check
	private void checkEntryData()
	{
		if(_size != _i.length) System.exit(1);
		if(_size != _name.length) System.exit(1);
		if(_size != _basekv.length) System.exit(1);
		if(_size != _ide.length) System.exit(1);
		if(_size != _area.length) System.exit(1);
		if(_size != _zone.length) System.exit(1);
		if(_size != _owner.length) System.exit(1);
		if(_size != _vm.length) System.exit(1);
		if(_size != _va.length) System.exit(1);
	}
	
	private void formExtentedBusName()
	{
		_extName = new String[_size];
		for (int i=0; i<_size; i++)
		{	
			_extName[i] = _name[i] + _basekv[i];
//			_extName[i] = "'" + _name[i] + Double.toString(_basekv[i]) + "'";
			if (_extName[i].length() > 18) {System.err.println("Warning: length of extented bus name should be less than 18."); /*System.exit(1);*/}
		}
	}
	
	public int size() {return _size;}

	/** Given a bus number, get the bus index */
	public int getIdx(int busNumber) {return _numToIdx.get(busNumber);}
	
	/** Get bus number */
	public int getI(int idx) {return _i[idx];}
	/** Get bus name */
	public String getName(int idx) {return _name[idx];}
	/** Get extented bus name */
	public String getExtName(int idx) {return _extName[idx];}
	/** Get bus base KV */
	public double getBaseKV(int idx) {return _basekv[idx];}
	/** Get bus ide */
	public int getIDE(int idx) {return _ide[idx];}
	/** Get area number */
	public int getArea(int idx) {return _area[idx];}
	/** Get zone number */
	public int getZone(int idx) {return _zone[idx];}
	/** Get owner number */
	public int getOwner(int idx) {return _owner[idx];}

	/** Given an extended bus name, return the associated bus index. */
	public int getBusIndex(String extElemName)
	{
		int busIdx = -1, num = 0;
		extElemName = AuxMethod.removeBoundarySingleQuotes(extElemName);
		for (int i=0; i<_size; i++)
		{
			String extBusName = _extName[i];
			if (!extBusName.substring(0, 12).equalsIgnoreCase(extElemName.substring(0, 12))) continue;
			if (Double.parseDouble(extBusName.substring(12).trim()) != Double.parseDouble(extElemName.substring(12).trim())) continue;
			busIdx = i; num++;
		}
		if(num == 0) System.err.println("No match bus record found for element with bus name '"+extElemName+"'");
		if(num > 1) System.err.println("More than one bus records found for element with bus name '"+extElemName+"'");
		return busIdx;
	}

}
