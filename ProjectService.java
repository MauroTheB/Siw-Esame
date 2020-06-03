package it.uniroma3.siw.taskmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	/*
	 * Questo metodo trova un progetto dal suo id nel DB
	 * @param ID del progetto
	 * @return il PROGETTO se non esiste NULL
	 */
	 @Transactional
	 public Project getProject(Long id) {
		Optional <Project> result = this.projectRepository.findById(id);
		return result.orElse(null);
	}
	 /*
	  * Questo medoto salava un progetto nel DB
	  * @param l'ogetto PROGETTO
	  * @return il PROGETTO salvato
	  * @exception IllegalArgumentException in caso viene dato progetto nullo
	  */
	 @Transactional
	 public Project saveProject(Project progetto) {
		return this.projectRepository.save(progetto);
	}
	 /*
	  * Questo metodo cancella un progetto 
	  * @param l'ogetto PROGETTO
	  */
	 @Transactional 
	 public void delateProject(Project progetto) {
		 this.projectRepository.delete(progetto);
	 }
	 /*
	  * metodo che condivide i progetti con il proprio user
	  * @param PROGETTO da condivide e lo USER con cui condividerlo
	  * @return il PROGETTO condiviso
	  *  */
	 @Transactional 
	 public Project shareProjectWithUser(Project progetto, User user ) {
		 progetto.addMemeber(user);
		 return this.projectRepository.save(progetto);
	 }
	 
	 
}
