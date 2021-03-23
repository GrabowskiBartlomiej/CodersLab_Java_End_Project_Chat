package pl.coderslab.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String logo = "https://logopond.com/logos/b1de93f14263ad77eb5cc53a40ff8d9b.png";

    @OneToMany(fetch = FetchType.EAGER)
    private List<Channel> channels;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }



    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", channels=" + channels +
                '}';
    }
}
