package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * GNE device entry data from .raw file.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class GNEList {

	PsseRawModel _model;
	int _size;
	
	public GNEList(PsseRawModel model) {_model = model;}

	public boolean readData(BufferedReader reader) throws IOException
	{
		String line;
		_size = 0;
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			if (_model.loadQRecord(line) == true) return true;
			_size++;
		}
		System.out.println("****** Skip reading GNE device data ******");
		if (_size == 0) System.out.println("    No GNE device data is found");
		else System.out.println("    There are "+_size+" lines representing the GNE device data");
		return false;
	}
	
}
