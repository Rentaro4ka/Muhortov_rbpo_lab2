package com.example.lab2.repository;

import com.example.lab2.model.LossReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LossReportRepository {
    private final Map<Long, LossReport> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<LossReport> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<LossReport> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public LossReport save(LossReport report) {
        if (report.getId() == null) {
            report.setId(idGenerator.getAndIncrement());
        }
        storage.put(report.getId(), report);
        return report;
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }

    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}