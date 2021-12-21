package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

/**
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class InductionMachineList {

	PsseRawModel _model;
	int _size;

	public InductionMachineList(PsseRawModel model) {_model = model;}

	public boolean readData(BufferedReader reader) throws IOException
	{
		String line;
		_size = 0;
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			if (_model.loadQRecord(line) == true) return true;
			_size++;
		}
		String device = "Induction Machine device";
		System.out.println("****** Skip reading " + device + " data ******");
		if (_size == 0) System.out.println("    No " + device + " data is found");
		else System.out.println("    There are "+_size+" lines representing the "+ device + " data");
		return false;
	}

}
