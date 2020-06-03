package it.uniroma3.siw.taskmanager;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.repository.ProjectRepository;
import it.uniroma3.siw.taskmanager.repository.TaskRepository;
import it.uniroma3.siw.taskmanager.repository.UserRepository;
import it.uniroma3.siw.taskmanager.service.ProjectService;
import it.uniroma3.siw.taskmanager.service.TaskService;
import it.uniroma3.siw.taskmanager.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
class TaskmanagerApplicationTests {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	/*
	 * Cancella tutti i dati dal db prima di avviare i test
	 * */
	@BeforeEach
	 void delateAll() {
		System.out.println("Procedura avviamento cancellazione dati dal DB...");
		this.projectRepository.deleteAll();
		this.userRepository.deleteAll();
		this.taskRepository.deleteAll();
		System.out.println("COMPLETATO");
	}
	
	
	@Test
	void testUpdateUser() {
		//Salvataggio primo user nel DB
		User user1 = new User("xxSuperBoy2008xx", "password", "PierAngelo", "Cassini");
		user1 = this.userService.saveUser(user1);
		assertEquals(user1.getFirstName(), "PierAngelo");
		assertEquals(user1.getLastName(), "Cassini");
		assertEquals(user1.getUsername(), "xxSuperBoy2008xx");
		
		//Salvataggio secondo user nel DB
		User user2 = new User("m4ri0", "password", "Danilo", "Casni");
		user2 = this.userService.saveUser(user2);
		assertEquals(user2.getFirstName(), "Danilo");
		assertEquals(user2.getLastName(), "Casni");
		assertEquals(user2.getUsername(), "m4ri0");
	}
	@Test 
	void testUpdateProject(){
		//Creo e salvo lo user1
		User user1 = new User("xxSuperBoy2008xx", "password", "PierAngelo", "Cassini");
		user1 = this.userService.saveUser(user1);
		
		//Creo e salvo lo user2
		User user2 = new User("m4ri0", "password", "Danilo", "Casni");
		user2 = this.userService.saveUser(user2);
		
		//salva il primo progetto nel DB
		Project progetto1 = new Project("test progetto1","progetto1");
		progetto1 = this.projectService.saveProject(progetto1);
		progetto1.setOwner(user1);
		assertEquals(progetto1.getDescription(),"test progetto1");
		assertEquals(progetto1.getName(),"progetto1");
		assertEquals(progetto1.getOwner(), user1);
		
		//salva il seconodo progetto nel DB
		Project progetto2 = new Project("test progetto2","progetto2");
		progetto2 = this.projectService.saveProject(progetto2);
		progetto2.setOwner(user2);
		assertEquals(progetto2.getDescription(),"test progetto2");
		assertEquals(progetto2.getName(),"progetto2");
		assertEquals(progetto2.getOwner(), user2);
		
		//diamo la visibilità del project 1 allo user 2
		progetto1 = this.projectService.shareProjectWithUser(progetto1, user2);
		}
	
		@Test
		void testVisibilityProjectUser() {
			//Creo e salvo lo user1
			User user1 = new User("xxSuperBoy2008xx", "password", "PierAngelo", "Cassini");
			user1 = this.userService.saveUser(user1);
			
			//Creo e salvo lo user2
			User user2 = new User("m4ri0", "password", "Danilo", "Casni");
			user2 = this.userService.saveUser(user2);
			
			//salva il primo progetto nel DB
			Project progetto1 = new Project("test progetto1","progetto1");
			progetto1 = this.projectService.saveProject(progetto1);
			progetto1.setOwner(user1);
			
			//salva il seconodo progetto nel DB
			Project progetto2 = new Project("test progetto2","progetto2");
			progetto2 = this.projectService.saveProject(progetto2);
			progetto2.setOwner(user1);
			
			//test project1 posseduto da user1
			List <Project> progetti = this.projectRepository.findByOwner(user1);
			assertEquals(progetti.size(), 2);
			assertEquals(progetti.get(0), progetto1);
			assertEquals(progetti.get(1), progetto2);
			
			//progetti visibili da user2 
			List <User> project1Members = this.userRepository.findByVisibleProjects(progetto1);
			assertEquals(project1Members.get(0), user1);
			assertEquals(project1Members.size(), 1);
			
		}
	}
	
	
	
	
	
	


