package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Voltage source converter (VSC) DC transmission line entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class VSCDCLineList {

	PsseRawModel _model;
	BusList _buses;

	int _size;
	int[] _idxBus1;    // index of the inverter bus 
	int[] _idxBus2;    // index of the converter bus 

	// base value from raw file
	/*  line 1  */
	String[] _name;
	int[] _mdc;
	double[] _rdc;
	int[] _o1, _o2, _o3, _o4;
	double[] _f1, _f2, _f3, _f4;

	/*  line 2  */
	String[] _ibus1;
	int[] _type1, _mode1;
	double[] _dcset1, _acset1, _aloss1, _bloss1, _minloss1, _smax1, _imax1, _pwf1, _maxq1, _minq1;
	String[] _remot1;
	double[] _rmpct1;
	
	/*  line 3  */
	String[] _ibus2;
	int[] _type2, _mode2;
	double[] _dcset2, _acset2, _aloss2, _bloss2, _minloss2, _smax2, _imax2, _pwf2, _maxq2, _minq2;
	String[] _remot2;
	double[] _rmpct2;

	
	// default values - line 1
	final int _nParamL1 = 11;
	final int _defaultMDC = 1;

	// default values - line 2
	final int _nParamL2 = 15;
	final int _defaultMODE1 = 1;
	final String _defaultREMOT1 = "0";
	final double _defaultACSET1 = 1.0, _defaultALOSS1 = 0.0,
			_defaultBLOSS1 = 0.0, _defaultMINLOSS1 = 0.0,
			_defaultSMAX1 = 0.0, _defaultIMAX1 = 0.0, _defaultPWF1 = 1.0,
			_defaultMAXQ1 = 9999.0, _defaultMINQ1 = -9999.0, _defaultRMPCT1 = 100.0;
	
	// default values - line 3
	final int _nParamL3 = 15;
	final int _defaultMODE2 = 1;
	final String _defaultREMOT2 = "0";
	final double _defaultACSET2 = 1.0, _defaultALOSS2 = 0.0,
			_defaultBLOSS2 = 0.0, _defaultMINLOSS2 = 0.0,
			_defaultSMAX2 = 0.0, _defaultIMAX2 = 0.0, _defaultPWF2 = 1.0,
			_defaultMAXQ2 = 9999.0, _defaultMINQ2 = -9999.0, _defaultRMPCT2 = 100.0;

	public VSCDCLineList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> idxBus1 = new ArrayList<Integer>();
		ArrayList<Integer> idxBus2 = new ArrayList<Integer>(); 

		/* Line 1 */
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<Integer> mdc = new ArrayList<Integer>();
		ArrayList<Double> rdc = new ArrayList<Double>();
		ArrayList<Integer> o1 = new ArrayList<Integer>();
		ArrayList<Double> f1 = new ArrayList<Double>();
		ArrayList<Integer> o2 = new ArrayList<Integer>();
		ArrayList<Double> f2 = new ArrayList<Double>();
		ArrayList<Integer> o3 = new ArrayList<Integer>();
		ArrayList<Double> f3 = new ArrayList<Double>();
		ArrayList<Integer> o4 = new ArrayList<Integer>();
		ArrayList<Double> f4 = new ArrayList<Double>();

		/* Line 2 */
		ArrayList<String> ibus1 = new ArrayList<String>();
		ArrayList<Integer> type1 = new ArrayList<Integer>();
		ArrayList<Integer> mode1 = new ArrayList<Integer>();
		ArrayList<Double> dcset1 = new ArrayList<Double>();
		ArrayList<Double> acset1 = new ArrayList<Double>();
		ArrayList<Double> aloss1 = new ArrayList<Double>();
		ArrayList<Double> bloss1 = new ArrayList<Double>();
		ArrayList<Double> minloss1 = new ArrayList<Double>();
		ArrayList<Double> smax1 = new ArrayList<Double>();
		ArrayList<Double> imax1 = new ArrayList<Double>();
		ArrayList<Double> pwf1 = new ArrayList<Double>();
		ArrayList<Double> maxq1 = new ArrayList<Double>();
		ArrayList<Double> minq1 = new ArrayList<Double>();
		ArrayList<String> remot1 = new ArrayList<String>();
		ArrayList<Double> rmpct1 = new ArrayList<Double>();

		/* Line 3 */
		ArrayList<String> ibus2 = new ArrayList<String>();
		ArrayList<Integer> type2 = new ArrayList<Integer>();
		ArrayList<Integer> mode2 = new ArrayList<Integer>();
		ArrayList<Double> dcset2 = new ArrayList<Double>();
		ArrayList<Double> acset2 = new ArrayList<Double>();
		ArrayList<Double> aloss2 = new ArrayList<Double>();
		ArrayList<Double> bloss2 = new ArrayList<Double>();
		ArrayList<Double> minloss2 = new ArrayList<Double>();
		ArrayList<Double> smax2 = new ArrayList<Double>();
		ArrayList<Double> imax2 = new ArrayList<Double>();
		ArrayList<Double> pwf2 = new ArrayList<Double>();
		ArrayList<Double> maxq2 = new ArrayList<Double>();
		ArrayList<Double> minq2 = new ArrayList<Double>();
		ArrayList<String> remot2 = new ArrayList<String>();
		ArrayList<Double> rmpct2 = new ArrayList<Double>();

		String line;
		_size = 0;
		System.out.println("Start reading VSC DC line data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;

			/*  line 1  */
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			
			name.add(tokens[idx++]);
			AuxPsseRaw.readOneParam(mdc, tokens, idx++, _defaultMDC);
			rdc.add(Double.parseDouble(tokens[idx++])); 

			AuxPsseRaw.readOneParam(o1, tokens, idx++, 1);
			AuxPsseRaw.readOneParam(f1, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o2, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f2, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o3, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f3, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o4, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f4, tokens, idx++, 1.0);

			if(idx > _nParamL1) {System.err.println("More input data than desired L1 in the element entry "+_size); System.exit(1);}

			/*  line 2  */
			line = reader.readLine();
			tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			idx = 0;

			idxBus1.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			ibus1.add(tokens[idx++]);
			type1.add(Integer.parseInt(tokens[idx++]));
			AuxPsseRaw.readOneParam(mode1, tokens, idx++, _defaultMODE1);
			dcset1.add(Double.parseDouble(tokens[idx++]));
			AuxPsseRaw.readOneParam(acset1, tokens, idx++, _defaultACSET1);
			AuxPsseRaw.readOneParam(aloss1, tokens, idx++, _defaultALOSS1);
			AuxPsseRaw.readOneParam(bloss1, tokens, idx++, _defaultBLOSS1);
			AuxPsseRaw.readOneParam(minloss1, tokens, idx++, _defaultMINLOSS1);
			AuxPsseRaw.readOneParam(smax1, tokens, idx++, _defaultSMAX1);
			AuxPsseRaw.readOneParam(imax1, tokens, idx++, _defaultIMAX1);
			AuxPsseRaw.readOneParam(pwf1, tokens, idx++, _defaultPWF1);
			AuxPsseRaw.readOneParam(minq1, tokens, idx++, _defaultMAXQ1);
			AuxPsseRaw.readOneParam(maxq1, tokens, idx++, _defaultMINQ1);
			AuxPsseRaw.readOneParam(remot1, tokens, idx++, _defaultREMOT1);
			AuxPsseRaw.readOneParam(rmpct1, tokens, idx++, _defaultRMPCT1);
			
			if(idx > _nParamL2) {System.err.println("More input data than desired L2 in the element entry "+_size); System.exit(1);}

			/*  line 3  */
			line = reader.readLine();
			tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			idx = 0;

			idxBus2.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			ibus2.add(tokens[idx++]);
			type2.add(Integer.parseInt(tokens[idx++]));
			AuxPsseRaw.readOneParam(mode2, tokens, idx++, _defaultMODE2);
			dcset2.add(Double.parseDouble(tokens[idx++]));
			AuxPsseRaw.readOneParam(acset2, tokens, idx++, _defaultACSET2);
			AuxPsseRaw.readOneParam(aloss2, tokens, idx++, _defaultALOSS2);
			AuxPsseRaw.readOneParam(bloss2, tokens, idx++, _defaultBLOSS2);
			AuxPsseRaw.readOneParam(minloss2, tokens, idx++, _defaultMINLOSS2);
			AuxPsseRaw.readOneParam(smax2, tokens, idx++, _defaultSMAX2);
			AuxPsseRaw.readOneParam(imax2, tokens, idx++, _defaultIMAX2);
			AuxPsseRaw.readOneParam(pwf2, tokens, idx++, _defaultPWF2);
			AuxPsseRaw.readOneParam(minq2, tokens, idx++, _defaultMAXQ2);
			AuxPsseRaw.readOneParam(maxq2, tokens, idx++, _defaultMINQ2);
			AuxPsseRaw.readOneParam(remot2, tokens, idx++, _defaultREMOT2);
			AuxPsseRaw.readOneParam(rmpct2, tokens, idx++, _defaultRMPCT2);
			
			if(idx > _nParamL3) {System.err.println("More input data than desired L3 in the element entry "+_size); System.exit(1);}
		}
		
		// save data in member variables of this class - field
		_idxBus1 = AuxMethod.convtArrayListToInt(idxBus1);
		_idxBus2 = AuxMethod.convtArrayListToInt(idxBus2);

		/* line 1 */
		_name = AuxMethod.convtArrayListToStr(name);
		_mdc = AuxMethod.convtArrayListToInt(mdc);
		_rdc = AuxMethod.convtArrayListToDouble(rdc);
		_o1 = AuxMethod.convtArrayListToInt(o1);
		_f1 = AuxMethod.convtArrayListToDouble(f1);
		_o2 = AuxMethod.convtArrayListToInt(o2);
		_f2 = AuxMethod.convtArrayListToDouble(f2);
		_o3 = AuxMethod.convtArrayListToInt(o3);
		_f3 = AuxMethod.convtArrayListToDouble(f3);
		_o4 = AuxMethod.convtArrayListToInt(o4);
		_f4 = AuxMethod.convtArrayListToDouble(f4);

		/* line 2 */
		_ibus1 = AuxMethod.convtArrayListToStr(ibus1);
		_type1 = AuxMethod.convtArrayListToInt(type1);
		_mode1 = AuxMethod.convtArrayListToInt(mode1);
		_dcset1 = AuxMethod.convtArrayListToDouble(dcset1);
		_acset1 = AuxMethod.convtArrayListToDouble(acset1);
		_aloss1 = AuxMethod.convtArrayListToDouble(aloss1);
		_bloss1 = AuxMethod.convtArrayListToDouble(bloss1);
		_minloss1 = AuxMethod.convtArrayListToDouble(minloss1);
		_smax1 = AuxMethod.convtArrayListToDouble(smax1);
		_imax1 = AuxMethod.convtArrayListToDouble(imax1);
		_pwf1 = AuxMethod.convtArrayListToDouble(pwf1);
		_maxq1 = AuxMethod.convtArrayListToDouble(maxq1);
		_minq1 = AuxMethod.convtArrayListToDouble(minq1);
		_remot1 = AuxMethod.convtArrayListToStr(remot1);
		_rmpct1 = AuxMethod.convtArrayListToDouble(rmpct1);

		/* line 3 */
		_ibus2 = AuxMethod.convtArrayListToStr(ibus2);
		_type2 = AuxMethod.convtArrayListToInt(type2);
		_mode2 = AuxMethod.convtArrayListToInt(mode2);
		_dcset2 = AuxMethod.convtArrayListToDouble(dcset2);
		_acset2 = AuxMethod.convtArrayListToDouble(acset2);
		_aloss2 = AuxMethod.convtArrayListToDouble(aloss2);
		_bloss2 = AuxMethod.convtArrayListToDouble(bloss2);
		_minloss2 = AuxMethod.convtArrayListToDouble(minloss2);
		_smax2 = AuxMethod.convtArrayListToDouble(smax2);
		_imax2 = AuxMethod.convtArrayListToDouble(imax2);
		_pwf2 = AuxMethod.convtArrayListToDouble(pwf2);
		_maxq2 = AuxMethod.convtArrayListToDouble(maxq2);
		_minq2 = AuxMethod.convtArrayListToDouble(minq2);
		_remot2 = AuxMethod.convtArrayListToStr(remot2);
		_rmpct2 = AuxMethod.convtArrayListToDouble(rmpct2);

		// remove single quotes if any
		AuxMethod.removeBoundarySingleQuotes(_name);
		AuxMethod.removeBoundarySingleQuotes(_ibus1);
		AuxMethod.removeBoundarySingleQuotes(_ibus2);

		// remove double quotes if any
		AuxMethod.removeBoundaryDoubleQuotes(_name);

		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading VSC DC line data");
	}
	
	// data check
	private void checkEntryData()
	{
		if(_size != _name.length) System.exit(1);
		if(_size != _ibus1.length) System.exit(1);
		if(_size != _rdc.length) System.exit(1);
		if(_size != _f4.length) System.exit(1);
		
		if(_size != _remot2.length) System.exit(1);
		if(_size != _remot1.length) System.exit(1);
		if(_size != _o2.length) System.exit(1);
		if(_size != _type2.length) System.exit(1);
	}

}
