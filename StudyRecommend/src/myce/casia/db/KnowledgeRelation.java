package myce.casia.db;

import java.util.HashSet;
import java.util.Set;

public class KnowledgeRelation {

	private Integer id;
	private String knowledgepairs;
    private String Relation;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getKnowledgepairs() {
		return knowledgepairs;
	}
	public void setKnowledgepairs(String knowledgepairs) {
		this.knowledgepairs = knowledgepairs;
	}
	public String getRelation() {
		return Relation;
	}
	public void setRelation(String relation) {
		Relation = relation;
	}
	
	
	
	
}
