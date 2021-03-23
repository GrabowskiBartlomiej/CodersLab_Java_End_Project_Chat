package pl.coderslab.entity;

import java.util.List;

public class UsersStatus {
    List<User> online;
    List<User> offline;

    public List<User> getOnline() {
        return online;
    }

    public void setOnline(List<User> online) {
        this.online = online;
    }

    public List<User> getOffline() {
        return offline;
    }

    public void setOffline(List<User> offline) {
        this.offline = offline;
    }
}
