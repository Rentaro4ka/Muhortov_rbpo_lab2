package com.example.lab2.repository;

import com.example.lab2.model.ItemClaim;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemClaimRepository {
    private final Map<Long, ItemClaim> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<ItemClaim> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<ItemClaim> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public ItemClaim save(ItemClaim claim) {
        if (claim.getId() == null) {
            claim.setId(idGenerator.getAndIncrement());
        }
        storage.put(claim.getId(), claim);
        return claim;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }

    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}