package psseraw.elements;

import java.util.Arrays;

import psseraw.PsseRawModel;

/**
 * 
 * 2-winding transformer entry data from .raw file.
 * Phase shifter is not included in this class.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class Transformer2List {

	PsseRawModel _model;
	int _size;
	
	int[] _busFrmIdx;    // index of the from bus 
	int[] _busToIdx;    // index of the to bus 

	int[] _idxRawXfm;  // index of this xfm in the raw xfm entry
	
	int[] _cont1Index;  // the associated bus index
	int[] _cont1Number;  // the associated bus number

	// base value from raw file
	/* line 1 */
	String[] _i, _j, _ckt;     // _ckt may contains " "
	int[] _cw, _cz, _cm;
	double[] _mag1, _mag2;
	int[] _nmetr;
	String[] _name;
	int[] _stat, _o1, _o2, _o3, _o4;
	double[] _f1, _f2, _f3, _f4;
	String[] _vecgrp;
	
	/* line 2 */
	double[] _r, _x, _sbase;
	
	/* line 3 */
	double[] _windv1, _nomv1, _ang1, _rata1, _ratb1, _ratc1;
	int[] _cod1;
	String[] _cont1;
	double[] _rma1, _rmi1, _vma1, _vmi1;
	int[] _ntp1, _tab1;
	double[] _cr1, _cx1, _cnxa1;
	
	/* line 4 */
	double[] _windv2, _nomv2;
	
	public Transformer2List(PsseRawModel model) {_model = model;}

	
	/** Initialize the size of 2-winding transformers. */
	void initVarSize(int size)
	{
		_size = size;
		_idxRawXfm = new int[_size];

		_busFrmIdx = new int[_size];
		_busToIdx = new int[_size];

		/* line 1 */
		 _i = new String[_size]; 
		 _j = new String[_size]; 
		 _ckt = new String[_size]; 
		 _cw = new int[_size]; 
		 _cz = new int[_size]; 
		 _cm = new int[_size]; 
		 _cw = new int[_size]; 
		 _mag1 = new double[_size]; 
		 _mag2 = new double[_size]; 
		 _nmetr = new int[_size]; 
		 _name = new String[_size]; 
		 _nmetr = new int[_size]; 
		 _stat = new int[_size]; 
		 _o1 = new int[_size]; 
		 _o2 = new int[_size]; 
		 _o3 = new int[_size]; 
		 _o4 = new int[_size]; 
		 _f1 = new double[_size]; 
		 _f2 = new double[_size]; 
		 _f3 = new double[_size]; 
		 _f4 = new double[_size]; 
		 _vecgrp = new String[_size];
		 
		/* line 2 */
		 _r = new double[_size]; 
		 _x = new double[_size]; 
		 _sbase = new double[_size]; 

		/* line 3 */
		 _windv1 = new double[_size]; 
		 _nomv1 = new double[_size]; 
		 _ang1 = new double[_size]; 
		 _rata1 = new double[_size]; 
		 _ratb1 = new double[_size]; 
		 _ratc1 = new double[_size]; 
		 _cod1 = new int[_size]; 
		 _cont1 = new String[_size]; 
		 _rma1 = new double[_size]; 
		 _rmi1 = new double[_size]; 
		 _vma1 = new double[_size]; 
		 _vmi1 = new double[_size]; 
		 _ntp1 = new int[_size]; 
		 _tab1 = new int[_size]; 
		 _cr1 = new double[_size]; 
		 _cx1 = new double[_size]; 
		 _cnxa1 = new double[_size]; 

		/* line 4 */
		 _windv2 = new double[_size]; 
		 _nomv2 = new double[_size]; 
		 
		 /* Preprocess */
		 _cont1Index = new int[_size];
		 _cont1Number = new int[_size];
		 Arrays.fill(_cont1Index, -1);
		 Arrays.fill(_cont1Number, 0);
	}
	
	public boolean isPhaseShifterXfm(int i) {return (_cod1[i] == 3 || _cod1[i] == -3) ? true : false;}
	public int size() {return _size;}
	
	public int[] getIdxFrmBus() {return _busFrmIdx;}
	public int[] getIdxToBus() {return _busToIdx;}
//	public String[] getID() {return _ckt;}   //note that _ckt may have " " which is undesired

	public int getIdxFrmBus(int idx) {return _busFrmIdx[idx];}
	public int getIdxToBus(int idx) {return _busToIdx[idx];}
	public String getID(int idx) {return _ckt[idx].trim();}

	public int getControledBusNumber(int idx) {return _cont1Number[idx];}
	public int getControledBusIdx(int idx) {return _cont1Index[idx];}
	
	public int getCW(int idx) {return _cw[idx];}
	public double getNOMV1(int idx) {
		double nomv1 = _nomv1[idx];
		if (nomv1 == 0) nomv1 = _model.getBusList().getBaseKV(_busFrmIdx[idx]);
		return nomv1;
	}
	public int getCOD1(int idx) {return _cod1[idx];}
	
}


