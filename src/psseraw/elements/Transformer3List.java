package psseraw.elements;

import java.util.Arrays;

import psseraw.PsseRawModel;

/**
 * 
 * 3-winding transformer entry data from .raw file.
 * Phase shifter is not included in this class.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class Transformer3List {

	PsseRawModel _model;
	int _size;
	
	int[] _busIndx;    // index of the 1st winding bus 
	int[] _busJndx;    // index of the 2nd winding bus 
	int[] _busKndx;    // index of the 3rd winding bus 

	int[] _idxRawXfm;  // index of this xfm in the raw xfm entry
	
	int[] _cont1Index;  // the associated bus index
	int[] _cont1Number;  // the associated bus number
	int[] _cont2Index;  // the associated bus index
	int[] _cont2Number;  // the associated bus number
	int[] _cont3Index;  // the associated bus index
	int[] _cont3Number;  // the associated bus number

	// base value from raw file
	/* line 1 */
	String[] _i, _j, _k, _ckt;
	int[] _cw, _cz, _cm;
	double[] _mag1, _mag2;
	int[] _nmetr;
	String[] _name;
	int[] _stat, _o1, _o2, _o3, _o4;
	double[] _f1, _f2, _f3, _f4;
	String[] _vecgrp;

	/* line 2 */
	double[] _r12, _x12, _sb12, _r23, _x23, _sb23, _r31, _x31, _sb31,
		_vmstar, _anstar;

	/* line 3 */
	double[] _windv1, _nomv1, _ang1, _rata1, _ratb1, _ratc1;
	int[] _cod1;
	String[] _cont1;
	double[] _rma1, _rmi1, _vma1, _vmi1;
	int[] _ntp1, _tab1;
	double[] _cr1, _cx1, _cnxa1;
	
	/* line 4 */
	double[] _windv2, _nomv2, _ang2, _rata2, _ratb2, _ratc2;
	int[] _cod2;
	String[] _cont2;
	double[] _rma2, _rmi2, _vma2, _vmi2;
	int[] _ntp2, _tab2;
	double[] _cr2, _cx2, _cnxa2;
	
	/* line 5 */
	double[] _windv3, _nomv3, _ang3, _rata3, _ratb3, _ratc3;
	int[] _cod3;
	String[] _cont3;
	double[] _rma3, _rmi3, _vma3, _vmi3;
	int[] _ntp3, _tab3;
	double[] _cr3, _cx3, _cnxa3;

	public Transformer3List(PsseRawModel model) {_model = model;}

	/** Initialize the size of 2-winding transformers. */
	void initVarSize(int size)
	{
		_size = size;
		_idxRawXfm = new int[_size];
		
		_busIndx = new int[_size];
		_busJndx = new int[_size];
		_busKndx = new int[_size];

		/* line 1 */
		 _i = new String[_size]; 
		 _j = new String[_size]; 
		 _k = new String[_size];
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
		 _r12 = new double[_size]; 
		 _x12 = new double[_size]; 
		 _sb12 = new double[_size]; 
		 _r23 = new double[_size]; 
		 _x23 = new double[_size]; 
		 _sb23 = new double[_size]; 
		 _r31 = new double[_size]; 
		 _x31 = new double[_size]; 
		 _sb31 = new double[_size]; 
		 _vmstar = new double[_size]; 
		 _anstar = new double[_size]; 
		 
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
		 _ang2 = new double[_size]; 
		 _rata2 = new double[_size]; 
		 _ratb2 = new double[_size]; 
		 _ratc2 = new double[_size]; 
		 _cod2 = new int[_size]; 
		 _cont2 = new String[_size]; 
		 _rma2 = new double[_size]; 
		 _rmi2 = new double[_size]; 
		 _vma2 = new double[_size]; 
		 _vmi2 = new double[_size]; 
		 _ntp2 = new int[_size]; 
		 _tab2 = new int[_size]; 
		 _cr2 = new double[_size]; 
		 _cx2 = new double[_size]; 
		 _cnxa2 = new double[_size]; 
		 
		/* line 5 */
		 _windv3 = new double[_size]; 
		 _nomv3 = new double[_size]; 
		 _ang3 = new double[_size]; 
		 _rata3 = new double[_size]; 
		 _ratb3 = new double[_size]; 
		 _ratc3 = new double[_size]; 
		 _cod3 = new int[_size]; 
		 _cont3 = new String[_size]; 
		 _rma3 = new double[_size]; 
		 _rmi3 = new double[_size]; 
		 _vma3 = new double[_size]; 
		 _vmi3 = new double[_size]; 
		 _ntp3 = new int[_size]; 
		 _tab3 = new int[_size]; 
		 _cr3 = new double[_size]; 
		 _cx3 = new double[_size]; 
		 _cnxa3 = new double[_size]; 
		 
		 /* Preprocess */
		 _cont1Index = new int[_size];
		 _cont1Number = new int[_size];
		 Arrays.fill(_cont1Index, -1);
		 Arrays.fill(_cont1Number, 0);

		 _cont2Index = new int[_size];
		 _cont2Number = new int[_size];
		 Arrays.fill(_cont2Index, -1);
		 Arrays.fill(_cont2Number, 0);

		 _cont3Index = new int[_size];
		 _cont3Number = new int[_size];
		 Arrays.fill(_cont3Index, -1);
		 Arrays.fill(_cont3Number, 0);
	}

	public boolean isPhaseShifterXfm(int i) {return (_cod1[i] == 3 || _cod1[i] == -3) ? true : false;}
	public int size() {return _size;}

	public int[] getIndxBus() {return _busIndx;}
	public int[] getJndxBus() {return _busJndx;}
	public int[] getKndxBus() {return _busKndx;}
	public String[] getID() {return _ckt;}

	public int getIndxBus(int idx) {return _busIndx[idx];}
	public int getJndxBus(int idx) {return _busJndx[idx];}
	public int getKndxBus(int idx) {return _busKndx[idx];}
	public String getID(int idx) {return _ckt[idx];}

	public int getControledBusNumber1(int idx) {return _cont1Number[idx];}
	public int getControledBusNumber2(int idx) {return _cont2Number[idx];}
	public int getControledBusNumber3(int idx) {return _cont3Number[idx];}

}
