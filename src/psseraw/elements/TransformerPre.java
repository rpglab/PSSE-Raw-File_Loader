package psseraw.elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import psseraw.PsseRawModel;
import psseraw.elements.AuxMethod;

public class TransformerPre {

	Transformer2List _xfm2;
	Transformer3List _xfm3;
	
	int _sizeXfm2;
	int _sizeXfm3;
	
	boolean[] _isXfmPhaseShifter; // is this transformer a phase shifter

	PsseRawModel _model;
	BusList _buses;

	public TransformerPre(PsseRawModel model) {_model = model; _buses = _model.getBusList();}
	
	boolean[] _is3Xfm;
	
	// default value - line 1
	final int _nParamL1 = 21;
	final String _defaultK = "0", _defaultCKT = "1", _defaultNAME = "            ", _defaultVECGRP = "            ";
	final int _defaultCW = 1, _defaultCZ = 1, _defaultCM = 1, _defaultNMETR = 2, 
			_defaultSTAT = 1;
	final double _defaultMAG1 = 0.0, _defaultMAG2 = 0.0;

	// default value - line 2
	final int _nParamL2 = 11;
	final double _defaultR12 = 0.0, _defaultSBASE12 = RawParam.getSystemBaseMVA(), 
			_defaultR23 = 0.0, _defaultSBASE23 = RawParam.getSystemBaseMVA(), 
			_defaultR31 = 0.0, _defaultSBASE31 = RawParam.getSystemBaseMVA(), 
			_defaultVMSTAR = 1.0, _defaultANSTAR = 0.0;

	// default value - line 3
	final int _nParamL3 = 17;
	final double _defaultNOMV1 = 0.0, _defaultANG1 = 0.0, 
			_defaultRATA1 = 0.0, _defaultRATB1 = 0.0, _defaultRATC1 = 0.0, 
			_defaultCR1 = 0.0, _defaultCX1 = 0.0, _defaultCNXA1 = 0.0;
	final int _defaultCOD1 = 0, _defaultNTP1 = 33, _defaultTAB1 = 0;
	final String _defaultCONT1 = "0";
	
	// default value - line 4
	final int _nParamL4 = 17;
	final double _defaultNOMV2 = 0.0, _defaultANG2 = 0.0, 
			_defaultRATA2 = 0.0, _defaultRATB2 = 0.0, _defaultRATC2 = 0.0, 
			_defaultCR2 = 0.0, _defaultCX2 = 0.0, _defaultCNXA2 = 0.0;
	final int _defaultCOD2 = 0, _defaultNTP2 = 33, _defaultTAB2 = 0;
	final String _defaultCONT2 = "0";
	
	// default value - line 5
	final int _nParamL5 = 17;
	final double _defaultNOMV3 = 0.0, _defaultANG3 = 0.0, 
			_defaultRATA3 = 0.0, _defaultRATB3 = 0.0, _defaultRATC3 = 0.0, 
			_defaultCR3 = 0.0, _defaultCX3 = 0.0, _defaultCNXA3 = 0.0;
	final int _defaultCOD3 = 0, _defaultNTP3 = 33, _defaultTAB3 = 0;
	final String _defaultCONT3 = "0";
	
