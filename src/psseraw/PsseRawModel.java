package psseraw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import psseraw.elements.AreaInterchangeList;
import psseraw.elements.BusList;
import psseraw.elements.CaseIdentificationData;
import psseraw.elements.FACTSList;
import psseraw.elements.FixedBusShuntList;
import psseraw.elements.GNEList;
import psseraw.elements.GenList;
import psseraw.elements.InductionMachineList;
import psseraw.elements.InterareaTransferList;
import psseraw.elements.LineList;
import psseraw.elements.LoadList;
import psseraw.elements.MultiEndDCLineList;
import psseraw.elements.MultiSectionLineGrpList;
import psseraw.elements.OwnerList;
import psseraw.elements.PhaseShifter2List;
import psseraw.elements.PhaseShifter3List;
import psseraw.elements.SwitchedShuntList;
import psseraw.elements.Transformer2List;
import psseraw.elements.Transformer3List;
import psseraw.elements.TransformerPre;
import psseraw.elements.TwoEndDCLineList;
import psseraw.elements.VSCDCLineList;
import psseraw.elements.Xfm2W;
import psseraw.elements.Xfm3W;
import psseraw.elements.XfmImpdCrctList;
import psseraw.elements.ZoneList;

/**
 * This program is to read PSS/E raw file. 
 * So far, it only supports versions 32 and 33.
 * Also, version number must be manually specified to ensure data are correctly loaded.
 *  
 * Default values will be used when some data are not available.
 * IDs, Names may contain blank space.
 * 
 * @author Xingpeng Li (xplipower@gmail.com)
 *
 */
public class PsseRawModel {
	
	String _filePath;
	int _version = 33;    // if psse raw file version is not specified, use the default version 33.
	
	/* Auxiliary */
	Xfm2W _xfm2WList;  // all 2-winding transformer, include phase shifter
	Xfm3W _xfm3WList;  // all 3-winding transformer, include phase shifter
	
	/* data in raw file */
	CaseIdentificationData _caseIdtf;
	BusList _busList;
	LoadList _loadList;
	FixedBusShuntList _fbShuntList;
	GenList _genList;
	LineList _lineList;
	Transformer2List _xfm2List;
	Transformer3List _xfm3List;
	PhaseShifter2List _ps2List;
	PhaseShifter3List _ps3List;
	AreaInterchangeList _areaInterChangeList;
	TwoEndDCLineList _2TermDCLineList;
	VSCDCLineList _vscDCLineList;
	XfmImpdCrctList _xfmImpdCrctList;
	MultiEndDCLineList _mTermDCLineList;
	MultiSectionLineGrpList _mSctLineGroupingList;
	ZoneList _zoneList;
	InterareaTransferList _interAreaTransferList;
	OwnerList _ownerList;
	FACTSList _factsList;
	SwitchedShuntList _swShuntList;
	GNEList _gneList;
	InductionMachineList _indMachineList;
	
	/**
	 *  Example to create an instance:
	 *		String absPathRaw = "C:\\Users\\xli83\\Workspace\\Java_Projects\\PSSE-Raw_Loader\\PSSE_Raw_Loader\\input\\raw\\IEEE39busPsse33.raw";
	 *		PsseRawModel model = new PsseRawModel(absPathRaw);
	 *  Or, use relative path
	 *		String relPathRaw = "input\\raw\\IEEE39busPsse33.raw"; 
     *            // or: "input/raw/IEEE39busPsse33.raw" 
	 *		PsseRawModel model = new PsseRawModel(relPathRaw);
	 * 
	 * @param absFilePath
	 */
	public PsseRawModel(String filePath)
	{
		_filePath = filePath;
	}
	
	public PsseRawModel(String filePath, int version)
	{
		_version = version;
		_filePath = filePath;
	}
	
	public void launch()
	{
		loadRawFile();
		initial();
	}
	
	private void initial()
	{
		_xfm2WList = new Xfm2W(this);
		_xfm3WList = new Xfm3W(this);
	}
	
