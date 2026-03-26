-- Preload inbound calls for local testing (in-memory H2).
-- This lets you hit GET /calls without needing to send XML messages.

INSERT INTO inbound_call (call_id, caller_number, call_category, assigned_agent_id, received_at, status)
VALUES
    ('10001', '+13125550182', 'TECHNICAL', 1, TIMESTAMP '2026-03-17 09:42:00', 'ASSIGNED'),
    ('10002', '+13125550183', 'BILLING', 2, TIMESTAMP '2026-03-17 10:00:00', 'COMPLETED'),
    ('10003', '+13125550184', 'SALES', 7, TIMESTAMP '2026-03-17 10:15:00', 'ASSIGNED'),
    ('10004', '+13125550185', 'GENERAL', 5, TIMESTAMP '2026-03-17 11:00:00', 'ABANDONED');