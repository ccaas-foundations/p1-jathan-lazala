package com.revature.routing.repository;

import com.revature.routing.entity.InboundCall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InboundCallRepository extends JpaRepository<InboundCall, Long> {
    List<InboundCall> findByAssignedAgentId(Long assignedAgentId);
}
