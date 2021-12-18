package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * Multi-terminal DC transmission Line entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class MultiEndDCLineList {

	PsseRawModel _model;
	BusList _buses;

	int _size;
	
	// base value from raw file

	//TODO: this class 
	
	public MultiEndDCLineList(PsseRawModel model) {_model = model; _buses = _model.getBusList();}

	public void readData(BufferedReader reader) throws IOException
	{
		//TODO:
		
		String line;
		_size = 0;
//		System.out.println("Start reading transformer impedance correction table data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			_size++;
			
		}
		
		System.out.println("****** Skip reading Multi-terminal DC transmission Line entry data ******");
		if (_size == 0) System.out.println("    No Multi-terminal DC transmission line data is found");
		else System.out.println("    There are "+_size+" lines representing the Multi-terminal DC transmission line data");
	}
}
