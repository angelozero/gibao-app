package com.angelozero.gibao.service;

import com.angelozero.gibao.dao.InfoPost;
import com.angelozero.gibao.repository.InfoPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoPostServiceImpl implements InfoPostService {

    @Autowired
    InfoPostRepository infoPostRepository;

    @Override
    public List<InfoPost> findaAll() {
        return infoPostRepository.findAll();
    }

    @Override
    public InfoPost findyById(Long id) {
        return Optional.of(infoPostRepository.findById(id)).map(Optional::get).orElse(null);
    }

    @Override
    public InfoPost save(InfoPost infoPost) {
        return infoPostRepository.save(infoPost);
    }
}