	public void readData(BufferedReader reader) throws IOException
	{
		ArrayList<Boolean> is3Xfm = new ArrayList<Boolean>();
		
		ArrayList<Integer> busIndx = new ArrayList<Integer>();
		ArrayList<Integer> busJndx = new ArrayList<Integer>(); 
		ArrayList<Integer> busKndx = new ArrayList<Integer>(); 
		
		/* Line 1 */
		ArrayList<String> i = new ArrayList<String>();
		ArrayList<String> j = new ArrayList<String>();
		ArrayList<String> k = new ArrayList<String>();
		ArrayList<String> ckt = new ArrayList<String>();
		ArrayList<Integer> cw = new ArrayList<Integer>();
		ArrayList<Integer> cz = new ArrayList<Integer>();
		ArrayList<Integer> cm = new ArrayList<Integer>();
		ArrayList<Double> mag1 = new ArrayList<Double>();
		ArrayList<Double> mag2 = new ArrayList<Double>();
		ArrayList<Integer> nmetr = new ArrayList<Integer>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<Integer> stat = new ArrayList<Integer>();
		ArrayList<Integer> o1 = new ArrayList<Integer>();
		ArrayList<Double> f1 = new ArrayList<Double>();
		ArrayList<Integer> o2 = new ArrayList<Integer>();
		ArrayList<Double> f2 = new ArrayList<Double>();
		ArrayList<Integer> o3 = new ArrayList<Integer>();
		ArrayList<Double> f3 = new ArrayList<Double>();
		ArrayList<Integer> o4 = new ArrayList<Integer>();
		ArrayList<Double> f4 = new ArrayList<Double>();
		ArrayList<String> vecgrp = new ArrayList<String>();
		
		/* Line 2 */
		ArrayList<Double> r12 = new ArrayList<Double>();
		ArrayList<Double> x12 = new ArrayList<Double>();
		ArrayList<Double> sbase12 = new ArrayList<Double>();
		ArrayList<Double> r23 = new ArrayList<Double>();
		ArrayList<Double> x23 = new ArrayList<Double>();
		ArrayList<Double> sbase23 = new ArrayList<Double>();
		ArrayList<Double> r31 = new ArrayList<Double>();
		ArrayList<Double> x31 = new ArrayList<Double>();
		ArrayList<Double> sbase31 = new ArrayList<Double>();
		ArrayList<Double> vmstar = new ArrayList<Double>();
		ArrayList<Double> anstar = new ArrayList<Double>();

		/* Line 3 */
		ArrayList<Double> windv1 = new ArrayList<Double>();
		ArrayList<Double> nomv1 = new ArrayList<Double>();
		ArrayList<Double> ang1 = new ArrayList<Double>();
		ArrayList<Double> rata1 = new ArrayList<Double>();
		ArrayList<Double> ratb1 = new ArrayList<Double>();
		ArrayList<Double> ratc1 = new ArrayList<Double>();
		ArrayList<Integer> cod1 = new ArrayList<Integer>();
		ArrayList<String> cont1 = new ArrayList<String>();
		ArrayList<Double> rma1 = new ArrayList<Double>();
		ArrayList<Double> rmi1 = new ArrayList<Double>();
		ArrayList<Double> vma1 = new ArrayList<Double>();
		ArrayList<Double> vmi1 = new ArrayList<Double>();
		ArrayList<Integer> ntp1 = new ArrayList<Integer>();
		ArrayList<Integer> tab1 = new ArrayList<Integer>();
		ArrayList<Double> cr1 = new ArrayList<Double>();
		ArrayList<Double> cx1 = new ArrayList<Double>();
		ArrayList<Double> cnxa1 = new ArrayList<Double>();

		/* Line 4 */
		ArrayList<Double> windv2 = new ArrayList<Double>();
		ArrayList<Double> nomv2 = new ArrayList<Double>();
		ArrayList<Double> ang2 = new ArrayList<Double>();
		ArrayList<Double> rata2 = new ArrayList<Double>();
		ArrayList<Double> ratb2 = new ArrayList<Double>();
		ArrayList<Double> ratc2 = new ArrayList<Double>();
		ArrayList<Integer> cod2 = new ArrayList<Integer>();
		ArrayList<String> cont2 = new ArrayList<String>();
		ArrayList<Double> rma2 = new ArrayList<Double>();
		ArrayList<Double> rmi2 = new ArrayList<Double>();
		ArrayList<Double> vma2 = new ArrayList<Double>();
		ArrayList<Double> vmi2 = new ArrayList<Double>();
		ArrayList<Integer> ntp2 = new ArrayList<Integer>();
		ArrayList<Integer> tab2 = new ArrayList<Integer>();
		ArrayList<Double> cr2 = new ArrayList<Double>();
		ArrayList<Double> cx2 = new ArrayList<Double>();
		ArrayList<Double> cnxa2 = new ArrayList<Double>();

		/* Line 5 */
		ArrayList<Double> windv3 = new ArrayList<Double>();
		ArrayList<Double> nomv3 = new ArrayList<Double>();
		ArrayList<Double> ang3 = new ArrayList<Double>();
		ArrayList<Double> rata3 = new ArrayList<Double>();
		ArrayList<Double> ratb3 = new ArrayList<Double>();
		ArrayList<Double> ratc3 = new ArrayList<Double>();
		ArrayList<Integer> cod3 = new ArrayList<Integer>();
		ArrayList<String> cont3 = new ArrayList<String>();
		ArrayList<Double> rma3 = new ArrayList<Double>();
		ArrayList<Double> rmi3 = new ArrayList<Double>();
		ArrayList<Double> vma3 = new ArrayList<Double>();
		ArrayList<Double> vmi3 = new ArrayList<Double>();
		ArrayList<Integer> ntp3 = new ArrayList<Integer>();
		ArrayList<Integer> tab3 = new ArrayList<Integer>();
		ArrayList<Double> cr3 = new ArrayList<Double>();
		ArrayList<Double> cx3 = new ArrayList<Double>();
		ArrayList<Double> cnxa3 = new ArrayList<Double>();

		String line;
		_sizeXfm2 = 0; _sizeXfm3 = 0;
		System.out.println("Start reading transformer data");
		while ((line = reader.readLine()) != null) {
			if(AuxMethod.isOneTypeEntryEnded(line)) break;
			
			/*  line 1  */
			String[] tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			int idx = 0;
			
			int busIndxTmp = AuxPsseRaw.determineBusIndex(_buses, tokens, idx);
			busIndx.add(busIndxTmp);
			i.add(tokens[idx++]);
			
			int busJndxTmp = AuxPsseRaw.determineBusIndex(_buses, tokens, idx);
			busJndx.add(busJndxTmp);
			j.add(tokens[idx++]);

			int busKndxTmp = 0;
			if (tokens[idx].equals("0")) {busKndx.add(0); is3Xfm.add(false);}
			else {
				busKndxTmp = AuxPsseRaw.determineBusIndex(_buses, tokens, idx);
				busKndx.add(busKndxTmp); is3Xfm.add(true);
			}
			k.add(tokens[idx++]);

			AuxPsseRaw.readOneParam(ckt, tokens, idx++, _defaultCKT);
			AuxPsseRaw.readOneParam(cw, tokens, idx++, _defaultCW);
			AuxPsseRaw.readOneParam(cz, tokens, idx++, _defaultCZ);
			AuxPsseRaw.readOneParam(cm, tokens, idx++, _defaultCM);
			AuxPsseRaw.readOneParam(mag1, tokens, idx++, _defaultMAG1);
			AuxPsseRaw.readOneParam(mag2, tokens, idx++, _defaultMAG1);
			AuxPsseRaw.readOneParam(nmetr, tokens, idx++, _defaultNMETR);
			AuxPsseRaw.readOneParam(name, tokens, idx++, _defaultNAME);
			AuxPsseRaw.readOneParam(stat, tokens, idx++, _defaultSTAT);
			AuxPsseRaw.readOneParam(o1, tokens, idx++, _buses.getOwner(busIndxTmp));
			AuxPsseRaw.readOneParam(f1, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o2, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f2, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o3, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f3, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(o4, tokens, idx++, 0);
			AuxPsseRaw.readOneParam(f4, tokens, idx++, 1.0);
			AuxPsseRaw.readOneParam(vecgrp, tokens, idx++, _defaultVECGRP);
			
			if(idx > _nParamL1) {System.err.println("More input data than desired L1 after "+_sizeXfm2+" 2xfm and "+_sizeXfm3+" 3xfm."); System.exit(1);}
			
			/*  line 2  */
			line = reader.readLine();
			tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			idx = 0;
			
			AuxPsseRaw.readOneParam(r12, tokens, idx++, _defaultR12);
			x12.add(Double.parseDouble(tokens[idx++]));
			AuxPsseRaw.readOneParam(sbase12, tokens, idx++, _defaultSBASE12);
			
			if (is3Xfm.get(_sizeXfm2 + _sizeXfm3) == true)
			{
				AuxPsseRaw.readOneParam(r23, tokens, idx++, _defaultR23);
				x23.add(Double.parseDouble(tokens[idx++]));
				AuxPsseRaw.readOneParam(sbase23, tokens, idx++, _defaultSBASE23);
				
				AuxPsseRaw.readOneParam(r31, tokens, idx++, _defaultR31);
				x31.add(Double.parseDouble(tokens[idx++]));
				AuxPsseRaw.readOneParam(sbase31, tokens, idx++, _defaultSBASE31);
				
				AuxPsseRaw.readOneParam(vmstar, tokens, idx++, _defaultVMSTAR);
				AuxPsseRaw.readOneParam(anstar, tokens, idx++, _defaultANSTAR);
			} else {
				r23.add(null); x23.add(null); sbase23.add(null);
				r31.add(null); x31.add(null); sbase31.add(null);
				vmstar.add(null); anstar.add(null);
			}
			
			if(idx > _nParamL2) {System.err.println("More input data than desired L2 after "+_sizeXfm2+" 2xfm and "+_sizeXfm3+" 3xfm."); System.exit(1);}

			/*  line 3  */
			line = reader.readLine();
			tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			idx = 0;

			int cwTmp = cw.get(_sizeXfm2+_sizeXfm3);
			double tmpDefault = (cwTmp == 2) ? _buses.getBaseKV(busIndxTmp) : 1.0;
			AuxPsseRaw.readOneParam(windv1, tokens, idx++, tmpDefault);
			
			AuxPsseRaw.readOneParam(nomv1, tokens, idx++, _defaultNOMV1);
			AuxPsseRaw.readOneParam(ang1, tokens, idx++, _defaultANG1);
			AuxPsseRaw.readOneParam(rata1, tokens, idx++, _defaultRATA1);
			AuxPsseRaw.readOneParam(ratb1, tokens, idx++, _defaultRATB1);
			AuxPsseRaw.readOneParam(ratc1, tokens, idx++, _defaultRATC1);
			AuxPsseRaw.readOneParam(cod1, tokens, idx++, _defaultCOD1);
			AuxPsseRaw.readOneParam(cont1, tokens, idx++, _defaultCONT1);
			
			int cod1Tmp = Math.abs(cod1.get(_sizeXfm2+_sizeXfm3));
			if (cod1Tmp == 3 || cod1Tmp == 5 || ((cod1Tmp == 1 || cod1Tmp == 2) && cwTmp == 2))
			{
				rma1.add(Double.parseDouble(tokens[idx++]));
				rmi1.add(Double.parseDouble(tokens[idx++]));
			} else {
				AuxPsseRaw.readOneParam(rma1, tokens, idx++, 1.1);
				AuxPsseRaw.readOneParam(rmi1, tokens, idx++, 0.9);
			}
			
			if (cod1Tmp == 2 || cod1Tmp == 5 || cod1Tmp == 3)
			{
				vma1.add(Double.parseDouble(tokens[idx++]));
				vmi1.add(Double.parseDouble(tokens[idx++]));
			} else {
				AuxPsseRaw.readOneParam(vma1, tokens, idx++, 1.1);
				AuxPsseRaw.readOneParam(vmi1, tokens, idx++, 0.9);
			}

			AuxPsseRaw.readOneParam(ntp1, tokens, idx++, _defaultNTP1);
			AuxPsseRaw.readOneParam(tab1, tokens, idx++, _defaultTAB1);
			AuxPsseRaw.readOneParam(cr1, tokens, idx++, _defaultCR1);
			AuxPsseRaw.readOneParam(cx1, tokens, idx++, _defaultCX1);
			AuxPsseRaw.readOneParam(cnxa1, tokens, idx++, _defaultCNXA1);
			
			if(idx > _nParamL3) {System.err.println("More input data than desired L3 after "+_sizeXfm2+" 2xfm and "+_sizeXfm3+" 3xfm."); System.exit(1);}
			
			/*  line 4  */
			line = reader.readLine();
			tokens = AuxMethod.getTokensByComma(line);
			AuxMethod.stringsTrim(tokens);
			idx = 0;

			tmpDefault = (cwTmp == 2) ? _buses.getBaseKV(busJndxTmp) : 1.0;
			AuxPsseRaw.readOneParam(windv2, tokens, idx++, tmpDefault);
			AuxPsseRaw.readOneParam(nomv2, tokens, idx++, _defaultNOMV2);
			
			if (is3Xfm.get(_sizeXfm2 + _sizeXfm3) == true)
			{
				AuxPsseRaw.readOneParam(ang2, tokens, idx++, _defaultANG2);
				AuxPsseRaw.readOneParam(rata2, tokens, idx++, _defaultRATA2);
				AuxPsseRaw.readOneParam(ratb2, tokens, idx++, _defaultRATB2);
				AuxPsseRaw.readOneParam(ratc2, tokens, idx++, _defaultRATC2);
				AuxPsseRaw.readOneParam(cod2, tokens, idx++, _defaultCOD2);
				AuxPsseRaw.readOneParam(cont2, tokens, idx++, _defaultCONT2);
				
				int cod2Tmp = Math.abs(cod2.get(_sizeXfm2+_sizeXfm3));
				if (cod2Tmp == 3 || cod1Tmp == 5 || ((cod2Tmp == 1 || cod2Tmp == 2) && cwTmp == 2))
				{
					rma2.add(Double.parseDouble(tokens[idx++]));
					rmi2.add(Double.parseDouble(tokens[idx++]));
				} else {
					AuxPsseRaw.readOneParam(rma2, tokens, idx++, 1.1);
					AuxPsseRaw.readOneParam(rmi2, tokens, idx++, 0.9);
				}
				
				if (cod2Tmp == 2 || cod1Tmp == 5 || cod2Tmp == 3)
				{
					vma2.add(Double.parseDouble(tokens[idx++]));
					vmi2.add(Double.parseDouble(tokens[idx++]));
				} else {
					AuxPsseRaw.readOneParam(vma2, tokens, idx++, 1.1);
					AuxPsseRaw.readOneParam(vmi2, tokens, idx++, 0.9);
				}

				AuxPsseRaw.readOneParam(ntp2, tokens, idx++, _defaultNTP2);
				AuxPsseRaw.readOneParam(tab2, tokens, idx++, _defaultTAB2);
				AuxPsseRaw.readOneParam(cr2, tokens, idx++, _defaultCR2);
				AuxPsseRaw.readOneParam(cx2, tokens, idx++, _defaultCX2);
				AuxPsseRaw.readOneParam(cnxa2, tokens, idx++, _defaultCNXA2);
			} else {
				ang2.add(null); rata2.add(null); ratb2.add(null); ratc2.add(null);
				cod2.add(null); cont2.add(null); rma2.add(null); rmi2.add(null);
				vma2.add(null); vmi2.add(null); ntp2.add(null); tab2.add(null);
				cr2.add(null); cx2.add(null); cnxa2.add(null);
			}
			
			if(idx > _nParamL4) {System.err.println("More input data than desired L4 after "+_sizeXfm2+" 2xfm and "+_sizeXfm3+" 3xfm."); System.exit(1);}
			
			/*  line 5  */
			if (is3Xfm.get(_sizeXfm2 + _sizeXfm3) == true)
			{
				line = reader.readLine();
				tokens = AuxMethod.getTokensByComma(line);
				AuxMethod.stringsTrim(tokens);
				idx = 0;

				tmpDefault = (cwTmp == 2) ? _buses.getBaseKV(busKndxTmp) : 1.0;
				AuxPsseRaw.readOneParam(windv3, tokens, idx++, tmpDefault);
				
				AuxPsseRaw.readOneParam(nomv3, tokens, idx++, _defaultNOMV3);
				AuxPsseRaw.readOneParam(ang3, tokens, idx++, _defaultANG3);
				AuxPsseRaw.readOneParam(rata3, tokens, idx++, _defaultRATA3);
				AuxPsseRaw.readOneParam(ratb3, tokens, idx++, _defaultRATB3);
				AuxPsseRaw.readOneParam(ratc3, tokens, idx++, _defaultRATC3);
				AuxPsseRaw.readOneParam(cod3, tokens, idx++, _defaultCOD3);
				AuxPsseRaw.readOneParam(cont3, tokens, idx++, _defaultCONT3);
				
				int cod3Tmp = Math.abs(cod3.get(_sizeXfm2+_sizeXfm3));
				if (cod3Tmp == 3 || cod1Tmp == 5 || ((cod3Tmp == 1 || cod3Tmp == 2) && cwTmp == 2))
				{
					rma3.add(Double.parseDouble(tokens[idx++]));
					rmi3.add(Double.parseDouble(tokens[idx++]));
				} else {
					AuxPsseRaw.readOneParam(rma3, tokens, idx++, 1.1);
					AuxPsseRaw.readOneParam(rmi3, tokens, idx++, 0.9);
				}
				
				if (cod3Tmp == 2 || cod1Tmp == 5 || cod3Tmp == 3)
				{
					vma3.add(Double.parseDouble(tokens[idx++]));
					vmi3.add(Double.parseDouble(tokens[idx++]));
				} else {
					AuxPsseRaw.readOneParam(vma3, tokens, idx++, 1.1);
					AuxPsseRaw.readOneParam(vmi3, tokens, idx++, 0.9);
				}

				AuxPsseRaw.readOneParam(ntp3, tokens, idx++, _defaultNTP3);
				AuxPsseRaw.readOneParam(tab3, tokens, idx++, _defaultTAB3);
				AuxPsseRaw.readOneParam(cr3, tokens, idx++, _defaultCR3);
				AuxPsseRaw.readOneParam(cx3, tokens, idx++, _defaultCX3);
				AuxPsseRaw.readOneParam(cnxa3, tokens, idx++, _defaultCNXA3);
				
				if(idx > _nParamL5) {System.err.println("More input data than desired L5 after "+_sizeXfm2+" 2xfm and "+_sizeXfm3+" 3xfm."); System.exit(1);}
			} else {
				windv3.add(null); nomv3.add(null); ang3.add(null); 
				rata3.add(null); ratb3.add(null); ratc3.add(null); 
				cod3.add(null); cont3.add(null); rma3.add(null); 
				rmi3.add(null); vma3.add(null); vmi3.add(null); 
				ntp3.add(null); tab3.add(null); cr3.add(null); 
				cx3.add(null); cnxa3.add(null);
			}
			if (is3Xfm.get(_sizeXfm2 + _sizeXfm3) == true) _sizeXfm3++;
			else _sizeXfm2++;
		}
		
		// remove single quotes if any
		AuxMethod.removeBoundarySingleQuotes(i);
		AuxMethod.removeBoundarySingleQuotes(j);
		AuxMethod.removeBoundarySingleQuotes(k);
		AuxMethod.removeBoundarySingleQuotes(ckt);

		// remove double quotes if any
		AuxMethod.removeBoundaryDoubleQuotes(i);
		AuxMethod.removeBoundaryDoubleQuotes(j);
		AuxMethod.removeBoundaryDoubleQuotes(k);
		AuxMethod.removeBoundaryDoubleQuotes(ckt);

		// start separating 2-winding xfm, 2-winding phase shifter, 3-winding xfm, and 3-winding phase shifter.
		_isXfmPhaseShifter = isXfmPhaseShifter(cod1);
		int[] sizes = getXfmSizes(is3Xfm, _isXfmPhaseShifter);
		Transformer2List xfm2List = new Transformer2List(_model); xfm2List.initVarSize(sizes[0]);
		Transformer3List xfm3List = new Transformer3List(_model); xfm3List.initVarSize(sizes[1]);
		PhaseShifter2List ps2List = new PhaseShifter2List(_model); ps2List.initVarSize(sizes[2]);
		PhaseShifter3List ps3List = new PhaseShifter3List(_model); ps3List.initVarSize(sizes[3]);
		
		int ndx2Xfm = 0, ndx3Xfm = 0;
		int ndx2PS = 0, ndx3PS = 0;
		for (int ndxXfm=0; ndxXfm<is3Xfm.size(); ndxXfm++)
		{
			if (is3Xfm.get(ndxXfm) == true) {
				if (_isXfmPhaseShifter[ndxXfm] == true) {
					recordOne3WindingXfmEntryData(ps3List, ndx3PS++, ndxXfm, busIndx, busJndx, busKndx, i, j, k, ckt, cw, cz, cm, 
							mag1, mag2, nmetr, name, stat, o1, f1, o2, f2, o3, f3, o4, f4, vecgrp,
							r12, x12, sbase12, r23, x23, sbase23, r31, x31, sbase31, vmstar, anstar,
							windv1, nomv1, ang1, rata1, ratb1, ratc1, cod1, cont1, rma1, rmi1, vma1, vmi1, ntp1, tab1, cr1, cx1, cnxa1,
							windv2, nomv2, ang2, rata2, ratb2, ratc2, cod2, cont2, rma2, rmi2, vma2, vmi2, ntp2, tab2, cr2, cx2, cnxa2,
							windv3, nomv3, ang3, rata3, ratb3, ratc3, cod3, cont3, rma3, rmi3, vma3, vmi3, ntp3, tab3, cr3, cx3, cnxa3 );
				} else {
					recordOne3WindingXfmEntryData(xfm3List, ndx3Xfm++, ndxXfm, busIndx, busJndx, busKndx, i, j, k, ckt, cw, cz, cm, 
							mag1, mag2, nmetr, name, stat, o1, f1, o2, f2, o3, f3, o4, f4, vecgrp,
							r12, x12, sbase12, r23, x23, sbase23, r31, x31, sbase31, vmstar, anstar,
							windv1, nomv1, ang1, rata1, ratb1, ratc1, cod1, cont1, rma1, rmi1, vma1, vmi1, ntp1, tab1, cr1, cx1, cnxa1,
							windv2, nomv2, ang2, rata2, ratb2, ratc2, cod2, cont2, rma2, rmi2, vma2, vmi2, ntp2, tab2, cr2, cx2, cnxa2,
							windv3, nomv3, ang3, rata3, ratb3, ratc3, cod3, cont3, rma3, rmi3, vma3, vmi3, ntp3, tab3, cr3, cx3, cnxa3 );
				}
			} else {
				if (_isXfmPhaseShifter[ndxXfm] == true) {
					recordOne2WindingXfmEntryData(ps2List, ndx2PS++, ndxXfm, busIndx, busJndx, i, j, k, ckt, cw, cz, cm, 
							mag1, mag2, nmetr, name, stat, o1, f1, o2, f2, o3, f3, o4, f4, vecgrp, 
							r12, x12, sbase12,
							windv1, nomv1, ang1, rata1, ratb1, ratc1, cod1, cont1, rma1, rmi1, vma1, vmi1, ntp1, tab1, cr1, cx1, cnxa1,
							windv2, nomv2);
				} else {
					recordOne2WindingXfmEntryData(xfm2List, ndx2Xfm++, ndxXfm, busIndx, busJndx, i, j, k, ckt, cw, cz, cm, 
							mag1, mag2, nmetr, name, stat, o1, f1, o2, f2, o3, f3, o4, f4, vecgrp,
							r12, x12, sbase12,
							windv1, nomv1, ang1, rata1, ratb1, ratc1, cod1, cont1, rma1, rmi1, vma1, vmi1, ntp1, tab1, cr1, cx1, cnxa1,
							windv2, nomv2);
				}
			}
		}
//		assertTrue(ndx2Xfm == sizes[0]);
//		assertTrue(ndx3Xfm == sizes[1]);
//		assertTrue(ndx2PS == sizes[2]);
//		assertTrue(ndx3PS == sizes[3]);

		_model.setTransformer2List(xfm2List);
		_model.setTransformer3List(xfm3List);
		_model.setPhaseShifter2List(ps2List);
		_model.setPhaseShifter3List(ps3List);
		
		System.out.println("   Finish reading transformer data");
	}

	/**
	 * sizes[0]: # of 2-winding transformer 
	 * sizes[1]: # of 3-winding transformer 
	 * sizes[2]: # of 2-winding phase shifter
	 * sizes[3]: # of 3-winding phase shifter
	 */
	private int[] getXfmSizes(ArrayList<Boolean> is3Xfm, boolean[] isXfmPhaseShifter2) 
	{
		int[] sizes = new int[4];
		int sizeXfm = is3Xfm.size();
//		assertTrue(sizeXfm == isXfmPhaseShifter2.length);
		for (int i=0; i<sizeXfm; i++)
		{
			if (is3Xfm.get(i) == false) {
				if (isXfmPhaseShifter2[i] == false) sizes[0]++;
				else sizes[2]++;
			} else {
				if (isXfmPhaseShifter2[i] == false) sizes[1]++;
				else sizes[3]++;
			}
		}
		return sizes;
	}


	//TODO: when cod1 == 0, the corresponding xfm may also be a phase shifter. /* To be fixed. */
	private boolean[] isXfmPhaseShifter(ArrayList<Integer> cod1)
	{
		int size = cod1.size();
		boolean[] isXfmPS = new boolean[size];
		for (int i=0; i<size; i++) {
			if (cod1.get(i) == 3 || cod1.get(i) == -3) isXfmPS[i] = true;
			if (cod1.get(i) == 5 || cod1.get(i) == -5) isXfmPS[i] = true;
		}
		return isXfmPS;
	}
	
	private void recordOne3WindingXfmEntryData(Transformer3List xfm3List, int ndx3Xfm, int ndxXfm,
			ArrayList<Integer> busIndx, ArrayList<Integer> busJndx, ArrayList<Integer> busKndx,
			/* Line 1 */
			ArrayList<String> i,
			ArrayList<String> j, ArrayList<String> k, ArrayList<String> ckt, ArrayList<Integer> cw,
			ArrayList<Integer> cz, ArrayList<Integer> cm, ArrayList<Double> mag1, ArrayList<Double> mag2,
			ArrayList<Integer> nmetr, ArrayList<String> name, ArrayList<Integer> stat, 
			ArrayList<Integer> o1, ArrayList<Double> f1, ArrayList<Integer> o2, ArrayList<Double> f2,
			ArrayList<Integer> o3, ArrayList<Double> f3, ArrayList<Integer> o4, ArrayList<Double> f4,
			ArrayList<String> vecgrp,
			/* Line 2 */
			ArrayList<Double> r12, ArrayList<Double> x12, ArrayList<Double> sbase12, ArrayList<Double> r23,
			ArrayList<Double> x23, ArrayList<Double> sbase23, ArrayList<Double> r31, ArrayList<Double> x31,
			ArrayList<Double> sbase31, ArrayList<Double> vmstar, ArrayList<Double> anstar,
			/* Line 3 */
			ArrayList<Double> windv1, ArrayList<Double> nomv1, ArrayList<Double> ang1, 
			ArrayList<Double> rata1, ArrayList<Double> ratb1, ArrayList<Double> ratc1,
			ArrayList<Integer> cod1, ArrayList<String> cont1, ArrayList<Double> rma1,
			ArrayList<Double> rmi1, ArrayList<Double> vma1, ArrayList<Double> vmi1,
			ArrayList<Integer> ntp1, ArrayList<Integer> tab1, ArrayList<Double> cr1,
			ArrayList<Double> cx1, ArrayList<Double> cnxa1,
			/* Line 4 */
			ArrayList<Double> windv2, ArrayList<Double> nomv2, ArrayList<Double> ang2, 
			ArrayList<Double> rata2, ArrayList<Double> ratb2, ArrayList<Double> ratc2,
			ArrayList<Integer> cod2, ArrayList<String> cont2, ArrayList<Double> rma2,
			ArrayList<Double> rmi2, ArrayList<Double> vma2, ArrayList<Double> vmi2,
			ArrayList<Integer> ntp2, ArrayList<Integer> tab2, ArrayList<Double> cr2,
			ArrayList<Double> cx2, ArrayList<Double> cnxa2,
			/* Line 5 */
			ArrayList<Double> windv3, ArrayList<Double> nomv3, ArrayList<Double> ang3, 
			ArrayList<Double> rata3, ArrayList<Double> ratb3, ArrayList<Double> ratc3,
			ArrayList<Integer> cod3, ArrayList<String> cont3, ArrayList<Double> rma3,
			ArrayList<Double> rmi3, ArrayList<Double> vma3, ArrayList<Double> vmi3,
			ArrayList<Integer> ntp3, ArrayList<Integer> tab3, ArrayList<Double> cr3,
			ArrayList<Double> cx3, ArrayList<Double> cnxa3)
	{
		xfm3List._busIndx[ndx3Xfm] = busIndx.get(ndxXfm);
		xfm3List._busJndx[ndx3Xfm] = busJndx.get(ndxXfm);
		xfm3List._busKndx[ndx3Xfm] = busKndx.get(ndxXfm);
		xfm3List._idxRawXfm[ndx3Xfm] = ndxXfm;
		
		/* line 1 */
		xfm3List._i[ndx3Xfm] = i.get(ndxXfm);
		xfm3List._j[ndx3Xfm] = j.get(ndxXfm);
		xfm3List._k[ndx3Xfm] = k.get(ndxXfm);
		xfm3List._ckt[ndx3Xfm] = ckt.get(ndxXfm);
		xfm3List._cw[ndx3Xfm] = cw.get(ndxXfm);
		xfm3List._cz[ndx3Xfm] = cz.get(ndxXfm);
		xfm3List._cm[ndx3Xfm] = cm.get(ndxXfm);
		xfm3List._mag1[ndx3Xfm] = mag1.get(ndxXfm);
		xfm3List._mag2[ndx3Xfm] = mag2.get(ndxXfm);
		xfm3List._nmetr[ndx3Xfm] = nmetr.get(ndxXfm);
		xfm3List._name[ndx3Xfm] = name.get(ndxXfm);
		xfm3List._stat[ndx3Xfm] = stat.get(ndxXfm);
		xfm3List._o1[ndx3Xfm] = o1.get(ndxXfm);
		xfm3List._f1[ndx3Xfm] = f1.get(ndxXfm);
		xfm3List._o2[ndx3Xfm] = o2.get(ndxXfm);
		xfm3List._f2[ndx3Xfm] = f2.get(ndxXfm);
		xfm3List._o3[ndx3Xfm] = o3.get(ndxXfm);
		xfm3List._f3[ndx3Xfm] = f3.get(ndxXfm);
		xfm3List._o4[ndx3Xfm] = o4.get(ndxXfm);
		xfm3List._f4[ndx3Xfm] = f4.get(ndxXfm);
		xfm3List._vecgrp[ndx3Xfm] = vecgrp.get(ndxXfm);
		
		/* line 2 */
		xfm3List._r12[ndx3Xfm] = r12.get(ndxXfm);
		xfm3List._x12[ndx3Xfm] = x12.get(ndxXfm);
		xfm3List._sb12[ndx3Xfm] = sbase12.get(ndxXfm);
		xfm3List._r23[ndx3Xfm] = r23.get(ndxXfm);
		xfm3List._x23[ndx3Xfm] = x23.get(ndxXfm);
		xfm3List._sb23[ndx3Xfm] = sbase23.get(ndxXfm);
		xfm3List._r31[ndx3Xfm] = r31.get(ndxXfm);
		xfm3List._x31[ndx3Xfm] = x31.get(ndxXfm);
		xfm3List._sb31[ndx3Xfm] = sbase31.get(ndxXfm);
		xfm3List._vmstar[ndx3Xfm] = vmstar.get(ndxXfm);
		xfm3List._anstar[ndx3Xfm] = anstar.get(ndxXfm);
		
		/* line 3 */
		xfm3List._windv1[ndx3Xfm] = windv1.get(ndxXfm);
		xfm3List._nomv1[ndx3Xfm] = nomv1.get(ndxXfm);
		xfm3List._ang1[ndx3Xfm] = ang1.get(ndxXfm);
		xfm3List._rata1[ndx3Xfm] = rata1.get(ndxXfm);
		xfm3List._ratb1[ndx3Xfm] = ratb1.get(ndxXfm);
		xfm3List._ratc1[ndx3Xfm] = ratc1.get(ndxXfm);
		xfm3List._cod1[ndx3Xfm] = cod1.get(ndxXfm);
		xfm3List._cont1[ndx3Xfm] = cont1.get(ndxXfm);
		xfm3List._rma1[ndx3Xfm] = rma1.get(ndxXfm);
		xfm3List._rmi1[ndx3Xfm] = rmi1.get(ndxXfm);
		xfm3List._vma1[ndx3Xfm] = vma1.get(ndxXfm);
		xfm3List._vmi1[ndx3Xfm] = vmi1.get(ndxXfm);
		xfm3List._ntp1[ndx3Xfm] = ntp1.get(ndxXfm);
		xfm3List._tab1[ndx3Xfm] = tab1.get(ndxXfm);
		xfm3List._cr1[ndx3Xfm] = cr1.get(ndxXfm);
		xfm3List._cx1[ndx3Xfm] = cx1.get(ndxXfm);
		xfm3List._cnxa1[ndx3Xfm] = cnxa1.get(ndxXfm);

		/* line 4 */
		xfm3List._windv2[ndx3Xfm] = windv2.get(ndxXfm);
		xfm3List._nomv2[ndx3Xfm] = nomv2.get(ndxXfm);
		xfm3List._ang2[ndx3Xfm] = ang2.get(ndxXfm);
		xfm3List._rata2[ndx3Xfm] = rata2.get(ndxXfm);
		xfm3List._ratb2[ndx3Xfm] = ratb2.get(ndxXfm);
		xfm3List._ratc2[ndx3Xfm] = ratc2.get(ndxXfm);
		xfm3List._cod2[ndx3Xfm] = cod2.get(ndxXfm);
		xfm3List._cont2[ndx3Xfm] = cont2.get(ndxXfm);
		xfm3List._rma2[ndx3Xfm] = rma2.get(ndxXfm);
		xfm3List._rmi2[ndx3Xfm] = rmi2.get(ndxXfm);
		xfm3List._vma2[ndx3Xfm] = vma2.get(ndxXfm);
		xfm3List._vmi2[ndx3Xfm] = vmi2.get(ndxXfm);
		xfm3List._ntp2[ndx3Xfm] = ntp2.get(ndxXfm);
		xfm3List._tab2[ndx3Xfm] = tab2.get(ndxXfm);
		xfm3List._cr2[ndx3Xfm] = cr2.get(ndxXfm);
		xfm3List._cx2[ndx3Xfm] = cx2.get(ndxXfm);
		xfm3List._cnxa2[ndx3Xfm] = cnxa2.get(ndxXfm);

		/* line 5 */
		xfm3List._windv3[ndx3Xfm] = windv3.get(ndxXfm);
		xfm3List._nomv3[ndx3Xfm] = nomv3.get(ndxXfm);
		xfm3List._ang3[ndx3Xfm] = ang3.get(ndxXfm);
		xfm3List._rata3[ndx3Xfm] = rata3.get(ndxXfm);
		xfm3List._ratb3[ndx3Xfm] = ratb3.get(ndxXfm);
		xfm3List._ratc3[ndx3Xfm] = ratc3.get(ndxXfm);
		xfm3List._cod3[ndx3Xfm] = cod3.get(ndxXfm);
		xfm3List._cont3[ndx3Xfm] = cont3.get(ndxXfm);
		xfm3List._rma3[ndx3Xfm] = rma3.get(ndxXfm);
		xfm3List._rmi3[ndx3Xfm] = rmi3.get(ndxXfm);
		xfm3List._vma3[ndx3Xfm] = vma3.get(ndxXfm);
		xfm3List._vmi3[ndx3Xfm] = vmi3.get(ndxXfm);
		xfm3List._ntp3[ndx3Xfm] = ntp3.get(ndxXfm);
		xfm3List._tab3[ndx3Xfm] = tab3.get(ndxXfm);
		xfm3List._cr3[ndx3Xfm] = cr3.get(ndxXfm);
		xfm3List._cx3[ndx3Xfm] = cx3.get(ndxXfm);
		xfm3List._cnxa3[ndx3Xfm] = cnxa3.get(ndxXfm);
		
		/* preprocess */
		String cont1Tmp = xfm3List._cont1[ndx3Xfm];
		if (cont1Tmp.startsWith("-")) cont1Tmp = cont1Tmp.substring(1);
		if (cont1Tmp.equals("0") == false) {
			xfm3List._cont1Index[ndx3Xfm] = AuxPsseRaw.determineBusIndex(_buses, cont1Tmp);
			xfm3List._cont1Number[ndx3Xfm] = _buses.getI(xfm3List._cont1Index[ndx3Xfm]);
		}
		String cont2Tmp = xfm3List._cont2[ndx3Xfm];
		if (cont2Tmp.startsWith("-")) cont2Tmp = cont2Tmp.substring(1);
		if (cont2Tmp.equals("0") == false) {
			xfm3List._cont2Index[ndx3Xfm] = AuxPsseRaw.determineBusIndex(_buses, cont2Tmp);
			xfm3List._cont2Number[ndx3Xfm] = _buses.getI(xfm3List._cont2Index[ndx3Xfm]);
		}
		String cont3Tmp = xfm3List._cont3[ndx3Xfm];
		if (cont3Tmp.startsWith("-")) cont3Tmp = cont3Tmp.substring(1);
		if (cont3Tmp.equals("0") == false) {
			xfm3List._cont3Index[ndx3Xfm] = AuxPsseRaw.determineBusIndex(_buses, cont3Tmp);
			xfm3List._cont3Number[ndx3Xfm] = _buses.getI(xfm3List._cont3Index[ndx3Xfm]);
		}
	}

	private void recordOne2WindingXfmEntryData(Transformer2List xfm2List, int ndx2Xfm, int ndxXfm,
			ArrayList<Integer> busIndx, ArrayList<Integer> busJndx,
			/* Line 1 */
			ArrayList<String> i,
			ArrayList<String> j, ArrayList<String> k, ArrayList<String> ckt, ArrayList<Integer> cw,
			ArrayList<Integer> cz, ArrayList<Integer> cm, ArrayList<Double> mag1, ArrayList<Double> mag2,
			ArrayList<Integer> nmetr, ArrayList<String> name, ArrayList<Integer> stat, 
			ArrayList<Integer> o1, ArrayList<Double> f1, ArrayList<Integer> o2, ArrayList<Double> f2,
			ArrayList<Integer> o3, ArrayList<Double> f3, ArrayList<Integer> o4, ArrayList<Double> f4,
			ArrayList<String> vecgrp,
			/* Line 2 */
			ArrayList<Double> r12, ArrayList<Double> x12, ArrayList<Double> sbase12,
			/* Line 3 */
			ArrayList<Double> windv1, ArrayList<Double> nomv1, ArrayList<Double> ang1, 
			ArrayList<Double> rata1, ArrayList<Double> ratb1, ArrayList<Double> ratc1,
			ArrayList<Integer> cod1, ArrayList<String> cont1, ArrayList<Double> rma1,
			ArrayList<Double> rmi1, ArrayList<Double> vma1, ArrayList<Double> vmi1,
			ArrayList<Integer> ntp1, ArrayList<Integer> tab1, ArrayList<Double> cr1,
			ArrayList<Double> cx1, ArrayList<Double> cnxa1,
			/* Line 4 */
			ArrayList<Double> windv2, ArrayList<Double> nomv2)
	{
		xfm2List._busFrmIdx[ndx2Xfm] = busIndx.get(ndxXfm);
		xfm2List._busToIdx[ndx2Xfm] = busJndx.get(ndxXfm);
		xfm2List._idxRawXfm[ndx2Xfm] = ndxXfm;
				
		/* line 1 */
		xfm2List._i[ndx2Xfm] = i.get(ndxXfm);
		xfm2List._j[ndx2Xfm] = j.get(ndxXfm);
		xfm2List._ckt[ndx2Xfm] = ckt.get(ndxXfm);
		xfm2List._cw[ndx2Xfm] = cw.get(ndxXfm);
		xfm2List._cz[ndx2Xfm] = cz.get(ndxXfm);
		xfm2List._cm[ndx2Xfm] = cm.get(ndxXfm);
		xfm2List._mag1[ndx2Xfm] = mag1.get(ndxXfm);
		xfm2List._mag2[ndx2Xfm] = mag2.get(ndxXfm);
		xfm2List._nmetr[ndx2Xfm] = nmetr.get(ndxXfm);
		xfm2List._name[ndx2Xfm] = name.get(ndxXfm);
		xfm2List._stat[ndx2Xfm] = stat.get(ndxXfm);
		xfm2List._o1[ndx2Xfm] = o1.get(ndxXfm);
		xfm2List._f1[ndx2Xfm] = f1.get(ndxXfm);
		xfm2List._o2[ndx2Xfm] = o2.get(ndxXfm);
		xfm2List._f2[ndx2Xfm] = f2.get(ndxXfm);
		xfm2List._o3[ndx2Xfm] = o3.get(ndxXfm);
		xfm2List._f3[ndx2Xfm] = f3.get(ndxXfm);
		xfm2List._o4[ndx2Xfm] = o4.get(ndxXfm);
		xfm2List._f4[ndx2Xfm] = f4.get(ndxXfm);
		xfm2List._vecgrp[ndx2Xfm] = vecgrp.get(ndxXfm);

		/* line 2 */
		xfm2List._r[ndx2Xfm] = r12.get(ndxXfm);
		xfm2List._x[ndx2Xfm] = x12.get(ndxXfm);
		xfm2List._sbase[ndx2Xfm] = sbase12.get(ndxXfm);
		
		/* line 3 */
		xfm2List._windv1[ndx2Xfm] = windv1.get(ndxXfm);
		xfm2List._nomv1[ndx2Xfm] = nomv1.get(ndxXfm);
		xfm2List._ang1[ndx2Xfm] = ang1.get(ndxXfm);
		xfm2List._rata1[ndx2Xfm] = rata1.get(ndxXfm);
		xfm2List._ratb1[ndx2Xfm] = ratb1.get(ndxXfm);
		xfm2List._ratc1[ndx2Xfm] = ratc1.get(ndxXfm);
		xfm2List._cod1[ndx2Xfm] = cod1.get(ndxXfm);
		xfm2List._cont1[ndx2Xfm] = cont1.get(ndxXfm);
		xfm2List._rma1[ndx2Xfm] = rma1.get(ndxXfm);
		xfm2List._rmi1[ndx2Xfm] = rmi1.get(ndxXfm);
		xfm2List._vma1[ndx2Xfm] = vma1.get(ndxXfm);
		xfm2List._vmi1[ndx2Xfm] = vmi1.get(ndxXfm);
		xfm2List._ntp1[ndx2Xfm] = ntp1.get(ndxXfm);
		xfm2List._tab1[ndx2Xfm] = tab1.get(ndxXfm);
		xfm2List._cr1[ndx2Xfm] = cr1.get(ndxXfm);
		xfm2List._cx1[ndx2Xfm] = cx1.get(ndxXfm);
		xfm2List._cnxa1[ndx2Xfm] = cnxa1.get(ndxXfm);

		/* line 4 */
		xfm2List._windv2[ndx2Xfm] = windv2.get(ndxXfm);
		xfm2List._nomv2[ndx2Xfm] = nomv2.get(ndxXfm);
		
		/* preprocess */
		String cont1Tmp = xfm2List._cont1[ndx2Xfm];
		if (cont1Tmp.startsWith("-")) cont1Tmp = cont1Tmp.substring(1);
		if (cont1Tmp.equals("0") == false) {
			xfm2List._cont1Index[ndx2Xfm] = AuxPsseRaw.determineBusIndex(_buses, cont1Tmp);
			xfm2List._cont1Number[ndx2Xfm] = _buses.getI(xfm2List._cont1Index[ndx2Xfm]);
		}
	}

}
