package com.example.lab2.service;

import com.example.lab2.model.HandoverRecord;
import com.example.lab2.repository.HandoverRecordRepository;
import com.example.lab2.repository.ItemClaimRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HandoverRecordService {
    private final HandoverRecordRepository repository;
    private final ItemClaimRepository itemClaimRepository;

    public HandoverRecordService(HandoverRecordRepository repository,
                                 ItemClaimRepository itemClaimRepository) {
        this.repository = repository;
        this.itemClaimRepository = itemClaimRepository;
    }

    public List<HandoverRecord> findAll() { return repository.findAll(); }
    public Optional<HandoverRecord> findById(Long id) { return repository.findById(id); }

    public Optional<HandoverRecord> create(HandoverRecord record) {
        if (record.getClaimId() == null || !itemClaimRepository.existsById(record.getClaimId())) {
            return Optional.empty();
        }
        record.setId(null);
        record.setHandoverDate(LocalDateTime.now());
        if (record.getConfirmed() == null) record.setConfirmed(false);
        return Optional.of(repository.save(record));
    }

    public Optional<HandoverRecord> update(Long id, HandoverRecord patch) {
        return repository.findById(id).map(existing -> {
            if (patch.getConfirmed() != null) existing.setConfirmed(patch.getConfirmed());
            return repository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}