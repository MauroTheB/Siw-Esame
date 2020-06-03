package it.uniroma3.siw.taskmanager.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, length = 30)
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(updatable = false, nullable = false)
    private LocalDateTime creationLocalDateTime;

    @Column(nullable = false)
    private LocalDateTime lastUpdateLocalDateTime;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private List<Project> ownerProject;

    @ManyToMany(mappedBy = "members")
    private List <Project> visibleProjects;



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
    * tutti i parametri del costruttore
    */
    public User(String username, String password, String firstName, String lastName) {
        this();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /*
     *empty constructor
     */
    public User(){
        this.ownerProject = new ArrayList<>();
        this.visibleProjects = new ArrayList<>();
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
    public void setOwnerProject(List<Project> myProjects) {
        this.ownerProject = myProjects;
    }
    public void setVisibleProjects(List<Project> visibleProjects) {
        this.visibleProjects = visibleProjects;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setCreationLocalDateTime(LocalDateTime creationLocalDateTime) {
        this.creationLocalDateTime = creationLocalDateTime;
    }
    public void setLastUpdateLocalDateTime(LocalDateTime lastUpdateLocalDateTime) {
        this.lastUpdateLocalDateTime = lastUpdateLocalDateTime;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDateTime getCreationLocalDateTime() {
        return creationLocalDateTime;
    }
    public LocalDateTime getLastUpdateLocalDateTime() {
        return lastUpdateLocalDateTime;
    }
    public List<Project> getOwnerProject() {
        return ownerProject;
    }
    public List<Project> getVisibleProjects() {
        return visibleProjects;
    }

    /*
    * Equals & HashCode & ToString
    * */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", creationLocalDateTime=" + creationLocalDateTime +
                ", lastUpdateLocalDateTime=" + lastUpdateLocalDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getCreationLocalDateTime(), user.getCreationLocalDateTime()) &&
                Objects.equals(getLastUpdateLocalDateTime(), user.getLastUpdateLocalDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getFirstName(), getLastName(), getCreationLocalDateTime(), getLastUpdateLocalDateTime());
    }
}
