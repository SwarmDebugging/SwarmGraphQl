package com.swarm.graphql.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.swarm.graphql.model.Namespace;
import com.swarm.graphql.repository.NamespaceRepository;

public class QueryNamespace  implements GraphQLQueryResolver{

	private NamespaceRepository namespaceRepository;
	
	public QueryNamespace(NamespaceRepository namespaceRepository) {
		// TODO Auto-generated constructor stub
		this.namespaceRepository = namespaceRepository;
	}

    public Namespace namespaceByFullPath(String fullPath){
    	return namespaceRepository.findByFullPath(fullPath);
    }
	
}
