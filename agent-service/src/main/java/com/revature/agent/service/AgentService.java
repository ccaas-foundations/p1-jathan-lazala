package com.revature.agent.service;

import com.revature.agent.entity.Agent;
import com.revature.agent.entity.AgentStatus;
import com.revature.agent.entity.CallCategory;
import com.revature.agent.repository.AgentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent getAgentById(Long id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent not found"));
    }

    public List<Agent> getAvailableAgentsByCategory(CallCategory category) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Agent> cq = cb.createQuery(Agent.class);
        Root<Agent> root = cq.from(Agent.class);

        cq.select(root).where(
                cb.and(
                        cb.equal(root.get("status"), AgentStatus.AVAILABLE),
                        cb.isMember(category, root.get("categories"))
                )
        );

        return entityManager.createQuery(cq).getResultList();
    }

    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public Agent updateStatus(Long id, AgentStatus status) {
        Agent agent = getAgentById(id);
        agent.setStatus(status);
        return agentRepository.save(agent);
    }
}