package psseraw.elements;

import psseraw.PsseRawModel;

/**
 * All 3-winding transformer including phase shifter
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 * 
 */
public class Xfm3W {
	
	PsseRawModel _model;
	Transformer3List _transformers;
	PhaseShifter3List _phaseshifters;
	
	int _nTransformers;
	int _nPhaseShifter;
	int _size;

	
	public Xfm3W(PsseRawModel model) {_model = model; initial();}

	private void initial()
	{
		_transformers = _model.getTransformer3List();
		_phaseshifters = _model.getPhaseShifter3List();

		_nTransformers = _transformers.size();
		_nPhaseShifter = _phaseshifters.size();
		_size = _nTransformers + _nPhaseShifter;
	}
	
	public int getIndxBus(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getIndxBus(ndx);
		else return _phaseshifters.getIndxBus(ndx -_nTransformers);
	}
	
	public int getJndxBus(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getJndxBus(ndx);
		else return _phaseshifters.getJndxBus(ndx -_nTransformers);
	}
	
	public int getKndxBus(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getKndxBus(ndx);
		else return _phaseshifters.getKndxBus(ndx -_nTransformers);
	}
	
	public String getID(int ndx)
	{
		if (ndx < _nTransformers) return _transformers.getID(ndx);
		else return _phaseshifters.getID(ndx -_nTransformers);
	}



}
