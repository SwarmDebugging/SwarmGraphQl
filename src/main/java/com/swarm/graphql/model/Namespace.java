package com.swarm.graphql.model;

import javax.persistence.*;

@Entity
public class Namespace {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

	String name;

	String fullPath;
	
	public Namespace() {
	}
	
	public Namespace(String name, String fullPath) {
		this.name = name;
		this.fullPath = fullPath;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Namespace namespace = (Namespace) o;

        return id.equals(namespace.id);
    }
	
	@Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
		return id + ": " + name;
	}

}
