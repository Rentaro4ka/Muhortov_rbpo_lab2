package com.example.lab2.repository;

import com.example.lab2.model.HandoverRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HandoverRecordRepository {
    private final Map<Long, HandoverRecord> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<HandoverRecord> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<HandoverRecord> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public HandoverRecord save(HandoverRecord record) {
        if (record.getId() == null) {
            record.setId(idGenerator.getAndIncrement());
        }
        storage.put(record.getId(), record);
        return record;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }

    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}