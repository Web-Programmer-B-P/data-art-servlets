package model;

import java.util.Objects;

public class Ticket {
    private int id;
    private String name;
    private String description;
    private boolean status;
    private int userId;

    public Ticket() {
    }

    public Ticket(int id, String name, String description, boolean status, int userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    public Ticket(String name, String description, boolean status, int userId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    public Ticket(String name, String description, int userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return id == ticket.id &&
                status == ticket.status &&
                userId == ticket.userId &&
                Objects.equals(name, ticket.name) &&
                Objects.equals(description, ticket.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, userId);
    }

    @Override
    public String toString() {
        return "Ticket{id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", status=" + status
                + ", userId=" + userId
                + '}';
    }
}
