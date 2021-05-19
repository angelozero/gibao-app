package com.angelozero.gibao.app.gateway.db;

import com.angelozero.gibao.app.domain.DataPost;

import java.util.List;

public interface DataPostGateway {

    DataPost save(DataPost dataPost);

    void deleteById(Long id);

    DataPost findById(Long id);

    List<DataPost> findAll();
}
