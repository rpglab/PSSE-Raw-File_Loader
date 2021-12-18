package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Two-terminal DC transmission line entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class TwoEndDCLineList {

	PsseRawModel _model;
	BusList _buses;
	
	int _size;
	
	int[] _inverterBusIdx;    // index of the inverter bus 
	int[] _converterBusIdx;    // index of the converter bus 

	// base value from raw file
	/* line 1 */
	String[] _name;
	protected int[]	_mdc;
	double[] _rdc, _setvl, _vschd, _vcmod, _rcomp, _delti;
	String[] _meter;
	double[] _dcvmin;
	int[] _cccitmx;
	double[] _cccacc;
	
	/* line 2 */
	String[] _ipr;
	int[] _nbr;
	double[] _anmxr, _anmnr, _rcr, _xcr, _ebasr, _trr, _tapr, _tmxr, _tmnr, _stpr; 
	String[] _icr, _ifr, _itr, _idr;
	double[] _xcapr;
	
	/* line 3 */
	String[] _ipi;
	int[] _nbi;
	double[] _gammx, _gammn, _rci, _xci, _ebasi, _tri, _tapi, _tmxi, _tmni, _stpi; 
	String[] _ici, _ifi, _iti, _idi;
	double[] _xcapi;

	// default values - line 1
	final int _nParamL1 = 12;
	final String _defaultMETER = "I";
	final int _defaultMDC = 0, _defaultCCCITMX = 20;
	final double _defaultVCMOD = 0.0, _defaultRCOMP = 0.0, _defaultDELTI = 0.0,
			_defaultDCVMIN = 0.0, _defaultCCCACC = 1.0;

	// default values - line 2
	final int _nParamL2 = 17;
	final String _defaultICR = "0", _defaultIFR = "0",
			_defaultITR = "0", _defaultIDR = "1";
	final double _defaultTRR = 1.0, _defaultTAPR = 1.0,
			_defaultTMXR = 1.5, _defaultTMNR = 0.51,
			_defaultSTPR = 0.00625, _defaultXCAPR = 0.0;
	
	// default values - line 3
	final int _nParamL3 = 17;
	final String _defaultICI = "0", _defaultIFI = "0",
			_defaultITI = "0", _defaultIDI = "1";
	final double _defaultTRI = 1.0, _defaultTAPI = 1.0,
			_defaultTMXI = 1.5, _defaultTMNI = 0.51,
			_defaultSTPI = 0.00625, _defaultXCAPI = 0.0;

	public TwoEndDCLineList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> inverterBusIdx = new ArrayList<Integer>();
		ArrayList<Integer> converterBusIdx = new ArrayList<Integer>(); 
		
		/* Line 1 */
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<Integer> mdc = new ArrayList<Integer>();
		ArrayList<Double> rdc = new ArrayList<Double>();
		ArrayList<Double> setvl = new ArrayList<Double>();
		ArrayList<Double> vschd = new ArrayList<Double>();
		ArrayList<Double> vcmod = new ArrayList<Double>();
		ArrayList<Double> rcomp = new ArrayList<Double>();
		ArrayList<Double> delti = new ArrayList<Double>();
		ArrayList<String> meter = new ArrayList<String>();
		ArrayList<Double> dcvmin = new ArrayList<Double>();
		ArrayList<Integer> cccitmx = new ArrayList<Integer>();
		ArrayList<Double> cccacc = new ArrayList<Double>();
		
		/* Line 2 */
		ArrayList<String> ipr = new ArrayList<String>();
		ArrayList<Integer> nbr = new ArrayList<Integer>();
		ArrayList<Double> anmxr = new ArrayList<Double>();
		ArrayList<Double> anmnr = new ArrayList<Double>();
		ArrayList<Double> rcr = new ArrayList<Double>();
		ArrayList<Double> xcr = new ArrayList<Double>();
		ArrayList<Double> ebasr = new ArrayList<Double>();
		ArrayList<Double> trr = new ArrayList<Double>();
		ArrayList<Double> tapr = new ArrayList<Double>();
		ArrayList<Double> tmxr = new ArrayList<Double>();
		ArrayList<Double> tmnr = new ArrayList<Double>();
		ArrayList<Double> stpr = new ArrayList<Double>();
		ArrayList<String> icr = new ArrayList<String>();
		ArrayList<String> ifr = new ArrayList<String>();
		ArrayList<String> itr = new ArrayList<String>();
		ArrayList<String> idr = new ArrayList<String>();
		ArrayList<Double> xcapr = new ArrayList<Double>();

		/* Line 3 */
		ArrayList<String> ipi = new ArrayList<String>();
		ArrayList<Integer> nbi = new ArrayList<Integer>();
		ArrayList<Double> gammx = new ArrayList<Double>();
		ArrayList<Double> gammn = new ArrayList<Double>();
		ArrayList<Double> rci = new ArrayList<Double>();
		ArrayList<Double> xci = new ArrayList<Double>();
		ArrayList<Double> ebasi = new ArrayList<Double>();
		ArrayList<Double> tri = new ArrayList<Double>();
		ArrayList<Double> tapi = new ArrayList<Double>();
		ArrayList<Double> tmxi = new ArrayList<Double>();
		ArrayList<Double> tmni = new ArrayList<Double>();
		ArrayList<Double> stpi = new ArrayList<Double>();
		ArrayList<String> ici = new ArrayList<String>();
		ArrayList<String> ifi = new ArrayList<String>();
		ArrayList<String> iti = new ArrayList<String>();
		ArrayList<String> idi = new ArrayList<String>();
		ArrayList<Double> xcapi = new ArrayList<Double>();

		String line;
		_size = 0;
		System.out.println("Start reading Two-Terminal DC line data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;

			/*  line 1  */
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			
			name.add(tokens[idx++]); 
			AuxPsseRaw.readOneParam(mdc, tokens, idx++, _defaultMDC);
			rdc.add(Double.parseDouble(tokens[idx++])); setvl.add(Double.parseDouble(tokens[idx++]));
			vschd.add(Double.parseDouble(tokens[idx++]));
			
			AuxPsseRaw.readOneParam(vcmod, tokens, idx++, _defaultVCMOD);
			AuxPsseRaw.readOneParam(rcomp, tokens, idx++, _defaultRCOMP);
			AuxPsseRaw.readOneParam(delti, tokens, idx++, _defaultDELTI);
			AuxPsseRaw.readOneParam(meter, tokens, idx++, _defaultMETER);
			AuxPsseRaw.readOneParam(dcvmin, tokens, idx++, _defaultDCVMIN);
			AuxPsseRaw.readOneParam(cccitmx, tokens, idx++, _defaultCCCITMX);
			AuxPsseRaw.readOneParam(cccacc, tokens, idx++, _defaultCCCACC);
			
			if(idx > _nParamL1) {System.err.println("More input data than desired L1 in line "+_size); System.exit(1);}

			/*  line 2  */
			line = reader.readLine();
			tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			idx = 0;
			
			inverterBusIdx.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			ipr.add(tokens[idx++]); nbr.add(Integer.parseInt(tokens[idx++]));
			anmxr.add(Double.parseDouble(tokens[idx++]));
			anmnr.add(Double.parseDouble(tokens[idx++]));
			rcr.add(Double.parseDouble(tokens[idx++]));
			xcr.add(Double.parseDouble(tokens[idx++]));
			ebasr.add(Double.parseDouble(tokens[idx++]));

			AuxPsseRaw.readOneParam(trr, tokens, idx++, _defaultTRR);
			AuxPsseRaw.readOneParam(tapr, tokens, idx++, _defaultTAPR);
			AuxPsseRaw.readOneParam(tmxr, tokens, idx++, _defaultTMXR);
			AuxPsseRaw.readOneParam(tmnr, tokens, idx++, _defaultTMNR);
			AuxPsseRaw.readOneParam(stpr, tokens, idx++, _defaultSTPR);
			AuxPsseRaw.readOneParam(icr, tokens, idx++, _defaultICR);
			AuxPsseRaw.readOneParam(ifr, tokens, idx++, _defaultIFR);
			AuxPsseRaw.readOneParam(itr, tokens, idx++, _defaultITR);
			AuxPsseRaw.readOneParam(idr, tokens, idx++, _defaultIDR);
			AuxPsseRaw.readOneParam(xcapr, tokens, idx++, _defaultXCAPR);

			if(idx > _nParamL2) {System.err.println("More input data than desired L2 in line "+_size); System.exit(1);}

			/*  line 3  */
			line = reader.readLine();
			tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			idx = 0;
			
			converterBusIdx.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			ipi.add(tokens[idx++]); nbi.add(Integer.parseInt(tokens[idx++]));
			gammx.add(Double.parseDouble(tokens[idx++]));
			gammn.add(Double.parseDouble(tokens[idx++]));
			rci.add(Double.parseDouble(tokens[idx++]));
			xci.add(Double.parseDouble(tokens[idx++]));
			ebasi.add(Double.parseDouble(tokens[idx++]));

			AuxPsseRaw.readOneParam(tri, tokens, idx++, _defaultTRI);
			AuxPsseRaw.readOneParam(tapi, tokens, idx++, _defaultTAPI);
			AuxPsseRaw.readOneParam(tmxi, tokens, idx++, _defaultTMXI);
			AuxPsseRaw.readOneParam(tmni, tokens, idx++, _defaultTMNI);
			AuxPsseRaw.readOneParam(stpi, tokens, idx++, _defaultSTPI);
			AuxPsseRaw.readOneParam(ici, tokens, idx++, _defaultICI);
			AuxPsseRaw.readOneParam(ifi, tokens, idx++, _defaultIFI);
			AuxPsseRaw.readOneParam(iti, tokens, idx++, _defaultITI);
			AuxPsseRaw.readOneParam(idi, tokens, idx++, _defaultIDI);
			AuxPsseRaw.readOneParam(xcapi, tokens, idx++, _defaultXCAPI);

			if(idx > _nParamL3) {System.err.println("More input data than desired L3 in line "+_size); System.exit(1);}
		}
		
		// save data in member variables of this class - field
		_inverterBusIdx = AuxMethod.convtArrayListToInt(inverterBusIdx);
		_converterBusIdx = AuxMethod.convtArrayListToInt(converterBusIdx);

		/* line 1 */
		_name = AuxMethod.convtArrayListToStr(name);
		_mdc = AuxMethod.convtArrayListToInt(mdc);
		_rdc = AuxMethod.convtArrayListToDouble(rdc);
		_setvl = AuxMethod.convtArrayListToDouble(setvl);
		_vschd = AuxMethod.convtArrayListToDouble(vschd);
		_vcmod = AuxMethod.convtArrayListToDouble(vcmod);
		_rcomp = AuxMethod.convtArrayListToDouble(rcomp);
		_delti = AuxMethod.convtArrayListToDouble(delti);
		_meter = AuxMethod.convtArrayListToStr(meter);
		_dcvmin = AuxMethod.convtArrayListToDouble(dcvmin);
		_cccitmx = AuxMethod.convtArrayListToInt(cccitmx);
		_cccacc = AuxMethod.convtArrayListToDouble(cccacc);

		/* line 2 */
		_ipr = AuxMethod.convtArrayListToStr(ipr);
		_nbr = AuxMethod.convtArrayListToInt(nbr);
		_anmxr = AuxMethod.convtArrayListToDouble(anmxr);
		_anmnr = AuxMethod.convtArrayListToDouble(anmnr);
		_rcr = AuxMethod.convtArrayListToDouble(rcr);
		_xcr = AuxMethod.convtArrayListToDouble(xcr);
		_ebasr = AuxMethod.convtArrayListToDouble(ebasr);
		_trr = AuxMethod.convtArrayListToDouble(trr);
		_tapr = AuxMethod.convtArrayListToDouble(tapr);
		_tmxr = AuxMethod.convtArrayListToDouble(tmxr);
		_tmnr = AuxMethod.convtArrayListToDouble(tmnr);
		_stpr = AuxMethod.convtArrayListToDouble(stpr);
		_icr = AuxMethod.convtArrayListToStr(icr);
		_ifr = AuxMethod.convtArrayListToStr(ifr);
		_itr = AuxMethod.convtArrayListToStr(itr);
		_idr = AuxMethod.convtArrayListToStr(idr);
		_xcapr = AuxMethod.convtArrayListToDouble(xcapr);

		/* line 3 */
		_ipi = AuxMethod.convtArrayListToStr(ipi);
		_nbi = AuxMethod.convtArrayListToInt(nbi);
		_gammx = AuxMethod.convtArrayListToDouble(gammx);
		_gammn = AuxMethod.convtArrayListToDouble(gammn);
		_rci = AuxMethod.convtArrayListToDouble(rci);
		_xci = AuxMethod.convtArrayListToDouble(xci);
		_ebasi = AuxMethod.convtArrayListToDouble(ebasi);
		_tri = AuxMethod.convtArrayListToDouble(tri);
		_tapi = AuxMethod.convtArrayListToDouble(tapi);
		_tmxi = AuxMethod.convtArrayListToDouble(tmxi);
		_tmni = AuxMethod.convtArrayListToDouble(tmni);
		_stpi = AuxMethod.convtArrayListToDouble(stpi);
		_ici = AuxMethod.convtArrayListToStr(ici);
		_ifi = AuxMethod.convtArrayListToStr(ifi);
		_iti = AuxMethod.convtArrayListToStr(iti);
		_idi = AuxMethod.convtArrayListToStr(idi);
		_xcapi = AuxMethod.convtArrayListToDouble(xcapi);
		
		// remove single quotes if any
		AuxMethod.removeBoundarySingleQuotes(_name);
		AuxMethod.removeBoundarySingleQuotes(_ipr);
		AuxMethod.removeBoundarySingleQuotes(_icr);
		AuxMethod.removeBoundarySingleQuotes(_ifr);
		AuxMethod.removeBoundarySingleQuotes(_itr);
		AuxMethod.removeBoundarySingleQuotes(_ipi);
		AuxMethod.removeBoundarySingleQuotes(_ici);
		AuxMethod.removeBoundarySingleQuotes(_ifi);
		AuxMethod.removeBoundarySingleQuotes(_iti);

		// remove double quotes if any
		AuxMethod.removeBoundaryDoubleQuotes(_name);

		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading Two-Terminal DC line data");
	}
	
	// data check
	private void checkEntryData()
	{
		if(_size != _name.length) System.exit(1);
		if(_size != _ipr.length) System.exit(1);
		if(_size != _rdc.length) System.exit(1);
		if(_size != _ifi.length) System.exit(1);
		
		if(_size != _xcapr.length) System.exit(1);
		if(_size != _stpi.length) System.exit(1);
		if(_size != _xcapi.length) System.exit(1);
		if(_size != _ebasr.length) System.exit(1);
	}


}
