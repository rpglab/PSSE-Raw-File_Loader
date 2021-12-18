package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;
import psseraw.elements.base.OneTermDevListBase;

/**
 * 
 * Switched shunt entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class SwitchedShuntList extends OneTermDevListBase {

	int[] _swremBusIdx; // index of bus where voltage is controlled by this switched shunt. -1 if NA
	int[] _swremBusNumber; // number of bus where voltage is controlled by this switched shunt. -1 if NA
	
	// base value from raw file
	String[] _i;
	int[] _modsw, _adjm, _stat;
	double[] _vswhi, _vswlo;
	String[] _swrem;
	double[] _rmpct;
	String[] _rmidnt;
	double[] _binit;
	
	int[] _n1, _n2, _n3, _n4, _n5, _n6, _n7, _n8; 
	double[] _b1, _b2, _b3, _b4, _b5, _b6, _b7, _b8; 
	
	// default value
	final int _nParam = 26;
	final String _defaultRMIDNT = " ", _defaultSWREM = "0";
	final int _defaultMODSW = 1, _defaultADJM = 0, _defaultSTAT = 0;
	final double _defaultVSWHI = 1.0, _defaultVSWLO = 1.0, _defaultRMPCT = 100.0, 
			_defaultBINIT = 0.0;
	
	public SwitchedShuntList(PsseRawModel model) {super(model);}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> busIdx = new ArrayList<Integer>();
		
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<Integer> modsw = new ArrayList<Integer>();
		ArrayList<Integer> adjm = new ArrayList<Integer>();
		ArrayList<Integer> stat = new ArrayList<Integer>();
		ArrayList<Double> vswhi = new ArrayList<Double>();
		ArrayList<Double> vswlo = new ArrayList<Double>();
		ArrayList<String> swrem = new ArrayList<String>();
		ArrayList<Double> rmpct = new ArrayList<Double>();
		ArrayList<String> rmidnt = new ArrayList<String>();
		ArrayList<Double> binit = new ArrayList<Double>();
		ArrayList<Integer> n1 = new ArrayList<Integer>();
		ArrayList<Double> b1 = new ArrayList<Double>();
		ArrayList<Integer> n2 = new ArrayList<Integer>();
		ArrayList<Double> b2 = new ArrayList<Double>();
		ArrayList<Integer> n3 = new ArrayList<Integer>();
		ArrayList<Double> b3 = new ArrayList<Double>();
		ArrayList<Integer> n4 = new ArrayList<Integer>();
		ArrayList<Double> b4 = new ArrayList<Double>();
		ArrayList<Integer> n5 = new ArrayList<Integer>();
		ArrayList<Double> b5 = new ArrayList<Double>();
		ArrayList<Integer> n6 = new ArrayList<Integer>();
		ArrayList<Double> b6 = new ArrayList<Double>();
		ArrayList<Integer> n7 = new ArrayList<Integer>();
		ArrayList<Double> b7 = new ArrayList<Double>();
		ArrayList<Integer> n8 = new ArrayList<Integer>();
		ArrayList<Double> b8 = new ArrayList<Double>();

		String line;
		_size = 0;
		System.out.println("Start reading switched shunt data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			
			int busIdxTmp = AuxPsseRaw.determineBusIndex(_buses, tokens, idx);
			busIdx.add(busIdxTmp);
			i.add(tokens[idx++]);
			
			AuxPsseRaw.readOneParam(modsw, tokens, idx++, _defaultMODSW);
			AuxPsseRaw.readOneParam(adjm, tokens, idx++, _defaultADJM);
			AuxPsseRaw.readOneParam(stat, tokens, idx++, _defaultSTAT);
			AuxPsseRaw.readOneParam(vswhi, tokens, idx++, _defaultVSWHI);
			AuxPsseRaw.readOneParam(vswlo, tokens, idx++, _defaultVSWLO);
			AuxPsseRaw.readOneParam(swrem, tokens, idx++, _defaultSWREM);
			AuxPsseRaw.readOneParam(rmpct, tokens, idx++, _defaultRMPCT);
			AuxPsseRaw.readOneParam(rmidnt, tokens, idx++, _defaultRMIDNT);
			AuxPsseRaw.readOneParam(binit, tokens, idx++, _defaultBINIT);
			
			AuxPsseRaw.readOneParam(n1, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(b1, tokens, idx++, 0.0);
			AuxPsseRaw.readOneParam(n2, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(b2, tokens, idx++, 0.0);
			AuxPsseRaw.readOneParam(n3, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(b3, tokens, idx++, 0.0);
			AuxPsseRaw.readOneParam(n4, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(b4, tokens, idx++, 0.0);
			AuxPsseRaw.readOneParam(n5, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(b5, tokens, idx++, 0.0);
			AuxPsseRaw.readOneParam(n6, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(b6, tokens, idx++, 0.0);
			AuxPsseRaw.readOneParam(n7, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(b7, tokens, idx++, 0.0);
			AuxPsseRaw.readOneParam(n8, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(b8, tokens, idx++, 0.0);
			
			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}

		// save data in member variables of this class - field
		_busIdx = AuxMethod.convtArrayListToInt(busIdx);
		_i = AuxMethod.convtArrayListToStr(i);
		_modsw = AuxMethod.convtArrayListToInt(modsw);
		_adjm = AuxMethod.convtArrayListToInt(adjm);
		_stat = AuxMethod.convtArrayListToInt(stat);
		_vswhi = AuxMethod.convtArrayListToDouble(vswhi);
		_vswlo = AuxMethod.convtArrayListToDouble(vswlo);
		_swrem = AuxMethod.convtArrayListToStr(swrem);
		_rmpct = AuxMethod.convtArrayListToDouble(rmpct);
		_rmidnt = AuxMethod.convtArrayListToStr(rmidnt);
		_binit = AuxMethod.convtArrayListToDouble(binit);
		_n1 = AuxMethod.convtArrayListToInt(n1);
		_b1 = AuxMethod.convtArrayListToDouble(b1);
		_n2 = AuxMethod.convtArrayListToInt(n2);
		_b2 = AuxMethod.convtArrayListToDouble(b2);
		_n3 = AuxMethod.convtArrayListToInt(n3);
		_b3 = AuxMethod.convtArrayListToDouble(b3);
		_n4 = AuxMethod.convtArrayListToInt(n4);
		_b4 = AuxMethod.convtArrayListToDouble(b4);
		_n5 = AuxMethod.convtArrayListToInt(n5);
		_b5 = AuxMethod.convtArrayListToDouble(b5);
		_n6 = AuxMethod.convtArrayListToInt(n6);
		_b6 = AuxMethod.convtArrayListToDouble(b6);
		_n7 = AuxMethod.convtArrayListToInt(n7);
		_b7 = AuxMethod.convtArrayListToDouble(b7);
		_n8 = AuxMethod.convtArrayListToInt(n8);
		_b8 = AuxMethod.convtArrayListToDouble(b8);

		// remove single quotes for _names if any
		AuxMethod.removeBoundarySingleQuotes(_i);
		AuxMethod.removeBoundarySingleQuotes(_swrem);
		AuxMethod.removeBoundarySingleQuotes(_rmidnt);

		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading switched shunt data");
	}
	
	// data check
	private void checkEntryData()
	{
		if(_size != _busIdx.length) System.exit(1);
		if(_size != _i.length) System.exit(1);
		if(_size != _vswhi.length) System.exit(1);
		if(_size != _rmpct.length) System.exit(1);
		
		if(_size != _binit.length) System.exit(1);
		if(_size != _b4.length) System.exit(1);
		if(_size != _b5.length) System.exit(1);
		if(_size != _n8.length) System.exit(1);
	}

	/**  Get the first matched shunt index */
	public int getShuntIdx(int busNumber)
	{
		if (_busNumber == null) calcBusNumberRaw();
		for (int i=0; i<_size; i++) {
			if (_busNumber[i] == busNumber) return i;
		}
		return -1;
	}
	
	/**  Get the first matched shunt index. The input number is swrem bus number. */
	public int getShuntIdxGivenSwremBusNumber(int busNumber)
	{
		if (_swremBusNumber == null) calcSwremBusNumber();
		for (int i=0; i<_size; i++) {
			if (_swremBusNumber[i] == busNumber) return i;
		}
		return -1;
	}
	
	/**  Get the first matched shunt index. The input number is swrem bus index. */
	public int getShuntIdxGivenSwremBusIdx(int swremBusIdx)
	{
		if (_swremBusIdx == null) calcSwremBusIdx();
		for (int i=0; i<_size; i++) {
			if (_swremBusIdx[i] == swremBusIdx) return i;
		}
		return -1;
	}
	
	private void calcSwremBusNumber()
	{
		if (_swremBusIdx == null) calcSwremBusIdx();
		_swremBusNumber = new int[_size];
		for (int i=0; i<_size; i++) {
			if (_swremBusIdx[i] == -1) continue;
			_swremBusNumber[i] = _buses.getI(_swremBusIdx[i]);
		}
	}
	
	private void calcSwremBusIdx()
	{
		_swremBusIdx = new int[_size];
		for (int i=0; i<_size; i++) {
			if (_swrem[i].equals("0") == true) _swremBusIdx[i] = -1;
			else _swremBusIdx[i] = AuxPsseRaw.determineBusIndex(_buses, _swrem[i]);
		}
	}

	public int[] getSwremBusNumber() 
	{
		if (_swremBusNumber == null) calcSwremBusNumber();
		return _swremBusNumber;
	}
	
	public int[] getSwremBusIdx()
	{
		if (_swremBusIdx == null) calcSwremBusIdx();
		return _swremBusIdx;
	}
	
	public double getRmpct(int i) {return _rmpct[i];}

}
