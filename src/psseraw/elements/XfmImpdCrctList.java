package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Transformer impedance correction table entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class XfmImpdCrctList {

	PsseRawModel _model;
	BusList _buses;

	int _size;
	
	// base value from raw file
	int[] _i;
	double[] _t1, _f1, _t2, _f2, _t3, _f3, _t4, _f4, _t5, _f5, 
	_t6, _f6, _t7, _f7, _t8, _f8, _t9, _f9, _t10, _f10, _t11, _f11;

	// default value
	final int _nParam = 23; 
	final double _defaultTi = 0.0, _defaultFi = 0.0;

	public XfmImpdCrctList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> i = new ArrayList<Integer>();
		ArrayList<Double> t1 = new ArrayList<Double>();
		ArrayList<Double> f1 = new ArrayList<Double>();
		ArrayList<Double> t2 = new ArrayList<Double>();
		ArrayList<Double> f2 = new ArrayList<Double>();
		ArrayList<Double> t3 = new ArrayList<Double>();
		ArrayList<Double> f3 = new ArrayList<Double>();
		ArrayList<Double> t4 = new ArrayList<Double>();
		ArrayList<Double> f4 = new ArrayList<Double>();
		ArrayList<Double> t5 = new ArrayList<Double>();
		ArrayList<Double> f5 = new ArrayList<Double>();
		ArrayList<Double> t6 = new ArrayList<Double>();
		ArrayList<Double> f6 = new ArrayList<Double>();
		ArrayList<Double> t7 = new ArrayList<Double>();
		ArrayList<Double> f7 = new ArrayList<Double>();
		ArrayList<Double> t8 = new ArrayList<Double>();
		ArrayList<Double> f8 = new ArrayList<Double>();
		ArrayList<Double> t9 = new ArrayList<Double>();
		ArrayList<Double> f9 = new ArrayList<Double>();
		ArrayList<Double> t10 = new ArrayList<Double>();
		ArrayList<Double> f10 = new ArrayList<Double>();
		ArrayList<Double> t11 = new ArrayList<Double>();
		ArrayList<Double> f11 = new ArrayList<Double>();
		
		String line;
		_size = 0;
		System.out.println("Start reading transformer impedance correction table data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			
			i.add(Integer.parseInt(tokens[idx++]));
			AuxPsseRaw.readOneParam(t1, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f1, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t2, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f2, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t3, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f3, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t4, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f4, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t5, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f5, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t6, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f6, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t7, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f7, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t8, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f8, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t9, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f9, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t10, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f10, tokens, idx++, _defaultFi);
			AuxPsseRaw.readOneParam(t11, tokens, idx++, _defaultTi);
			AuxPsseRaw.readOneParam(f11, tokens, idx++, _defaultFi);

			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}
		
		// save data in member variables of this class - field
		_i = AuxMethod.convtArrayListToInt(i);
		_t1 = AuxMethod.convtArrayListToDouble(t1);
		_f1 = AuxMethod.convtArrayListToDouble(f1);
		_t2 = AuxMethod.convtArrayListToDouble(t2);
		_f2 = AuxMethod.convtArrayListToDouble(f2);
		_t3 = AuxMethod.convtArrayListToDouble(t3);
		_f3 = AuxMethod.convtArrayListToDouble(f3);
		_t4 = AuxMethod.convtArrayListToDouble(t4);
		_f4 = AuxMethod.convtArrayListToDouble(f4);
		_t5 = AuxMethod.convtArrayListToDouble(t5);
		_f5 = AuxMethod.convtArrayListToDouble(f5);
		_t6 = AuxMethod.convtArrayListToDouble(t6);
		_f6 = AuxMethod.convtArrayListToDouble(f6);
		_t7 = AuxMethod.convtArrayListToDouble(t7);
		_f7 = AuxMethod.convtArrayListToDouble(f7);
		_t8 = AuxMethod.convtArrayListToDouble(t8);
		_f8 = AuxMethod.convtArrayListToDouble(f8);
		_t9 = AuxMethod.convtArrayListToDouble(t9);
		_f9 = AuxMethod.convtArrayListToDouble(f9);
		_t10 = AuxMethod.convtArrayListToDouble(t10);
		_f10 = AuxMethod.convtArrayListToDouble(f10);
		_t11 = AuxMethod.convtArrayListToDouble(t11);
		_f11 = AuxMethod.convtArrayListToDouble(f11);

		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading transformer impedance correction table data");
	}
	
	// data check
	private void checkEntryData()
	{
		if(_size != _i.length) System.exit(1);
		if(_size != _f5.length) System.exit(1);
		if(_size != _t1.length) System.exit(1);
		
		if(_size != _t9.length) System.exit(1);
		if(_size != _t11.length) System.exit(1);
		if(_size != _t11.length) System.exit(1);
		if(_size != _f11.length) System.exit(1);
	}


}
