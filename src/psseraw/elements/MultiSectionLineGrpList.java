package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Multi-section line grouping entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class MultiSectionLineGrpList {

	PsseRawModel _model;
	BusList _buses;

	int _size;
	int[] _idxFrmBus;
	int[] _idxToBus;
	
	// base value from raw file
	String[] _i, _j,_id;
	int[] _met;
	ArrayList<String[]> _dumBus;
	ArrayList<int[]> _dumBusIdx;
	int[] _sizeDumBus;
	
	// default value
	final int _nParam = 13;
	final String _defaultID = "&1";
	final int _defaultMET = 1;

	public MultiSectionLineGrpList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Integer> idxFrmBus = new ArrayList<Integer>();
		ArrayList<Integer> idxToBus = new ArrayList<Integer>();

		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> j = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<Integer> met = new ArrayList<Integer>();
		ArrayList<String[]> dumBus = new ArrayList<String[]>();
		ArrayList<Integer> sizeDumBus = new ArrayList<Integer>();
		ArrayList<int[]> dumBusIdx = new ArrayList<int[]>();

		String line;
		_size = 0;
		System.out.println("Start reading multi-section line grouping data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			
			idxFrmBus.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			i.add(tokens[idx++]);
			idxToBus.add(AuxPsseRaw.determineBusIndex(_buses, tokens, idx));
			j.add(tokens[idx++]);
			AuxPsseRaw.readOneParam(id, tokens, idx++, _defaultID);
			AuxPsseRaw.readOneParam(met, tokens, idx++, _defaultMET);
			
			int numDum = tokens.length - idx;
			sizeDumBus.add(numDum);
			String[] tmpStr = new String[numDum];
			int[] tmpInt = new int[numDum];
			for (int ndx=0; ndx<numDum; ndx++)
			{
				tmpStr[ndx] = tokens[ndx+idx];
				tmpInt[ndx] = AuxPsseRaw.determineBusIndex(_buses, tmpStr[ndx]);
			}
			dumBus.add(tmpStr); dumBusIdx.add(tmpInt);
		}
		
		// save data in member variables of this class - field
		_idxFrmBus = AuxMethod.convtArrayListToInt(idxFrmBus);
		_idxToBus = AuxMethod.convtArrayListToInt(idxToBus);
		_i = AuxMethod.convtArrayListToStr(i);
		_j = AuxMethod.convtArrayListToStr(j);
		_id = AuxMethod.convtArrayListToStr(id);
		_met = AuxMethod.convtArrayListToInt(met);
		_sizeDumBus = AuxMethod.convtArrayListToInt(sizeDumBus);
		_dumBus = dumBus;
		_dumBusIdx = dumBusIdx;

		// remove single quotes if any
		AuxMethod.removeBoundarySingleQuotes(_i);
		AuxMethod.removeBoundarySingleQuotes(_j);
		AuxMethod.removeBoundarySingleQuotes(_id);

		if (_size != 0) checkEntryData();
		System.out.println("   Finish reading multi-section line grouping data");
	}
	
	// data check
	private void checkEntryData()
	{
		if(_size != _i.length) System.exit(1);
		if(_size != _j.length) System.exit(1);
		if(_size != _id.length) System.exit(1);
		if(_size != _met.length) System.exit(1);
		if(_size != _sizeDumBus.length) System.exit(1);
		if(_size != _dumBus.size()) System.exit(1);
		if(_size != _dumBusIdx.size()) System.exit(1);
	}

}
