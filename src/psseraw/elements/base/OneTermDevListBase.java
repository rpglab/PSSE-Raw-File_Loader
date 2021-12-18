package psseraw.elements.base;

import psseraw.PsseRawModel;
import psseraw.elements.BusList;

public class OneTermDevListBase {
	
	protected PsseRawModel _model;
	protected BusList _buses;
	
	protected int _size;
	protected int[] _busIdx;    // index of the bus that elements connects to
	protected int _stat[];

	protected int[] _busNumber;      // bus number in raw file before reindex
	protected String[] _cktSimple;   // remove any blanks/taps in the beginning and ending parts.

	
	public OneTermDevListBase(PsseRawModel model) {_model = model; _buses = _model.getBusList();}
	
	/** Get bus index. */
	public int getBusIdx(int i) {return _busIdx[i];}

	public boolean isInSvc(int i) {return (_stat[i] == 1) ? true : false;}
	public int size() {return _size;}

	protected void calcBusNumberRaw()
	{
		_busNumber = new int[_size];
		for (int i=0; i<_size; i++)
			_busNumber[i] = _buses.getI(_busIdx[i]);
	}

	/** Get bus number. */
	public int[] getBusNumber()
	{
		if (_busNumber == null) calcBusNumberRaw();
		return _busNumber;
	}
	
	/** Get device index in its list */
	public int getIdx(int busNumber)
	{
		if (_busNumber == null) calcBusNumberRaw();
		for (int i=0; i<_size; i++)
			if (_busNumber[i] == busNumber) return i;
		return -1;
	}

	
}