	TransformerPre _xfmList;
	private void loadRawFile()
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(_filePath));
			System.out.println("Start loading PSS/E raw file, version " + _version);
			/* the order of the following data loading process matters,
			 * should be the same with the raw file */
			loadCaseIdentificationInfo(reader);
			loadBusList(reader);
			loadLoadList(reader);
			loadFBShuntList(reader);
			loadGenList(reader);
			loadLineList(reader);
			loadXfmList(reader);
			loadAreaInterchangeList(reader);
			loadTwoEndDCLineList(reader);
			loadVSCDCLine(reader);
			loadXfmImpdCrctList(reader);
			loadMultiEndDCLineList(reader);
			loadMultiSectionLineGrpList(reader);
			loadZoneList(reader);
			loadInterareaTransferList(reader);
			loadOwnerList(reader);
			loadFACTSList(reader);
			loadSwitchedShuntList(reader);
			
			if(loadGNEList(reader) == true) {reader.close(); return;}
			if (_version == 33)
				if (loadIndMachineList(reader) == true) {reader.close(); return;}
			loadQRecord_Last(reader);
			reader.close();
		} catch (IOException e) {
			System.out.println("Fail to read PSS/E raw file, version " + _version + "\n" + e);
			e.printStackTrace();
		}
	}

	private void loadCaseIdentificationInfo(BufferedReader reader) throws IOException
	{
		_caseIdtf = new CaseIdentificationData(this);
		_caseIdtf.readData(reader);
	}

	private void loadBusList(BufferedReader reader) throws IOException
	{
		_busList = new BusList(this);
		_busList.readData(reader);
	}
	
	private void loadLoadList(BufferedReader reader) throws IOException
	{
		_loadList = new LoadList(this);
		_loadList.readData(reader);
	}
	
	private void loadFBShuntList(BufferedReader reader) throws IOException
	{
		_fbShuntList = new FixedBusShuntList(this);
		_fbShuntList.readData(reader);
	}
	
	private void loadGenList(BufferedReader reader) throws IOException
	{
		_genList = new GenList(this);
		_genList.readData(reader);
	}
	
	private void loadLineList(BufferedReader reader) throws IOException
	{
		_lineList = new LineList(this);
		_lineList.readData(reader);
	}
	
	private void loadXfmList(BufferedReader reader) throws IOException
	{
		TransformerPre xfmList = new TransformerPre(this);
		xfmList.readData(reader);
		// _xfmList = xfmList;
	}

	private void loadAreaInterchangeList(BufferedReader reader) throws IOException
	{
		_areaInterChangeList = new AreaInterchangeList(this);
		_areaInterChangeList.readData(reader);
	}

	private void loadTwoEndDCLineList(BufferedReader reader) throws IOException
	{
		_2TermDCLineList = new TwoEndDCLineList(this);
		_2TermDCLineList.readData(reader);
	}

	private void loadVSCDCLine(BufferedReader reader) throws IOException
	{
		_vscDCLineList = new VSCDCLineList(this);
		_vscDCLineList.readData(reader);
	}
	
	private void loadXfmImpdCrctList(BufferedReader reader) throws IOException
	{
		_xfmImpdCrctList = new XfmImpdCrctList(this);
		_xfmImpdCrctList.readData(reader);
	}
	
	private void loadMultiEndDCLineList(BufferedReader reader) throws IOException
	{
		_mTermDCLineList = new MultiEndDCLineList(this);
		_mTermDCLineList.readData(reader);
	}
	
	private void loadMultiSectionLineGrpList(BufferedReader reader) throws IOException
	{
		_mSctLineGroupingList = new MultiSectionLineGrpList(this);
		_mSctLineGroupingList.readData(reader);
	}
	
	private void loadZoneList(BufferedReader reader) throws IOException
	{
		_zoneList = new ZoneList(this);
		_zoneList.readData(reader);
	}
	
	private void loadInterareaTransferList(BufferedReader reader) throws IOException
	{
		_interAreaTransferList = new InterareaTransferList(this);
		_interAreaTransferList.readData(reader);
	}
	
	private void loadOwnerList(BufferedReader reader) throws IOException
	{
		_ownerList = new OwnerList(this);
		_ownerList.readData(reader);
	}
	
	private void loadFACTSList(BufferedReader reader) throws IOException
	{
		_factsList = new FACTSList(this);
		_factsList.readData(reader);
	}

	private void loadSwitchedShuntList(BufferedReader reader) throws IOException
	{
		_swShuntList = new SwitchedShuntList(this);
		_swShuntList.readData(reader);
	}

	private boolean loadGNEList(BufferedReader reader) throws IOException
	{
		_gneList = new GNEList(this);
		return _gneList.readData(reader);
	}

	private boolean loadIndMachineList(BufferedReader reader) throws IOException
	{
		_indMachineList = new InductionMachineList(this);
		return _indMachineList.readData(reader);
	}
	
	private boolean loadQRecord_Last(BufferedReader reader) throws IOException
	{
		String line = reader.readLine();
		return loadQRecord_Last(line);
	}
	
	private boolean loadQRecord_Last(String line) throws IOException
	{
		boolean endInput = (line.charAt(0) == 'Q') ? true : false;
		if (endInput == true) System.out.println("Q record is identified, (PSS/E raw file) data input process is finished");
		else System.out.println("Q record is missing, but (PSS/E raw file) data input process stoped anyway");
		return endInput;
	}
	
	public boolean loadQRecord(String line) throws IOException
	{
		boolean endInput = (line.charAt(0) == 'Q') ? true : false;
		if (endInput == true) System.out.println("Q record is identified, (PSS/E raw file) data input process is finished");
		return endInput;
	}
	
	public BusList getBusList() {return _busList;}
	public LoadList getLoadList() {return _loadList;}
	public FixedBusShuntList getFixedBusShuntList() {return _fbShuntList;}
	public GenList getGenList() {return _genList;}
	public LineList getLineList() {return _lineList;}
	public Transformer2List getTransformer2List() {return _xfm2List;}
	public Transformer3List getTransformer3List() {return _xfm3List;}
	public PhaseShifter2List getPhaseShifter2List() {return _ps2List;}
	public PhaseShifter3List getPhaseShifter3List() {return _ps3List;}
	public AreaInterchangeList getAreaInterchangeList() {return _areaInterChangeList;}
	public TwoEndDCLineList getTwoEndDCLineList() {return _2TermDCLineList;}
	public VSCDCLineList getVSCDCLineList() {return _vscDCLineList;}
	public XfmImpdCrctList getXfmImpdCrctList() {return _xfmImpdCrctList;}
	public MultiEndDCLineList getMultiEndDCLineList() {return _mTermDCLineList;}
	public MultiSectionLineGrpList getMultiSectionLineGrpList() {return _mSctLineGroupingList;}
	public ZoneList getZoneList() {return _zoneList;}
	public InterareaTransferList getInterareaTransferList() {return _interAreaTransferList;}
	public OwnerList getOwnerList() {return _ownerList;}
	public FACTSList getFACTSList() {return _factsList;}
	public SwitchedShuntList getSwitchedShuntList() {return _swShuntList;}
	public GNEList getGNEList() {return _gneList;}
	
	public void setTransformer2List(Transformer2List xfm2List) {_xfm2List = xfm2List;}
	public void setTransformer3List(Transformer3List xfm3List) {_xfm3List = xfm3List;}
	public void setPhaseShifter2List(PhaseShifter2List xfm2List) {_ps2List = xfm2List;}
	public void setPhaseShifter3List(PhaseShifter3List xfm3List) {_ps3List = xfm3List;}

	public Xfm2W getXfm2WList() {return _xfm2WList;}
	public Xfm3W getXfm3WList() {return _xfm3WList;}
	
	public static void main(String[] argc)
	{
		/* Read PSSE raw file (version 32 or 33). */
		String path = "input\\raw\\IEEE14busPsse33.raw";
		//String path = "input/raw/IEEE14busPsse33.raw";
		//String path = "C:\\Users\\xli83\\Workspace\\Java_Projects\\PSSE-Raw_Loader\\PSSE_Raw_Loader\\input\\raw\\IEEE14busPsse33.raw";
		int versionPSSE = 33;
		PsseRawModel model = new PsseRawModel(path, versionPSSE);
		model.launch();
		System.out.println("Program ends here..");
	}
	
}
