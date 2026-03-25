package com.revature.agent.entity;


import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private AgentStatus status;

    @ElementCollection(targetClass = CallCategory.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "agent_categories", joinColumns = @JoinColumn(name = "agent_id"))
    @Column(name = "category")
    private Set<CallCategory> categories;

    public Agent() {

    }

    public Agent(String name, String email){
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AgentStatus getStatus() {
        return status;
    }

    public void setStatus(AgentStatus status) {
        this.status = status;
    }

    public Set<CallCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<CallCategory> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Objects.equals(id, agent.id) && Objects.equals(name, agent.name) && Objects.equals(email, agent.email) && status == agent.status && Objects.equals(categories, agent.categories);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", categories=" + categories +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, status, categories);
    }
}
