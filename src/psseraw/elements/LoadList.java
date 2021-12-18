package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Load entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class LoadList {

	PsseRawModel _model;
	BusList _buses;
	
	int _size;
	int[] _busIdx;    // index of the bus that load connects to
	
	// base value from raw file
	String[] _i, _id;
	
	int[] _status, _area, _zone;
	double[] _pl, _ql, _ip, _iq, _yp, _yq;
	int[] _owner, _scale, _intrpt;
	
	// default value
	final int _nParam = 14; 
	final int _defaultSTATUS = 1, _defaultSCALE = 1, _defaultINTRPT = 0;
	final String _defaultID = "1";
	final double _defaultPL = 0.0, _defaultQL = 0.0, _defaultIP = 0.0, 
			_defaultIQ = 0.0, _defaultYP = 0.0, _defaultYQ = 0.0;

	public LoadList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}
	
	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> busIdx = new ArrayList<Integer>(); // index of associate bus
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<Integer> status = new ArrayList<Integer>();
		ArrayList<Integer> area = new ArrayList<Integer>();
		ArrayList<Integer> zone = new ArrayList<Integer>();
		ArrayList<Double> pl = new ArrayList<Double>();
		ArrayList<Double> ql = new ArrayList<Double>();
		ArrayList<Double> ip = new ArrayList<Double>();
		ArrayList<Double> iq = new ArrayList<Double>();
		ArrayList<Double> yp = new ArrayList<Double>();
		ArrayList<Double> yq = new ArrayList<Double>();
		ArrayList<Integer> owner = new ArrayList<Integer>();
		ArrayList<Integer> scale = new ArrayList<Integer>();
		ArrayList<Integer> intrpt = new ArrayList<Integer>();

		String line;
		_size = 0;
		System.out.println("Start reading load data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0, busIdxTmp = -1;
			busIdxTmp = AuxPsseRaw.determineBusIndex(_buses, tokens, idx);
			busIdx.add(busIdxTmp);
			
			i.add(tokens[idx++]);
			AuxPsseRaw.readOneParam(id, tokens, idx++, _defaultID);
			AuxPsseRaw.readOneParam(status, tokens, idx++, _defaultSTATUS);
			AuxPsseRaw.readOneParam(area, tokens, idx++, _buses.getArea(busIdxTmp));
			AuxPsseRaw.readOneParam(zone, tokens, idx++, _buses.getZone(busIdxTmp));
			AuxPsseRaw.readOneParam(pl, tokens, idx++, _defaultPL);
			AuxPsseRaw.readOneParam(ql, tokens, idx++, _defaultQL);
			AuxPsseRaw.readOneParam(ip, tokens, idx++, _defaultIP);			
			AuxPsseRaw.readOneParam(iq, tokens, idx++, _defaultIQ);
			AuxPsseRaw.readOneParam(yp, tokens, idx++, _defaultYP);
			AuxPsseRaw.readOneParam(yq, tokens, idx++, _defaultYQ);
			AuxPsseRaw.readOneParam(owner, tokens, idx++, _buses.getOwner(busIdxTmp));
			AuxPsseRaw.readOneParam(scale, tokens, idx++, _defaultSCALE);
			AuxPsseRaw.readOneParam(intrpt, tokens, idx++, _defaultINTRPT);
			
			if(idx > _nParam) {System.err.println("More input data than desired in line "+_size); System.exit(1); }
		}

		// save data in member variables of this class - field
		_busIdx = AuxMethod.convtArrayListToInt(busIdx);
		_i = AuxMethod.convtArrayListToStr(i);
		_id = AuxMethod.convtArrayListToStr(id);
		_status = AuxMethod.convtArrayListToInt(status);
		_area = AuxMethod.convtArrayListToInt(area);
		_zone = AuxMethod.convtArrayListToInt(zone);
		_pl = AuxMethod.convtArrayListToDouble(pl);
		_ql = AuxMethod.convtArrayListToDouble(ql);
		_ip = AuxMethod.convtArrayListToDouble(ip);
		_iq = AuxMethod.convtArrayListToDouble(iq);
		_yp = AuxMethod.convtArrayListToDouble(yp);
		_yq = AuxMethod.convtArrayListToDouble(yq);
		_owner = AuxMethod.convtArrayListToInt(owner);
		_scale = AuxMethod.convtArrayListToInt(scale);
		_intrpt = AuxMethod.convtArrayListToInt(intrpt);

		// remove single quotes for _names if any
		AuxMethod.removeBoundarySingleQuotes(_i);
		AuxMethod.removeBoundarySingleQuotes(_id);
		
		checkEntryData();
		System.out.println("   Finish reading load data");
	}

	// data check
	private void checkEntryData()
	{
		if(_size != _busIdx.length) System.exit(1);
		if(_size != _i.length) System.exit(1);
		if(_size != _id.length) System.exit(1);
		if(_size != _status.length) System.exit(1);
		if(_size != _area.length) System.exit(1);
		if(_size != _zone.length) System.exit(1);
		
		if(_size != _pl.length) System.exit(1);
		if(_size != _ql.length) System.exit(1);
		if(_size != _ip.length) System.exit(1);
		if(_size != _iq.length) System.exit(1);
		if(_size != _yp.length) System.exit(1);
		if(_size != _yq.length) System.exit(1);
		
		if(_size != _owner.length) System.exit(1);
		if(_size != _scale.length) System.exit(1);
	}

	public boolean isInSvc(int i) {return (_status[i] == 1) ? true : false;}

}
