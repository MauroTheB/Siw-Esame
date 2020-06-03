package it.uniroma3.siw.taskmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.taskmanager.model.Task;
import it.uniroma3.siw.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	/*
	 * Metodo che ottine una task dal db
	 * @param ID del task
	 * @return ritorna il task trovato
	 */
	@Transactional
	public Task getTask(Long id) {
		Optional <Task> risultato = this.taskRepository.findById(id);
		return risultato.orElse(null);
	}
	/*
	 * Metodo che salva un task nel db e lo segna come completato
	 * @param il TASK da salvare
	 * @return il TASK salvato
	 */
	@Transactional
	public Task saveTask(Task task) {
		return this.taskRepository.save(task);
	}
	/*
	 * Metodo che imposta il campo completed a true
	 * @param il TASK da mettere a completo
	 * @return il TASK salvato
	 */
	@Transactional
	public Task setCompleted(Task task) {
		task.setCompleted(true);
		return this.taskRepository.save(task);
	}
	/*
	 * Metodo che cancella una TASK dal DB
	 * @param il TASK 
	 */
	@Transactional
	public void delateTask(Task task) {
		this.taskRepository.delete(task);
	}
}
