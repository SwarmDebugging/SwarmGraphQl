package com.swarm.graphql.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.swarm.graphql.model.Method;
import com.swarm.graphql.model.Session;
import com.swarm.graphql.repository.*;

public class QueryMethod implements GraphQLQueryResolver {
	
	private MethodRepository methodRepository;
	private SessionRepository sessionRepository;
	
	public QueryMethod(MethodRepository methodRepository) {
		this.methodRepository = methodRepository;
	}
    
    public Iterable<Method> methodsByTypeId(Long typeId){
    	return methodRepository.findByTypeId(typeId);
    }
    
    public Iterable<Method> startingMethodsBySessionId(Long sessionId){
    	Session session = sessionRepository.findOne(sessionId);
    	return methodRepository.getStartingMethods(session);
    }
    
    public Iterable<Method> endingMethodsBySessionId(Long sessionId){
    	Session session = sessionRepository.findOne(sessionId);
    	return methodRepository.getEndingMethods(session);
    }

}
