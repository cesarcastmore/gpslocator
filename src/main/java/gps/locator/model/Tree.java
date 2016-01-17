package gps.locator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tree {
	
	@Id @GeneratedValue
	private Long treeId;
	private Long nodeId;
	private Long parentId;
	

	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getTreeId() {
		return treeId;
	}
	public void setTreeId(Long treeId) {
		this.treeId = treeId;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	
	
	
	

}