package de.hsmainz.cs.semgis.arqextension.benchmark;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.apache.jena.query.QueryParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import de.hsmainz.cs.semgis.arqextension.example.JDBCConnection;
import de.hsmainz.cs.semgis.arqextension.example.TripleStoreConnection;

public class BenchmarkExecutor {

	static long startTime = System.nanoTime();

	static long endTime = System.nanoTime();

	public static JSONObject benchmark(String queryfolder, String dataset,Boolean sql) {
		System.out.println("BENCHMARK START!!!!!!");
		Map<String, Long> result = new TreeMap<String, Long>();
		startTime = System.nanoTime();
		String fileending=(sql?"sql":"sparql");
		System.out.println("StartTime: " + startTime);
		JSONObject resultjson=new JSONObject();
		if (dataset.equals("all")) {
			for (String model : TripleStoreConnection.INSTANCE.modelmap.keySet()) {
				String[] directories = new File("queries").list(new FilenameFilter() {
					  @Override
					  public boolean accept(File current, String name) {
					    return new File(current, name).isDirectory();
					  }
					});
				Arrays.sort(directories);
				for (String datatype : directories) {
					String[] directories2 = new File("queries/"+datatype).list(new FilenameFilter() {
						  @Override
						  public boolean accept(File current, String name) {
						    return new File(current, name).isDirectory();
						  }
						});
						Arrays.sort(directories2);
						for(String cat:directories2) {
							JSONObject catarray=new JSONObject();
							resultjson.put(datatype+"_"+cat, catarray);
							JSONObject category=new JSONObject();
							resultjson.put(datatype+"_"+cat, category);
							category.put("array", catarray);
							Double cattotal=0.,catobjtotal=0.;
						String[] queries=new File("queries/"+datatype+"/"+cat).list();
						for (String queryfile : queries) {
							if (queryfile.contains(fileending)) {
								System.out.println("queries/"+datatype+"/"+cat+"/"+queryfile);
								if (!new File("queries/"+datatype+"/"+cat+"/"+queryfile).isDirectory()) {
									String content;
									try {
										long sTime = System.nanoTime();
										System.out.println("Substarttime: " + sTime);
										content = readFile("queries/"+datatype+"/"+cat+"/" + queryfile, StandardCharsets.UTF_8);
										System.out.println("Query: " + content);
										Integer resultSize=0;
										if(sql) {
											try {	
												if(content.contains("geographica_vector")) {
													String dbtable=dataset.replace(".ttl","");	
													if(dataset.contains(".")) {
														dbtable.substring(0,dbtable.lastIndexOf('.'));
													}
													content=content.replace("geographica_vector",dbtable);
												}
												JDBCConnection.getInstance().executeQuery(content);
												resultSize=JDBCConnection.getInstance().resultSize;
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}else {
											TripleStoreConnection.executeQuery(content, model);
											resultSize=TripleStoreConnection.resultSetSize;
										}
										long eTime = System.nanoTime();
										System.out.println("Subendtime: " + eTime);
										JSONObject res=new JSONObject();
										res.put("experiment", queryfile.replace(".sparql", "").replace(".sql",""));
										res.put("result", (eTime - sTime) / 1000000);
										res.put("dataset", model);
										res.put("resultsize", resultSize);
										catarray.put(queryfile.replace(".sparql", "").replace(".sql",""),res);
										result.put(queryfile.replace(".sparql", "").replace(".sql","") + ";" + model + ";"
												+ resultSize, (eTime - sTime) / 1000000);
										cattotal+=(eTime - sTime) / 1000000;
										catobjtotal+=resultSize;
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
						category.put("cattotal", cattotal);
						category.put("catobjtotal", catobjtotal);
						}
				}
			}

		} else {
			String[] directories = new File("queries").list(new FilenameFilter() {
				  @Override
				  public boolean accept(File current, String name) {
				    return new File(current, name).isDirectory();
				  }
				});
			Arrays.sort(directories);
			for (String datatype : directories) {
				String[] directories2 = new File("queries/"+datatype).list(new FilenameFilter() {
					  @Override
					  public boolean accept(File current, String name) {
					    return new File(current, name).isDirectory();
					  }
					});
				Arrays.sort(directories2);
				for(String cat:directories2) {	
					JSONObject catarray=new JSONObject();
					resultjson.put(datatype+"_"+cat, catarray);
					JSONObject category=new JSONObject();
					resultjson.put(datatype+"_"+cat, category);
					category.put("array", catarray);
					Double cattotal=0.,catobjtotal=0.;
				System.out.println("Cat: "+cat);
				String[] queries=new File("queries/"+datatype+"/"+cat).list();
				Arrays.sort(queries);
					for (String queryfile : queries) {
						System.out.println(queryfile);
						if (queryfile.contains(fileending)) {
							System.out.println("queries/"+datatype+"/"+cat+"/"+queryfile);
							if (!new File("queries/"+datatype+"/"+cat+"/"+queryfile).isDirectory()) {
								String content;
								try {
									long sTime = System.nanoTime();
									content = readFile("queries/"+datatype+"/"+cat+"/" + queryfile, StandardCharsets.UTF_8);
									System.out.println("Query: " + content);
									Integer resultSize=0;
									if(sql) {
										try {
											if(content.contains("geographica_vector")) {
												String dbtable=dataset.replace(".ttl","");			
												/*if(dataset.contains(".")) {
													dbtable.substring(0,dbtable.lastIndexOf('.'));
												}*/
												content=content.replace("geographica_vector",dbtable);
											}
											//if(!content.contains("snow"))
												JDBCConnection.getInstance().executeQuery(content);
											resultSize=JDBCConnection.getInstance().resultSize;
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}else {
										try {
										TripleStoreConnection.executeQuery(content, dataset);
										resultSize=TripleStoreConnection.resultSetSize;
										}catch(Exception e) {
											e.printStackTrace();
										}
									}
									long eTime = System.nanoTime();
									JSONObject res=new JSONObject();
									res.put("experiment", queryfile.replace(".sparql", "").replace(".sql",""));
									res.put("result", (eTime - sTime) / 1000000);
									res.put("dataset", dataset);
									res.put("resultsize", resultSize);
									catarray.put(queryfile.replace(".sparql", "").replace(".sql",""),res);
									result.put(queryfile.replace(".sparql", "").replace(".sql","") + ";" + dataset + ";"
											+ resultSize, (eTime - sTime) / 1000000);
									cattotal+=(eTime - sTime) / 1000000;
									catobjtotal+=resultSize;
								} catch (IOException e) {
									// TODO Auto-generated catch block
									//e.printStackTrace();
								}
							}
						}
					}
					category.put("cattotal", cattotal);
					category.put("catobjtotal", catobjtotal);
				}
			}
		}
		endTime = System.nanoTime();
		System.out.println("EndTime: " + endTime);
		long timeElapsed = endTime - startTime;
		System.out.println("Duration: " + timeElapsed / 1000000 + "ms" + " - " + timeElapsed / 1000000 / 1000 + "s");
		result.put("all", timeElapsed / 1000000);
		return resultjson;
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public static JSONArray resultMapToJSON(Map<String, Long> resultMap) {
		JSONArray result = new JSONArray();
		for (String experiment : resultMap.keySet()) {
			JSONObject subresult = new JSONObject();
			if (experiment.contains(";")) {
				subresult.put("experiment", experiment.split(";")[0]);
				subresult.put("dataset", experiment.split(";")[1]);
				subresult.put("resultsize", experiment.split(";")[2]);
			} else {
				subresult.put("experiment", experiment);
			}
			subresult.put("result", resultMap.get(experiment));
			result.put(subresult);
		}
		return result;
	}
	
}
