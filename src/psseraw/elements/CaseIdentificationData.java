package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;

import psseraw.PsseRawModel;

/**
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *         Website: https://rpglab.github.io/
 *
 */
public class CaseIdentificationData {

	PsseRawModel _model;

	String _headingL1; // heading line 1
	String _headingL2; // heading line 2
	String _headingL3; // heading line 3
	
	public CaseIdentificationData(PsseRawModel model) {_model = model;}
	
	public void readData(BufferedReader reader) throws IOException
	{
		System.out.println("Start reading case identification data");
		_headingL1 = reader.readLine();
		_headingL2 = reader.readLine();
		_headingL3 = reader.readLine();
		System.out.println("   Finish reading case identification data");
	}
	
	
}
