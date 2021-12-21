package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;
import psseraw.elements.base.OneTermDevListBase;

/**
 * 
 * Generator entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class GenList extends OneTermDevListBase {

//	PsseRaw _model;
//	BusList _buses;
//	int _size;
	
//	int[] _busIdx;    // index of the bus that generator connects to

	// base value from raw file
	String[] _i, _id;
	double _pg[],_qg[],_qt[],_qb[],_vs[];
	String _ireg[];
	double _mbase[],_zr[],_zx[],_rt[],_xt[],_gtap[];
//	int _stat[];
	double _rmpct[],_pt[],_pb[];
	int[] _o1, _o2, _o3, _o4;
	double[] _f1, _f2, _f3, _f4;
	int[] _wmod;
	double[] _wpf;

	// default value
	final int _nParam = 28; 
	final String _defaultID = "1", _defaultIREG = "0";
	final int _defaultSTAT = 1, _defaultWMOD = 0;
	final double _defaultMBASE = RawParam.getSystemBaseMVA(), _defaultPG = 0.0, _defaultQG = 0.0, _defaultQT = 9999.0, _defaultQB = -9999.0, 
			_defaultVS = 1.0, _defaultZR = 0.0, _defaultZX = 1.0, 
			_defaultRT = 0.0, _defaultXT = 0.0, _defaultGTAP = 1.0, 
			_defaultRMPCT = 100.0, _defaultPT = 9999.0, _defaultPB = -9999.0, _defaultWPF = 1.0;

	public GenList(PsseRawModel model) {super(model);}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> busIdx = new ArrayList<Integer>(); // index of associate bus
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<Double> pg = new ArrayList<Double>();
		ArrayList<Double> qg = new ArrayList<Double>();
		ArrayList<Double> qt = new ArrayList<Double>();
		ArrayList<Double> qb = new ArrayList<Double>();
		ArrayList<Double> vs = new ArrayList<Double>();
		ArrayList<String> ireg = new ArrayList<String>();
		ArrayList<Double> mbase = new ArrayList<Double>();
		ArrayList<Double> zr = new ArrayList<Double>();
		ArrayList<Double> zx = new ArrayList<Double>();
		ArrayList<Double> rt = new ArrayList<Double>();
		ArrayList<Double> xt = new ArrayList<Double>();
		ArrayList<Double> gtap = new ArrayList<Double>();
		ArrayList<Integer> status = new ArrayList<Integer>();
		ArrayList<Double> rmpct = new ArrayList<Double>();
		ArrayList<Double> pt = new ArrayList<Double>();
		ArrayList<Double> pb = new ArrayList<Double>();
		ArrayList<Integer> o1 = new ArrayList<Integer>();
		ArrayList<Double> f1 = new ArrayList<Double>();
		ArrayList<Integer> o2 = new ArrayList<Integer>();
		ArrayList<Double> f2 = new ArrayList<Double>();
		ArrayList<Integer> o3 = new ArrayList<Integer>();
		ArrayList<Double> f3 = new ArrayList<Double>();
		ArrayList<Integer> o4 = new ArrayList<Integer>();
		ArrayList<Double> f4 = new ArrayList<Double>();
		ArrayList<Integer> wmod = new ArrayList<Integer>();
		ArrayList<Double> wpf = new ArrayList<Double>();

		String line;
		_size = 0;
		System.out.println("Start reading generator data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			int busIdxTmp = AuxPsseRaw.determineBusIndex(_buses, tokens, idx);
			busIdx.add(busIdxTmp);
			
			i.add(tokens[idx++]);
			AuxPsseRaw.readOneParam(id, tokens, idx++, _defaultID);
			AuxPsseRaw.readOneParam(pg, tokens, idx++, _defaultPG);
			AuxPsseRaw.readOneParam(qg, tokens, idx++, _defaultQG);
			AuxPsseRaw.readOneParam(qt, tokens, idx++, _defaultQT);
			AuxPsseRaw.readOneParam(qb, tokens, idx++, _defaultQB);
			AuxPsseRaw.readOneParam(vs, tokens, idx++, _defaultVS);
			AuxPsseRaw.readOneParam(ireg, tokens, idx++, _defaultIREG);
			AuxPsseRaw.readOneParam(mbase, tokens, idx++, _defaultMBASE);
			AuxPsseRaw.readOneParam(zr, tokens, idx++, _defaultZR);
			AuxPsseRaw.readOneParam(zx, tokens, idx++, _defaultZX);
			AuxPsseRaw.readOneParam(rt, tokens, idx++, _defaultRT);
			AuxPsseRaw.readOneParam(xt, tokens, idx++, _defaultXT);
			AuxPsseRaw.readOneParam(gtap, tokens, idx++, _defaultGTAP);
			AuxPsseRaw.readOneParam(status, tokens, idx++, _defaultSTAT);
			AuxPsseRaw.readOneParam(rmpct, tokens, idx++, _defaultRMPCT);
			AuxPsseRaw.readOneParam(pt, tokens, idx++, _defaultPT);
			AuxPsseRaw.readOneParam(pb, tokens, idx++, _defaultPB);
			AuxPsseRaw.readOneParam(o1, tokens, idx++, _buses.getOwner(busIdxTmp));
			AuxPsseRaw.readOneParam(f1, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o2, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f2, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o3, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f3, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o4, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f4, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(wmod, tokens, idx++, _defaultWMOD);
			AuxPsseRaw.readOneParam(wpf, tokens, idx++, _defaultWPF);
			
			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}

		// save data in member variables of this class - field
		_busIdx = AuxMethod.convtArrayListToInt(busIdx);
		_i = AuxMethod.convtArrayListToStr(i);
		_id = AuxMethod.convtArrayListToStr(id);
		_pg = AuxMethod.convtArrayListToDouble(pg);
		_qg = AuxMethod.convtArrayListToDouble(qg);
		_qt = AuxMethod.convtArrayListToDouble(qt);
		_qb = AuxMethod.convtArrayListToDouble(qb);
		_vs = AuxMethod.convtArrayListToDouble(vs);
		_ireg = AuxMethod.convtArrayListToStr(ireg);
		_mbase = AuxMethod.convtArrayListToDouble(mbase);
		_zr = AuxMethod.convtArrayListToDouble(zr);
		_zx = AuxMethod.convtArrayListToDouble(zx);
		_rt = AuxMethod.convtArrayListToDouble(rt);
		_xt = AuxMethod.convtArrayListToDouble(xt);
		_gtap = AuxMethod.convtArrayListToDouble(gtap);
		_stat = AuxMethod.convtArrayListToInt(status);
		_rmpct = AuxMethod.convtArrayListToDouble(rmpct);
		_pt = AuxMethod.convtArrayListToDouble(pt);
		_pb = AuxMethod.convtArrayListToDouble(pb);
		_o1 = AuxMethod.convtArrayListToInt(o1);
		_o2 = AuxMethod.convtArrayListToInt(o2);
		_o3 = AuxMethod.convtArrayListToInt(o3);
		_o4 = AuxMethod.convtArrayListToInt(o4);
		_f1 = AuxMethod.convtArrayListToDouble(f1);
		_f2 = AuxMethod.convtArrayListToDouble(f2);
		_f3 = AuxMethod.convtArrayListToDouble(f3);
		_f4 = AuxMethod.convtArrayListToDouble(f4);
		_wmod = AuxMethod.convtArrayListToInt(wmod);
		_wpf = AuxMethod.convtArrayListToDouble(wpf);

		// remove single quotes for _names if any
		AuxMethod.removeBoundarySingleQuotes(_i);
		AuxMethod.removeBoundarySingleQuotes(_id);

		checkEntryData();
		System.out.println("   Finish reading generator data");
	}

	// data check
	private void checkEntryData()
	{
		if(_size != _busIdx.length) System.exit(1);
		if(_size != _i.length) System.exit(1);
		if(_size != _id.length) System.exit(1);
		if(_size != _rmpct.length) System.exit(1);
		
		if(_size != _o4.length) System.exit(1);
		if(_size != _f4.length) System.exit(1);
		if(_size != _wmod.length) System.exit(1);
		if(_size != _wpf.length) System.exit(1);
	}

	public double getPg(int i) {return _pg[i];}
	public double getPmax(int i) {return _pt[i];}
	public double getPmin(int i) {return _pb[i];}
	
	public double getQg(int i) {return _pg[i];}
	public double getQmax(int i) {return _qt[i];}
	public double getQmin(int i) {return _qb[i];}
	
}
