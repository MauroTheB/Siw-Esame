package it.uniroma3.siw.taskmanager.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@Column()
    private String description;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @ManyToMany
    private List <User> members;
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)//we want to destroy all task when we delete the project
    @JoinColumn (name = "project_id")								//we want upload all task at the start of project
    private List <Task> tasks;

    /*
     *Costruttore vuoto
     */
    public Project(){
        this.members = new ArrayList<>();
    }
    /*
     *Costruttore con parametri
     */
    public Project(String description, String name) {
        this();                                                   //call empty constructor
        this.description = description;
        this.name = name;
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
    public List<User> getMembers() {
        return members;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public User getOwner() {
        return owner;
    }
    public void setMembers(List<User> members) {
        this.members = members;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    /*
     * ADD
     */
    public void addMemeber (User user) {
    	this.members.add(user);
    }
    public void addTask (Task task) {
    	this.tasks.add(task);
    }
    
    /*
     * Equals & HashCode & ToString
     * */
    @Override
    public String toString() {
        return "Project{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return Objects.equals(getDescription(), project.getDescription()) &&
                Objects.equals(getName(), project.getName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getName());
    }
}
