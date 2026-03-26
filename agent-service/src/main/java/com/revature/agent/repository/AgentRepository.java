package com.revature.agent.repository;

import com.revature.agent.entity.Agent;
import com.revature.agent.entity.AgentStatus;
import com.revature.agent.entity.CallCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long>, JpaSpecificationExecutor<Agent> {
    Optional<Agent> findByEmail(String email);

    List<Agent> findByStatus(AgentStatus status);

    List<Agent> findByStatusAndCategoriesContaining(AgentStatus status, CallCategory category);
}
