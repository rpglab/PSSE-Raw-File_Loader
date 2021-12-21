package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;
import psseraw.elements.base.OneTermDevListBase;

/**
 * 
 * Fixed Bus Shunt entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class FixedBusShuntList extends OneTermDevListBase {
	
	// base value from raw file
	String[] _i, _id;
	double[] _gl, _bl;
	
	// default value
	final int _nParam = 5; 
	final String _defaultID = "1";
	final int _defaultSTATUS = 1;
	final double _defaultGL = 0.0, _defaultBL = 0.0;

	public FixedBusShuntList(PsseRawModel model) {super(model);}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> busIdx = new ArrayList<Integer>(); // index of associate bus
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<Integer> status = new ArrayList<Integer>();
		ArrayList<Double> gl = new ArrayList<Double>();
		ArrayList<Double> bl = new ArrayList<Double>();

		String line;
		_size = 0;
		System.out.println("Start reading fixed bus shunt data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			busIdx.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			
			i.add(tokens[idx++]);
			AuxPsseRaw.readOneParam(id, tokens, idx++, _defaultID);
			AuxPsseRaw.readOneParam(status, tokens, idx++, _defaultSTATUS);
			AuxPsseRaw.readOneParam(gl, tokens, idx++, _defaultGL);
			AuxPsseRaw.readOneParam(bl, tokens, idx++, _defaultBL);

			if(idx > _nParam) {System.err.println("More data than desired.."); System.exit(1); }
		}

		// save data in member variables of this class - field
		_busIdx = AuxMethod.convtArrayListToInt(busIdx);
		_i = AuxMethod.convtArrayListToStr(i);
		_id = AuxMethod.convtArrayListToStr(id);
		_stat = AuxMethod.convtArrayListToInt(status);
		_gl = AuxMethod.convtArrayListToDouble(gl);
		_bl = AuxMethod.convtArrayListToDouble(bl);

		// remove single quotes for _names if any
		AuxMethod.removeBoundarySingleQuotes(_i);
		AuxMethod.removeBoundarySingleQuotes(_id);

		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading fixed bus shunt data");
	}

	// data check
	private void checkEntryData()
	{
		if(_size != _busIdx.length) System.exit(1);
		if(_size != _i.length) System.exit(1);
		if(_size != _id.length) System.exit(1);
		if(_size != _stat.length) System.exit(1);
		
		if(_size != _gl.length) System.exit(1);
		if(_size != _bl.length) System.exit(1);
	}

	public double getBL(int i) {return _bl[i];}
	public double[] getBL() {return _bl;}
	
	
}
