package com.nord.shorter.service;

import com.nord.shorter.model.Shorter;
import com.nord.shorter.repository.api.IShorterRepository;
import com.nord.shorter.service.api.IShortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShortService implements IShortService {

    private IShorterRepository repository;

    @Autowired
    public ShortService(IShorterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Shorter findByHash(String hash) {
        log.info("IN ShortServiceImpl findByHash {}", hash);
        return repository.findByHash(hash);
    }

    @Override
    public List<Shorter> findAll() {
        log.info("IN ShortServiceImpl findAll {}");
        return repository.findAll();
    }

    @Override
    public Shorter save(Shorter shorter) {
        log.info("IN ShortServiceImpl save {}", shorter);
        return repository.save(shorter);
    }

    @Override
    public boolean delete(Long userId) {
        if (repository.findById(userId).isPresent()) {
            repository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<Shorter> findByUserId(Long id) {
        return repository.findAllByUser(id);
    }
}
