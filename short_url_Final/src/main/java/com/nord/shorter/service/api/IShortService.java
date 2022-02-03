package com.nord.shorter.service.api;

import com.nord.shorter.model.Shorter;

import java.util.List;

public interface IShortService {
    Shorter save(Shorter shorter);
    Shorter findByHash(String hash);
    List<Shorter> findAll();
    boolean delete(Long id);
    List<Shorter> findByUserId(Long id);

}
