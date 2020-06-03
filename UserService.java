package it.uniroma3.siw.taskmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/*
	 * Questo metodo trova uno User sulla base del suo ID
	 * @param è l'ID che si cerca
	 * @return ritorna lo USER
	 */
	@Transactional
	public User getUser (Long ID) {
		 Optional <User> result = this.userRepository.findById(ID);
		 return result.orElse(null);
	}
	/*
	 * Questo metodo trova uno User sulla base del suo NOME
	 * @param è l'NOME che si cerca
	 * @return ritorna lo USER
	 */
	@Transactional
	public User getUser (String name) {
		 Optional <User> result = this.userRepository.findByFirstName(name);
		 return result.orElse(null);
	}
	/*
	 * Questo metodo salva uno User sulla base del suo NOME
	 * @param è lo USER che si cerca
	 * @return ritorna lo USER salvato
	 */
	@Transactional
	public User saveUser (User user) {
		return this.userRepository.save(user);
	}
	/*
	 * Questo metodo ritorna la lista di tutti gli user presenti sul DB
	 * @return lista di USER
	 */
	@Transactional
	public List<User> getAllUser () {
	List <User> lista = new ArrayList<>();
	Iterable<User> iteratore = this.userRepository.findAll();
	for (User user: iteratore)
		lista.add(user);
	return lista;
	}
	
	
	
	
	
	
}
