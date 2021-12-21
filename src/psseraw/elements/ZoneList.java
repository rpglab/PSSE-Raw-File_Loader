package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Zone entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class ZoneList {

	PsseRawModel _model;
	BusList _buses;

	int _size;
	
	// base value from raw file
	int[] _i;
	String[] _zoname;
	
	// default value
	final int _nParam = 2; 
	final String _defaultZONAME = "            ";

	public ZoneList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> i = new ArrayList<Integer>();
		ArrayList<String> zoname = new ArrayList<String>();

		String line;
		_size = 0;
		System.out.println("Start reading zone data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			i.add(Integer.parseInt(tokens[idx++]));
			AuxPsseRaw.readOneParam(zoname, tokens, idx++, _defaultZONAME);
			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}
		
		// save data in member variables of this class - field
		_i = AuxMethod.convtArrayListToInt(i);
		_zoname = AuxMethod.convtArrayListToStr(zoname);

		// remove single quotes for _names if any
		AuxMethod.removeBoundarySingleQuotes(_zoname);

		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading zone data");
	}

	// data check
	private void checkEntryData()
	{
		if(_size != _i.length) System.exit(1);
		if(_size != _zoname.length) System.exit(1);
	}

}
