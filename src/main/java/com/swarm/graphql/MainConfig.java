package com.swarm.graphql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.swarm.graphql.model.*;
import com.swarm.graphql.repository.*;
import com.swarm.graphql.resolver.*;

@Configuration
public class MainConfig {
	
	@Bean
	public SessionResolver developerResolver(DeveloperRepository developerRepository, TaskRepository taskRepository) {
		return new SessionResolver(developerRepository, taskRepository);
	}
	
	@Bean
	public TaskResolver taskResolver(ProductRepository productRepository) {
		return new TaskResolver(productRepository);
	}
	
	@Bean
	public TypeResolver typeResolver(NamespaceRepository namespaceRepository, SessionRepository sessionRepository) {
		return new TypeResolver(namespaceRepository, sessionRepository);
	}
	
	@Bean
	public BreakpointResolver breakpointResolver(TypeRepository typeRepository) {
		return new BreakpointResolver(typeRepository);
	}
	
	@Bean
	public MethodResolver methodResolver(TypeRepository typeRepository) {
		return new MethodResolver(typeRepository);
	}
	
	@Bean
	public InvocationResolver invocationResolver(SessionRepository sessionRepository, MethodRepository methodRepository) {
		return new InvocationResolver(sessionRepository, methodRepository);
	}
	
	@Bean
	public EventResolver eventResolver(SessionRepository sessionRepository, MethodRepository methodRepository) {
		return new EventResolver(sessionRepository, methodRepository);
	}

	@Bean
	public Query query(DeveloperRepository developerRepository, 
			SessionRepository sessionRepository, 
			TaskRepository taskRepository, 
			ProductRepository productRepository,
			NamespaceRepository namespaceRepository,
			TypeRepository typeRepository,
			BreakpointRepository breakpointRepository,
			MethodRepository methodRepository,
			InvocationRepository invocationRepository,
			EventRepository eventRepository) {
		
		return new Query(developerRepository, sessionRepository, taskRepository, productRepository, namespaceRepository, typeRepository, breakpointRepository, methodRepository, invocationRepository, eventRepository);
	}
	
	@Bean
	public CommandLineRunner test(
			DeveloperRepository developerRepository, 
			SessionRepository sessionRepository,
			TaskRepository taskRepository,
			ProductRepository productRepository,
			NamespaceRepository namespaceRepository,
			TypeRepository typeRepository,
			BreakpointRepository breakpointRepository,
			MethodRepository methodRepository,
			InvocationRepository invocationRepository,
			EventRepository eventRepository) {
		
		return (args) -> {
			Developer developer1 = new Developer("developer1.name", "developer1.color");
			Developer developer2 = new Developer("developer2.name", "developer2.color");
			
			Product product1 = new Product("product1.name");
			Product product2 = new Product("product2.name");
			
			Task task1 = new Task(product1, "task1.title","task1.url","task1.color");
			Task task2 = new Task(product2, "task2.title","task2.url","task2.color");
			
			Session session1 = new Session(developer1, task1, "session1.description", "session1.label","session1.purpose","session1.project");
			Session session2 = new Session(developer2, task2, "session2.description", "session2.label","session2.purpose","session2.project");
			
			Namespace namespace1 = new Namespace("namespace1.name","namespace1.fullPath");
			Namespace namespace2 = new Namespace("namespace2.name","namespace2.fullPath");
			
			Type type1 = new Type(namespace1, session1, "type1.fullName","type1.fullPath", "type1.name","type1.source");
			Type type2 = new Type(namespace2, session2, "type2.fullName","type2.fullPath", "type2.name","type2.source");
			
			Breakpoint breakpoint1 = new Breakpoint(type1,"breakpoint1.start","breakpoint1.end",59);
			Breakpoint breakpoint2 = new Breakpoint(type2,"breakpoint2.start","breakpoint2.end",374);
			
			Method method1 = new Method(type1,"method1.key","method1.name","method1.signature");
			Method method2 = new Method(type2,"method2.key","method2.name","method2.signature");
			
			Invocation invocation1 = new Invocation(method1,method2,session1);
			
			Event event1 = new Event(method2,session2,"event1.charStart","event1.charEnd",349,"event1.detail","event1.kind");
			
			developerRepository.save(developer1);
			developerRepository.save(developer2);
			productRepository.save(product1);
			productRepository.save(product2);
			taskRepository.save(task1);
			taskRepository.save(task2);
			sessionRepository.save(session1);
			sessionRepository.save(session2);
			namespaceRepository.save(namespace1);
			namespaceRepository.save(namespace2);
			typeRepository.save(type1);
			typeRepository.save(type2);
			breakpointRepository.save(breakpoint1);
			breakpointRepository.save(breakpoint2);
			methodRepository.save(method1);
			methodRepository.save(method2);
			invocationRepository.save(invocation1);
			eventRepository.save(event1);
			
			System.out.println("test git");
		};
	}

}