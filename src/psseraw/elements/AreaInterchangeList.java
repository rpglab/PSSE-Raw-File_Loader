package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Area interchange entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class AreaInterchangeList {

	PsseRawModel _model;
	BusList _buses;

	int _size;
	int[] _busIdx;    // index of the bus that load connects to

	// base value from raw file
	int[] _i;
	String[] _isw;
	double[] _pdes, _ptol;
	String[] _arname;
	
	// default value
	final int _nParam = 5; 
	final String _defaultISW = "0", _defaultARNAME = "            ";
	final double _defaultPDES = 0.0, _defaultPTOL = 10.0;

	public AreaInterchangeList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> busIdx = new ArrayList<Integer>(); // index of associate bus
		ArrayList<Integer> i = new ArrayList<Integer>();
		ArrayList<String> isw = new ArrayList<String>();
		ArrayList<Double> pdes = new ArrayList<Double>();
		ArrayList<Double> ptol = new ArrayList<Double>();
		ArrayList<String> arname = new ArrayList<String>();
		
		String line;
		_size = 0;
		System.out.println("Start reading area interchange data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;

			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0, busIdxTmp = -1;

			i.add(Integer.parseInt(tokens[idx++]));
			if (Integer.parseInt(tokens[idx]) == 0) busIdxTmp = 0;
			else busIdxTmp = AuxPsseRaw.determineBusIndex(_buses, tokens, idx);
			busIdx.add(busIdxTmp);

			AuxPsseRaw.readOneParam(isw, tokens, idx++, _defaultISW);
			AuxPsseRaw.readOneParam(pdes, tokens, idx++, _defaultPDES);
			AuxPsseRaw.readOneParam(ptol, tokens, idx++, _defaultPTOL);
			AuxPsseRaw.readOneParam(arname, tokens, idx++, _defaultARNAME);
			
			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}
		
		// save data in member variables of this class - field
		_busIdx = AuxMethod.convtArrayListToInt(busIdx);
		_i = AuxMethod.convtArrayListToInt(i);
		_isw = AuxMethod.convtArrayListToStr(isw);
		_pdes = AuxMethod.convtArrayListToDouble(pdes);
		_ptol = AuxMethod.convtArrayListToDouble(ptol);
		_arname = AuxMethod.convtArrayListToStr(arname);

		// remove single quotes for _names if any
		AuxMethod.removeBoundarySingleQuotes(_arname);
		
		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading area interchange data");
	}
	
	// data check
	private void checkEntryData()
	{
		if(_size != _busIdx.length) System.exit(1);
		if(_size != _i.length) System.exit(1);
		if(_size != _isw.length) System.exit(1);
		if(_size != _pdes.length) System.exit(1);
		if(_size != _ptol.length) System.exit(1);
		if(_size != _arname.length) System.exit(1);
	}



}
