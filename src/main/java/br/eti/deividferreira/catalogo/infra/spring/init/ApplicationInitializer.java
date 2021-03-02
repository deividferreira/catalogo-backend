package br.eti.deividferreira.catalogo.infra.spring.init;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Profile({"develop", "local"})
public class ApplicationInitializer {

	private List<InitialTask> tasks;

	public ApplicationInitializer(List<InitialTask> tasks) {
		this.tasks = tasks;
	}

	@PostConstruct
	public void orderTasks() {
		this.tasks.sort(AnnotationAwareOrderComparator.INSTANCE);
		log.info("Ordering tasks");
	}

	@EventListener
	public void onApplicationStart(ContextRefreshedEvent event) {
		log.info("Running application initializer tasks");
		this.tasks.forEach(InitialTask::perform);
		log.info("{} tasks performed", this.tasks.size());
	}
}
