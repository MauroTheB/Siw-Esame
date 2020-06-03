package it.uniroma3.siw.taskmanager.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@Column (nullable = false, length = 30)
    private String name;

    @Column (nullable = false, length = 300)
    private String description;

    @Column (nullable = false)
    private boolean completed;

    @Column (nullable = false, updatable = false)
    private LocalDateTime creationLocalDateTime;

    @Column (nullable = false)
    private LocalDateTime lastUpdateLocalDateTime;

    /*
     *empty constructor
     */
    public Task (){}

    /*
     * tutti i parametri del costruttore
     */
    public Task(String name, String description, boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    /*
     * Aggiornamento alla prima creazione e ad ogni aggiornamento
     * */
    @PrePersist
    protected void onPersist(){
        this.lastUpdateLocalDateTime = LocalDateTime.now();
        this.creationLocalDateTime= LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.lastUpdateLocalDateTime = LocalDateTime.now();
    }
    /*
     * Getter & Setter
     * */
    public int getId() {
		return id;
	}
    public void setId(int id) {
		this.id = id;
	}
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isCompleted() {
        return completed;
    }
    public LocalDateTime getCreationLocalDateTime() {
        return creationLocalDateTime;
    }
    public LocalDateTime getLastUpdateLocalDateTime() {
        return lastUpdateLocalDateTime;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public void setCreationLocalDateTime(LocalDateTime creationLocalDateTime) {
        this.creationLocalDateTime = creationLocalDateTime;
    }
    public void setLastUpdateLocalDateTime(LocalDateTime lastUpdateLocalDateTime) {
        this.lastUpdateLocalDateTime = lastUpdateLocalDateTime;
    }

    /*
     * Equals & HashCode & ToString
     * */
    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", creationLocalDateTime=" + creationLocalDateTime +
                ", lastUpdateLocalDateTime=" + lastUpdateLocalDateTime +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return isCompleted() == task.isCompleted() &&
                Objects.equals(getName(), task.getName()) &&
                Objects.equals(getDescription(), task.getDescription()) &&
                Objects.equals(getCreationLocalDateTime(), task.getCreationLocalDateTime()) &&
                Objects.equals(getLastUpdateLocalDateTime(), task.getLastUpdateLocalDateTime());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), isCompleted(), getCreationLocalDateTime(), getLastUpdateLocalDateTime());
    }
}
