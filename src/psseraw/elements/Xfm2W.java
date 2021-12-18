package psseraw.elements;

import java.util.ArrayList;

import psseraw.PsseRawModel;

/**
 * All 2-winding transformer including phase shifter
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 * 
 */
public class Xfm2W {
	
	PsseRawModel _model;
	Transformer2List _transformers;
	PhaseShifter2List _phaseshifters;
	
	int _nTransformers;
	int _nPhaseShifter;
	int _size;

	int[] _frmBusNumber;   // from bus number before reindex, phase shifter is included
	int[] _toBusNumber;    // to bus number before reindex, phase shifter is included
	String[] _circuitID;      
	
	ArrayList<String> _TAName;            // Winding 1 / Terminal / Station / Name
	ArrayList<String> _TBName;            // Winding 2 / Terminal / Station / Name
	ArrayList<String> _EMSID;             // ISO EMS ID

	public Xfm2W(PsseRawModel model) {_model = model; initial();}

	private void initial()
	{
		_transformers = _model.getTransformer2List();
		_phaseshifters = _model.getPhaseShifter2List();

		_nTransformers = _transformers.size();
		_nPhaseShifter = _phaseshifters.size();
		_size = _nTransformers + _nPhaseShifter;
	}
	
	/** Get from bus index */
	public int getIdxFrmBus(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getIdxFrmBus(ndx);
		else return _phaseshifters.getIdxFrmBus(ndx -_nTransformers);
	}
	
	/** Get to bus index */
	public int getIdxToBus(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getIdxToBus(ndx);
		else return _phaseshifters.getIdxToBus(ndx -_nTransformers);
	}
	
	/** Get controled bus index */
	public int getControledBusIdx(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getControledBusIdx(ndx);
		else return _phaseshifters.getControledBusIdx(ndx -_nTransformers);
	}

	/** Get Branch ID */
	public String getID(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getID(ndx);
		else return _phaseshifters.getID(ndx -_nTransformers);
	}
	
	public int size() {return _size;}
	
	/** from bus number before reindex */
	public int[] getFrmBuses() 
	{
		if (_frmBusNumber == null) calcXfmArray();
		return _frmBusNumber;
	}
	
	/** to bus number before reindex */
	public int[] getToBuses() 
	{
		if (_toBusNumber == null) calcXfmArray();
		return _toBusNumber;
	}
	
	public String[] getID() 
	{
		if (_circuitID == null) calcXfmArray();
		return _circuitID;
	}

	private void calcXfmArray()
	{
		BusList busesRaw = _model.getBusList();
		_frmBusNumber = new int[_size];
		_toBusNumber = new int[_size];
		_circuitID = new String[_size];
		for (int i=0; i<_size; i++)
		{
			_frmBusNumber[i] = busesRaw.getI(getIdxFrmBus(i));
			_toBusNumber[i] = busesRaw.getI(getIdxToBus(i));
			_circuitID[i] = getID(i);
		}
	}
	
	public int getCW(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getCW(ndx);
		else return _phaseshifters.getCW(ndx -_nTransformers);
	}
	
	public double getNOMV1(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getNOMV1(ndx);
		else return _phaseshifters.getNOMV1(ndx -_nTransformers);
	}
	
	public int getCOD1(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getCOD1(ndx);
		else return _phaseshifters.getCOD1(ndx -_nTransformers);
	}
	
}
