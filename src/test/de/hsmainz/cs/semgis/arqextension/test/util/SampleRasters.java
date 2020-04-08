package de.hsmainz.cs.semgis.arqextension.test.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.sun.jersey.core.util.Base64;

public class SampleRasters {

	public static final String rasterLiteral1="";
	
	public static final String rasterBand="";
	
	public static String wkbString1="00000000013FF00000000000003FF00000000000000000000000000000000000000000000000000000000000000000000000000000000010E600020002040000010100";
	
	public static String wkbString2="0000000001407F4000000000003FF00000000000000000000000000000000000000000000000000000000000000000000000000000000010E600020002040022283800";

	public static String hexwkbString1="010000000000000000000000400000000000000840000000000000E03F000000000000E03F000000000000000000000000000000000F1000000A001400";
	
	public static final String covJSONString1="";
	
	public static String rasterWKB;
		
	public static SampleRasters rasters;		
			
	protected SampleRasters()  {
		String inputFile = "wkb.bin";
		InputStream is;
		try {
			is = new FileInputStream(inputFile);
			rasterWKB=new String(Base64.encode(IOUtils.toByteArray(is)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SampleRasters getInstance()  {
		if(rasters==null) {
			rasters=new SampleRasters();
		}
		return rasters;
	}
	
}
