package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Non-transformer Branch entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class LineList {

	PsseRawModel _model;
	BusList _buses;
	int _size;
	
	int[] _busFrmIdx;    // index of the from bus 
	int[] _busToIdx;    // index of the to bus 
	
	int[] _busFrmNumRaw;   // frm bus number before reindex
	int[] _busToNumRaw;   // to bus number before reindex
	String[] _cktSimple;   // remove any blanks/taps in the beginning and ending parts.

	// base value from raw file
	protected String _i[],_j[],_ckt[];
	protected double _r[],_x[],_b[],_ratea[],_rateb[],_ratec[],_gi[],_bi[],_gj[],_bj[];
	protected int[] _st, _met;
	protected double _len[];
	int[] _o1, _o2, _o3, _o4;
	double[] _f1, _f2, _f3, _f4;

	// default value
	final int _nParam = 24; 
	final String _defaultCKT = "1";
	final int _defaultST = 1, _defaultMET = 0;
	final double _defaultB = 0.0, _defaultRATEA = 0.0, _defaultRATEB = 0.0, _defaultRATEC = 0.0, 
			_defaultGI = 1.0, _defaultBI = 0.0, _defaultGJ = 1.0, 
			_defaultBJ = 0.0, _defaultLEN = 0.0;
	
	public LineList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> busFrmIdx = new ArrayList<Integer>(); // index of associate from bus
		ArrayList<Integer> busToIdx = new ArrayList<Integer>(); // index of associate to bus
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> j = new ArrayList<String>();
		ArrayList<String> ckt = new ArrayList<String>();
		ArrayList<Double> r = new ArrayList<Double>();
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList<Double> b = new ArrayList<Double>();
		ArrayList<Double> ratea = new ArrayList<Double>();
		ArrayList<Double> rateb = new ArrayList<Double>();
		ArrayList<Double> ratec = new ArrayList<Double>();
		ArrayList<Double> gi = new ArrayList<Double>();
		ArrayList<Double> bi = new ArrayList<Double>();
		ArrayList<Double> gj = new ArrayList<Double>();
		ArrayList<Double> bj = new ArrayList<Double>();
		ArrayList<Integer> st = new ArrayList<Integer>();
		ArrayList<Integer> met = new ArrayList<Integer>();
		ArrayList<Double> len = new ArrayList<Double>();
		ArrayList<Integer> o1 = new ArrayList<Integer>();
		ArrayList<Double> f1 = new ArrayList<Double>();
		ArrayList<Integer> o2 = new ArrayList<Integer>();
		ArrayList<Double> f2 = new ArrayList<Double>();
		ArrayList<Integer> o3 = new ArrayList<Integer>();
		ArrayList<Double> f3 = new ArrayList<Double>();
		ArrayList<Integer> o4 = new ArrayList<Integer>();
		ArrayList<Double> f4 = new ArrayList<Double>();

		String line;
		_size = 0;
		System.out.println("Start reading transmission line data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			
			int busIdxTmp = AuxPsseRaw.determineBusIndex(_buses, tokens, idx);
			busFrmIdx.add(busIdxTmp);
			i.add(tokens[idx++]);
			
			busToIdx.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			j.add(tokens[idx++]);
			
			AuxPsseRaw.readOneParam(ckt, tokens, idx++, _defaultCKT);
			r.add(Double.parseDouble(tokens[idx++]));
			x.add(Double.parseDouble(tokens[idx++]));
			AuxPsseRaw.readOneParam(b, tokens, idx++, _defaultB);
			AuxPsseRaw.readOneParam(ratea, tokens, idx++, _defaultRATEA);
			AuxPsseRaw.readOneParam(rateb, tokens, idx++, _defaultRATEB);
			AuxPsseRaw.readOneParam(ratec, tokens, idx++, _defaultRATEC);
			AuxPsseRaw.readOneParam(gi, tokens, idx++, _defaultGI);
			AuxPsseRaw.readOneParam(bi, tokens, idx++, _defaultBI);
			AuxPsseRaw.readOneParam(gj, tokens, idx++, _defaultGJ);
			AuxPsseRaw.readOneParam(bj, tokens, idx++, _defaultBJ);
			AuxPsseRaw.readOneParam(st, tokens, idx++, _defaultST);
			AuxPsseRaw.readOneParam(met, tokens, idx++, _defaultMET);
			AuxPsseRaw.readOneParam(len, tokens, idx++, _defaultLEN);

			AuxPsseRaw.readOneParam(o1, tokens, idx++, _buses.getOwner(busIdxTmp));
			AuxPsseRaw.readOneParam(f1, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o2, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f2, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o3, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f3, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o4, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f4, tokens, idx++, 1.0);
			
			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}

		// save data in member variables of this class - field
		_busFrmIdx = AuxMethod.convtArrayListToInt(busFrmIdx);
		_busToIdx = AuxMethod.convtArrayListToInt(busToIdx);
		_i = AuxMethod.convtArrayListToStr(i);
		_j = AuxMethod.convtArrayListToStr(j);
		_ckt = AuxMethod.convtArrayListToStr(ckt);
		_r = AuxMethod.convtArrayListToDouble(r);
		_x = AuxMethod.convtArrayListToDouble(x);
		_b = AuxMethod.convtArrayListToDouble(b);
		_ratea = AuxMethod.convtArrayListToDouble(ratea);
		_rateb = AuxMethod.convtArrayListToDouble(rateb);
		_ratec = AuxMethod.convtArrayListToDouble(ratec);
		_gi = AuxMethod.convtArrayListToDouble(gi);
		_bi = AuxMethod.convtArrayListToDouble(bi);
		_gj = AuxMethod.convtArrayListToDouble(gj);
		_bj = AuxMethod.convtArrayListToDouble(bj);
		_st = AuxMethod.convtArrayListToInt(st);
		_met = AuxMethod.convtArrayListToInt(met);
		_len = AuxMethod.convtArrayListToDouble(len);
		_o1 = AuxMethod.convtArrayListToInt(o1);
		_o2 = AuxMethod.convtArrayListToInt(o2);
		_o3 = AuxMethod.convtArrayListToInt(o3);
		_o4 = AuxMethod.convtArrayListToInt(o4);
		_f1 = AuxMethod.convtArrayListToDouble(f1);
		_f2 = AuxMethod.convtArrayListToDouble(f2);
		_f3 = AuxMethod.convtArrayListToDouble(f3);
		_f4 = AuxMethod.convtArrayListToDouble(f4);

		// remove single quotes if any
		AuxMethod.removeBoundarySingleQuotes(_i);
		AuxMethod.removeBoundarySingleQuotes(_j);
		AuxMethod.removeBoundarySingleQuotes(_ckt);

		if (_size != 0) checkEntryData();
		else System.err.println("No transmission line found in the input raw file.");
		System.out.println("   Finish reading transmission line data");
	}

	// data check
	private void checkEntryData()
	{
		if(_size != _busFrmIdx.length) System.exit(1);
		if(_size != _i.length) System.exit(1);
		if(_size != _ckt.length) System.exit(1);
		
		if(_size != _len.length) System.exit(1);
		if(_size != _o4.length) System.exit(1);
		if(_size != _f4.length) System.exit(1);
	}
	
	private void calcBusNumberRaw()
	{
		_busFrmNumRaw = new int[_size];
		_busToNumRaw = new int[_size];
		for (int i=0; i<_size; i++) {
			_busFrmNumRaw[i] = _buses.getI(_busFrmIdx[i]);
			_busToNumRaw[i] = _buses.getI(_busToIdx[i]);
		}
	}
	
	private void simplifyCKT()
	{
		_cktSimple = new String[_size];
		for (int i=0; i<_size; i++) {
			_cktSimple[i] = _ckt[i].trim();
		}
	}
	
	public int size() {return _size;}
	public boolean isInSvc(int i) {return (_st[i] == 1) ? true : false;}
	public int getStat(int i) {return _st[i];}

	public int[] getIdxFrmBus() {return _busFrmIdx;}
	public int[] getIdxToBus() {return _busToIdx;}

	public int getIdxFrmBus(int idx) {return _busFrmIdx[idx];}
	public int getIdxToBus(int idx) {return _busToIdx[idx];}
	
	public String[] getID()
	{
		if (_cktSimple == null) simplifyCKT();
		return _cktSimple;
	}
	public String getID(int idx) {return getID()[idx];}

	public int[] getFrmBusNumber() 
	{
		if (_busFrmNumRaw == null) calcBusNumberRaw();
		return _busFrmNumRaw;
	}

	public int[] getToBusNumber()
	{
		if (_busToNumRaw == null) calcBusNumberRaw();
		return _busToNumRaw;
	}
	
	
}
