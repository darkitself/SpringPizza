package com.example.springpizza.domain.audit;

import com.example.springpizza.adapter.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateAudit(String eventType, @Nullable Long userId, AuditableResult result) {
        auditRepository.save(new AuditEntity(eventType, userId, result, null));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateAudit(String eventType, @Nullable Long userId, AuditableResult result, String message) {
        auditRepository.save(new AuditEntity(eventType, userId, result, message));
    }
}
