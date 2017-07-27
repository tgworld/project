package my.casia.rules;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

public class JenaReasonerByName {
	
	public static void testExpert() {
		String ruleFile = "./mydata/Expert.rules";
		String ontoFile = "./mydata/Expert.owl";
		
		IReasoner famRea = ReasonerFactory.createFamilyReasoner();
		
		Model m = FileManager.get().loadModel("./mydata/Expert.owl");
		String NS = "http://www.owl-ontologies.com/Expert.owl#";

		Resource Jim = m.getResource(NS + "频数与频率");
		Resource Jena = m.getResource(NS + "概率初步");
		Resource John = m.getResource(NS + "利用频率估计概率");
		
		famRea.getInfModel(ruleFile, ontoFile);
		famRea.printInferResult(Jim, Jena);
		famRea.printInferResult(Jena, John);
		famRea.printInferResult(Jim, John);
		famRea.printInferResult(John,Jim);
		famRea.printInferResult(John,Jena);
	}
	
	public static void testFamily() {
		String ruleFile = "file:./family/family.rules";
		String ontoFile = "file:./family/family.owl";
		
		IReasoner famRea = ReasonerFactory.createFamilyReasoner();
		
		Model m = FileManager.get().loadModel("file:./family/family.owl");
		String NS = "http://www.semanticweb.org/ontologies/2010/0/family.owl#";
		
		
		/*
		Resource Lucy = m.getResource(NS + "Lucy");
		Resource Kate = m.getResource(NS + "Kate");
		Resource Sam = m.getResource(NS + "Sam");
		Resource James = m.createResource(NS + "James");
		Resource Anna = m.getResource(NS + "Anna");
		Resource Holly = m.createResource(NS + "Holly");
		*/
		
		Resource Jim = m.getResource(NS + "频数与频率");
		Resource Jena = m.getResource(NS + "概率初步");
		Resource John = m.getResource(NS + "利用频率估计概率");
		
		famRea.getInfModel(ruleFile, ontoFile);
		famRea.printInferResult(Jim, Jena);
		famRea.printInferResult(Jena, John);
		famRea.printInferResult(Jim, John);
		famRea.printInferResult(John,Jim);
	}
	
	public static void isSuccessorQuery(String name) {
		String ruleFile = "./mydata/Expert.rules";
		String ontoFile = "./mydata/Expert.owl";
		String queryString = "PREFIX Expert:<http://www.owl-ontologies.com/Expert.owl#> " +
	    	"SELECT ?expert " +
	    	"WHERE {?expert Expert:isSuccessor Expert:"+name+"} ";
		
		IReasoner famRea = ReasonerFactory.createFamilyReasoner();
		famRea.getInfModel(ruleFile, ontoFile);
		famRea.searchOnto(queryString);
	}public static void containQuery(String name) {
		String ruleFile = "./mydata/Expert.rules";
		String ontoFile = "./mydata/Expert.owl";
		String queryString = "PREFIX Expert:<http://www.owl-ontologies.com/Expert.owl#> " +
	    	"SELECT ?expert " +
	    	"WHERE {?expert Expert:contain Expert:"+name+"} ";
		
		IReasoner famRea = ReasonerFactory.createFamilyReasoner();
		famRea.getInfModel(ruleFile, ontoFile);
		famRea.searchOnto(queryString);
	}

	public static void main(String[] args) {
	
		isSuccessorQuery("概率初步");
		containQuery("频率与频数");
		//testExpert();
	}
	
}
