/*******************************************************************************
 * Copyright (c) 2016 The University of Reading
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the University of Reading, nor the names of the
 *    authors or contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package de.hsmainz.cs.semgis.arqextension.util.parsers;

import org.apache.sis.coverage.Category;
import org.apache.sis.coverage.SampleDimension;
import org.apache.sis.coverage.grid.GridCoverage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.opengis.metadata.spatial.DimensionNameType;

public class CoverageJsonWriter {
	GridCoverage coverage;

	public CoverageJsonWriter(GridCoverage coverage) {
		this.coverage=coverage;
	}
	
	public static JSONObject coverageToCovJSON(GridCoverage coverage) {
		JSONObject result=new JSONObject();
		result.put("type","Coverage");
		JSONObject domain=new JSONObject();
		result.put("domain",domain);
		domain.put("type", "Domain");
		domain.put("domainType", "Grid");
		JSONArray geocoords=new JSONArray();
		JSONArray tempcoords=new JSONArray();
		JSONObject axes=new JSONObject();
		domain.put("axes",axes);
		for(int i=0;i<coverage.getGridGeometry().getExtent().getDimension();i++) {
			if(coverage.getGridGeometry().getExtent().getAxisType(i).get().equals(DimensionNameType.COLUMN)) {
				axes.put("x",new JSONObject());
				geocoords.put("x");
			}
			else if(coverage.getGridGeometry().getExtent().getAxisType(i).get().equals(DimensionNameType.ROW)) {
				axes.put("y",new JSONObject());
				geocoords.put("y");
			}
			else if(coverage.getGridGeometry().getExtent().getAxisType(i).get().equals(DimensionNameType.VERTICAL)) {
				axes.put("z",new JSONObject());
				geocoords.put("z");
			}
			else if(coverage.getGridGeometry().getExtent().getAxisType(i).get().equals(DimensionNameType.TIME)) {
				axes.put("t",new JSONObject());
				tempcoords.put("t");
			}
		}
		JSONArray referencing=new JSONArray();
		domain.put("referencing", referencing);
		if(!geocoords.isEmpty()) {
			JSONObject crs=new JSONObject();
			JSONObject tcrs=new JSONObject();
			crs.put("system",tcrs);
			crs.put("coordinates",geocoords);
			referencing.put(crs);
			tcrs.put("type", "GeopraphicCRS");
			try {
				if(coverage.getCoordinateReferenceSystem()!=null) {
					tcrs.put("id", coverage.getCoordinateReferenceSystem().getName());
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(!tempcoords.isEmpty()) {
			JSONObject tcrs=new JSONObject();
			JSONObject tcrstype=new JSONObject();
			tcrs.put("coordinates",new JSONArray());
			tcrs.put("system",tcrstype);
			tcrs.put("type", "TemporalRS");
			tcrs.put("calendar", "Gregorian");
			referencing.put(tcrs);
		}
		//if(coverage.getGridGeometry().getTemporalExtent().length!=0) {
			
		//}
		JSONObject ranges=new JSONObject();
		result.put("ranges",ranges);
		JSONObject parameters=new JSONObject();
		result.put("parameters", parameters);
		for(SampleDimension dimension:coverage.getSampleDimensions()) {
			JSONObject sampledim=new JSONObject();
			parameters.put(dimension.getName().toString(),sampledim);
			JSONObject paramrange=new JSONObject();
			ranges.put(dimension.getName().toString(),paramrange);
			paramrange.put("type","NdArray");
			paramrange.put("dataType","float");
			JSONArray axiss=new JSONArray();
			paramrange.put("axisNames",axiss);
			for(String ax:axes.keySet()) {
				axiss.put(ax);
			}
			paramrange.put("shape",new JSONArray());
			paramrange.put("values",new JSONArray());
			sampledim.put("type","Parameter");
			JSONObject description=new JSONObject();
			sampledim.put("description",description);
			description.put("en",dimension.getName().toString());
			if(dimension.getUnits()!=null && dimension.getUnits().isPresent()) {
				JSONObject unit=new JSONObject();
				sampledim.put("unit",unit);
				JSONObject unitlabel=new JSONObject();
				unit.put("label",unitlabel);
				unitlabel.put("en",dimension.getUnits().get().getName());
				JSONObject symbol=new JSONObject();
				unit.put("symbol",symbol);
				symbol.put("value",dimension.getUnits().get());
			}
			JSONObject observedProperty=new JSONObject();
			sampledim.put("observedProperty",observedProperty);
			JSONObject catencoding=null;
			if(dimension.getCategories()!=null && !dimension.getCategories().isEmpty()) {
				catencoding=new JSONObject();
				JSONArray categories=new JSONArray();
				observedProperty.put("categories",categories);
				for(Category cat:dimension.getCategories()) {
					JSONObject category=new JSONObject();
					JSONObject catlabel=new JSONObject();
					category.put("label",catlabel);
					catlabel.put("en",dimension.getUnits().get().getName());
					JSONObject catdesc=new JSONObject();
					category.put("description",catdesc);
					catdesc.put("en",dimension.getUnits().get().getName());
					categories.put(category);
				}
			}
			if(catencoding!=null) {
				observedProperty.put("categoryEncoding",catencoding);
			}
			JSONObject oPLabel=new JSONObject();
			observedProperty.put("label",oPLabel);
			/*oPLabel.put("en",dimension.)
			
			dimension.getCategories();
			dimension.getMeasurementRange()*/
		}


		return result;
	}
	
}
