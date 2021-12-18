package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * FACTS device entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class FACTSList {

	PsseRawModel _model;
	BusList _buses;
	
	int _size;
	int[] _idxFrmBus;    // index of the sending end bus
	int[] _idxToBus;     // index of the terminal end bus

	// base value from raw file
	String[] _name, _i, _j;
	int[] _mode;
	double[] _pdes, _qdes, _vset, _shmx, _trmx, _vtmn, _vtmx, _vsmx, _imx, _linx, _rmpct;
	
	int[] _owner;
	double[] _set1, _set2;
	int[] _vsref;
	String[] _remot, _mname;
	
	// default value
	final int _nParam = 21; 
	final String _defaultJ = "0", _defaultREMOT = "0", _defaultMNAME = " ";
	final int _defaultMODE = 1, _defaultOWNER = 1, _defaultVSREF = 1;
	final double _defaultPDES = 0.0, _defaultQDES = 0.0, _defaultVSET = 1.0, _defaultSHMX = 9999.0, 
			_defaultTRMX = 9999.0, _defaultVTMN = 0.9, _defaultVTMX = 1.1, _defaultVSMX = 1.0,
			_defaultIMX = 0.0, _defaultLINX = 0.05, _defaultRMPCT = 100.0, _defaultSET1 = 0.0,
			_defaultSET2 = 0.0;

	public FACTSList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> idxFrmBus = new ArrayList<Integer>();
		ArrayList<Integer> idxToBus= new ArrayList<Integer>(); 
		
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> j = new ArrayList<String>();
		ArrayList<Integer> mode = new ArrayList<Integer>();
		ArrayList<Double> pdes = new ArrayList<Double>();
		ArrayList<Double> qdes = new ArrayList<Double>();
		ArrayList<Double> vset = new ArrayList<Double>();
		ArrayList<Double> shmx = new ArrayList<Double>();
		ArrayList<Double> trmx = new ArrayList<Double>();
		ArrayList<Double> vtmn = new ArrayList<Double>();
		ArrayList<Double> vtmx = new ArrayList<Double>();
		ArrayList<Double> vsmx = new ArrayList<Double>();
		ArrayList<Double> imx = new ArrayList<Double>();
		ArrayList<Double> linx = new ArrayList<Double>();
		ArrayList<Double> rmpct = new ArrayList<Double>();
		ArrayList<Integer> owner = new ArrayList<Integer>();
		ArrayList<Double> set1 = new ArrayList<Double>();
		ArrayList<Double> set2 = new ArrayList<Double>();
		ArrayList<Integer> vsref = new ArrayList<Integer>();
		ArrayList<String> remot = new ArrayList<String>();
		ArrayList<String> mname = new ArrayList<String>();

		String line;
		_size = 0;
		System.out.println("Start reading FACTS data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			
			name.add(tokens[idx++]);
			idxFrmBus.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			i.add(tokens[idx++]);
			if (tokens[idx].equals("0")) idxToBus.add(-1);
			else idxToBus.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			AuxPsseRaw.readOneParam(j, tokens, idx++, _defaultJ);

			AuxPsseRaw.readOneParam(mode, tokens, idx++, _defaultMODE);
			AuxPsseRaw.readOneParam(pdes, tokens, idx++, _defaultPDES);
			AuxPsseRaw.readOneParam(qdes, tokens, idx++, _defaultQDES);
			AuxPsseRaw.readOneParam(vset, tokens, idx++, _defaultVSET);
			AuxPsseRaw.readOneParam(shmx, tokens, idx++, _defaultSHMX);
			AuxPsseRaw.readOneParam(trmx, tokens, idx++, _defaultTRMX);
			AuxPsseRaw.readOneParam(vtmn, tokens, idx++, _defaultVTMN);
			AuxPsseRaw.readOneParam(vtmx, tokens, idx++, _defaultVTMX);
			AuxPsseRaw.readOneParam(vsmx, tokens, idx++, _defaultVSMX);
			AuxPsseRaw.readOneParam(imx, tokens, idx++, _defaultIMX);
			AuxPsseRaw.readOneParam(linx, tokens, idx++, _defaultLINX);
			AuxPsseRaw.readOneParam(rmpct, tokens, idx++, _defaultRMPCT);
			AuxPsseRaw.readOneParam(owner, tokens, idx++, _defaultOWNER);
			AuxPsseRaw.readOneParam(set1, tokens, idx++, _defaultSET1);
			AuxPsseRaw.readOneParam(set2, tokens, idx++, _defaultSET2);
			AuxPsseRaw.readOneParam(vsref, tokens, idx++, _defaultVSREF);
			AuxPsseRaw.readOneParam(remot, tokens, idx++, _defaultREMOT);
			AuxPsseRaw.readOneParam(mname, tokens, idx++, _defaultMNAME);
			
			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}
		
		// save data in member variables of this class - field
		_idxFrmBus = AuxMethod.convtArrayListToInt(idxFrmBus);
		_idxToBus = AuxMethod.convtArrayListToInt(idxToBus);
		_name = AuxMethod.convtArrayListToStr(name);
		_i = AuxMethod.convtArrayListToStr(i);
		_j = AuxMethod.convtArrayListToStr(j);
		_mode = AuxMethod.convtArrayListToInt(mode);
		_pdes = AuxMethod.convtArrayListToDouble(pdes);
		_qdes = AuxMethod.convtArrayListToDouble(qdes);
		_vset = AuxMethod.convtArrayListToDouble(vset);
		_shmx = AuxMethod.convtArrayListToDouble(shmx);
		_trmx = AuxMethod.convtArrayListToDouble(trmx);
		_vtmn = AuxMethod.convtArrayListToDouble(vtmn);
		_vtmx = AuxMethod.convtArrayListToDouble(vtmx);
		_vsmx = AuxMethod.convtArrayListToDouble(vsmx);
		_imx = AuxMethod.convtArrayListToDouble(imx);
		_linx = AuxMethod.convtArrayListToDouble(linx);
		_rmpct = AuxMethod.convtArrayListToDouble(rmpct);
		_owner = AuxMethod.convtArrayListToInt(owner);
		_set1 = AuxMethod.convtArrayListToDouble(set1);
		_set2 = AuxMethod.convtArrayListToDouble(set2);
		_vsref = AuxMethod.convtArrayListToInt(vsref);
		_remot = AuxMethod.convtArrayListToStr(remot);
		_mname = AuxMethod.convtArrayListToStr(mname);

		// remove single quotes if any
		AuxMethod.removeBoundarySingleQuotes(_name);
		AuxMethod.removeBoundarySingleQuotes(_i);
		AuxMethod.removeBoundarySingleQuotes(_j);
		AuxMethod.removeBoundarySingleQuotes(_mname);
		
		// remove double quotes if any
		AuxMethod.removeBoundaryDoubleQuotes(_name);

		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading FACTS data");
	}

	// data check
	private void checkEntryData()
	{
		if(_size != _idxFrmBus.length) System.exit(1);
		if(_size != _i.length) System.exit(1);
		if(_size != _mode.length) System.exit(1);
		
		if(_size != _linx.length) System.exit(1);
		if(_size != _owner.length) System.exit(1);
		if(_size != _mname.length) System.exit(1);
	}

}
