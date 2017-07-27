package my.casia.rules;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.shared.RulesetNotFoundException;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;


public class ReasonerImpl implements IReasoner {
	
	private InfModel inf = null;

	/**
	 * ��ȡһ������ӿ�
	 * @param path
	 * @return
	 * @throws RulesetNotFoundException
	 */
	private GenericRuleReasoner getReasoner(String path) throws RulesetNotFoundException {
		
		List<Rule> rules = Rule.rulesFromURL(path);  //"file:./family/family.rules"
		GenericRuleReasoner reasoner = new GenericRuleReasoner(rules);
		reasoner.setOWLTranslation(true);
		reasoner.setDerivationLogging(true);
		reasoner.setTransitiveClosureCaching(true);
		return reasoner;
		
	}
	
	/**
	 * ��ȡ����ı���
	 * @param path
	 * @return
	 * @throws FileNotFoundException 
	 */
	private OntModel getOntModel(String path) throws FileNotFoundException {
		
		Model model = ModelFactory.createDefaultModel();
		model.read(new FileInputStream(path),"");  //"file:./family/family.owl"
		OntModel ont = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF,
				model);
		Resource configuration = ont.createResource(); //a new anonymous resource linked to this model
		configuration.addProperty(ReasonerVocabulary.PROPruleMode
				, "hybrid");
		return ont;
		
	}
	
	/**
	 * InfModel�ǶԳ���Model����չ��֧���κ���ص���������
	 * @param ontPath
	 * @param rulePath
	 * @return
	 */
	public InfModel getInfModel(String rulePath, String ontPath) {
		
		try {
			this.inf = ModelFactory.createInfModel(getReasoner(rulePath), getOntModel(ontPath));
		} catch (RulesetNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.inf;
		
	}
	
	/**
	 * InfModel�ǶԳ���Model����չ��֧���κ���ص���������
	 * @param model
	 * @param rulePath
	 * @return
	 */
	public InfModel getInfModel(String rulePath, OntModel model) {
		this.inf = ModelFactory.createInfModel(getReasoner(rulePath), model);
		return this.inf;
	}
	
	/**
	 * ��ӡ������
	 * @param a
	 * @param b
	 */
	public void printInferResult(Resource a, Resource b) {
		
		StmtIterator stmtIter = this.inf.listStatements(a, null, b);
		if (!stmtIter.hasNext()) {
			System.out.println("there is no relation between "
				      + a.getLocalName() + " and " + b.getLocalName());
			System.out.println("\n-------------------\n");
		}
		while (stmtIter.hasNext()) {
			Statement s = stmtIter.nextStatement();
			System.out.println("Relation between " + a.getLocalName() + " and "
				      + b.getLocalName() + " is :");
			System.out.println(a.getLocalName() + " "
				      + s.getPredicate().getLocalName() + " " + b.getLocalName());
			System.out.println("\n-------------------\n");
		}
		
	}
	
	public void searchOnto(String queryString) {
		
		Query query = QueryFactory.create(queryString);	  
	    QueryExecution qe = QueryExecutionFactory.create(query, this.inf);
	    ResultSet results = qe.execSelect();
	    ResultSetFormatter.out(results);
	    qe.close();
	    
	}
	
}
