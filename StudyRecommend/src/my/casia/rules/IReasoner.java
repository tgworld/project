package my.casia.rules;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * @purpose ����ӿڣ�ʵ�ֱ�������
 * @author zhaohongjie
 * 
 */
public interface IReasoner {

	public InfModel getInfModel(String ontPath, String rulePath);
	public InfModel getInfModel(String rulePath, OntModel model);
	public void printInferResult(Resource a, Resource b);
	public void searchOnto(String queryString);
	
}
