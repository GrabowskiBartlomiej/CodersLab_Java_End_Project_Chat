package pl.coderslab.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String email;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Room> rooms;
    @Column(columnDefinition = "COLUMN_TYPE default '0'")
    private long status;
    private String avatar = "https://www.hubitools.com/assets/36cb0984-e7f7-4b98-97e7-abbbd6fa3fc0/260x200/new.png";

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rooms=" + rooms +
                ", status=" + status +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
