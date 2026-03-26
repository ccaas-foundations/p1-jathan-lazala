package com.revature.agent.specification;

import com.revature.agent.entity.Agent;
import com.revature.agent.entity.AgentStatus;
import com.revature.agent.entity.CallCategory;
import org.springframework.data.jpa.domain.Specification;

public final class AgentSpecifications {

    private AgentSpecifications() {
    }

    public static Specification<Agent> availableForCategory(CallCategory category) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get("status"), AgentStatus.AVAILABLE),
                cb.isMember(category, root.get("categories"))
        );
    }
}
