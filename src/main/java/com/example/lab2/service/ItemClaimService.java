package com.example.lab2.service;

import com.example.lab2.model.ItemClaim;
import com.example.lab2.repository.FoundItemRepository;
import com.example.lab2.repository.ItemClaimRepository;
import com.example.lab2.repository.LossReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemClaimService {
    private final ItemClaimRepository repository;
    private final LossReportRepository lossReportRepository;
    private final FoundItemRepository foundItemRepository;

    public ItemClaimService(ItemClaimRepository repository,
                            LossReportRepository lossReportRepository,
                            FoundItemRepository foundItemRepository) {
        this.repository = repository;
        this.lossReportRepository = lossReportRepository;
        this.foundItemRepository = foundItemRepository;
    }

    public List<ItemClaim> findAll() { return repository.findAll(); }
    public Optional<ItemClaim> findById(Long id) { return repository.findById(id); }

    public Optional<ItemClaim> create(ItemClaim claim) {
        // проверяем ссылки
        if (claim.getLossReportId() == null || !lossReportRepository.existsById(claim.getLossReportId())) {
            return Optional.empty();
        }
        if (claim.getFoundItemId() == null || !foundItemRepository.existsById(claim.getFoundItemId())) {
            return Optional.empty();
        }
        claim.setId(null);
        claim.setCreatedAt(LocalDateTime.now());
        return Optional.of(repository.save(claim));
    }

    public Optional<ItemClaim> update(Long id, ItemClaim patch) {
        return repository.findById(id).map(existing -> {
            if (patch.getEvidence() != null) existing.setEvidence(patch.getEvidence());
            if (patch.getStatus() != null) existing.setStatus(patch.getStatus());
            return repository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}