package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Inter area transfer entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class InterareaTransferList {

	PsseRawModel _model;
	BusList _buses;

	int _size;
	
	// base value from raw file
	int[] _arfrom, _arto;
	String[] _trid;
	double[] _ptran;

	// default value
	final int _nParam = 4;
	final String _defaultTRID = "1";
	final double _defaultPTRAN = 0.0;

	public InterareaTransferList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> arfrom = new ArrayList<Integer>();
		ArrayList<Integer> arto = new ArrayList<Integer>();
		ArrayList<String> trid = new ArrayList<String>();
		ArrayList<Double> ptran = new ArrayList<Double>();

		String line;
		_size = 0;
		System.out.println("Start reading interarea transfer data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			arfrom.add(Integer.parseInt(tokens[idx++]));
			arto.add(Integer.parseInt(tokens[idx++]));
			AuxPsseRaw.readOneParam(trid, tokens, idx++, _defaultTRID);
			AuxPsseRaw.readOneParam(ptran, tokens, idx++, _defaultPTRAN);
			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}
		
		// save data in member variables of this class - field
		_arfrom = AuxMethod.convtArrayListToInt(arfrom);
		_arto = AuxMethod.convtArrayListToInt(arto);
		_trid = AuxMethod.convtArrayListToStr(trid);
		_ptran = AuxMethod.convtArrayListToDouble(ptran);

		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading interarea transfer data");
	}
	
	// data check
	private void checkEntryData()
	{
		if(_size != _arfrom.length) System.exit(1);
		if(_size != _arto.length) System.exit(1);
		if(_size != _trid.length) System.exit(1);
		if(_size != _ptran.length) System.exit(1);
	}

}
