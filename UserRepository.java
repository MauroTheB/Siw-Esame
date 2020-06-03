package it.uniroma3.siw.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.model.User;

/*
* https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
*/

public interface UserRepository extends CrudRepository<User, Long>{
     
	/*
	 * Metodo che prende dal DB uno user sulla base del suo firstName
	 * @param un firstName
	 * @return uno USER
	 * */
	public Optional<User> findByFirstName(String primoNome);
	/*
	 * Metodo che prende dal DB uno user sulla base del suo username
	 * @param uno username
	 * @return uno USER
	 * */
	public Optional<User> findByUsername(String username);
	/*
	 * Metodo che prende dal DB una lista di UTENTI visibili dell PROGETTO
	 * @param uno PROJECT
	 * @return lista di USER
	 * */
	public List<User> findByVisibleProjects(Project progetto);
	
	
	
}
