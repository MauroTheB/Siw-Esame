package it.uniroma3.siw.taskmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.model.User;
/*
* https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
*/
public interface ProjectRepository extends CrudRepository <Project, Long> {

	/*
	 * Metodo che prende dal DB tutti i progetti visibili di un determinato USER
	 * @param è uno USER
	 * @return una lista di PROGETTI con la visibilità dello USER
	 * */
	public List <Project> findByMembers(User user);
	/*
	 * Metodo che prende dal DB tutti i progetti posseduti di un determinato USER
	 * @param è uno USER
	 * @return una lista di PROGETTI possediti dallo USER
	 * */
	public List <Project> findByOwner(User user);
}
