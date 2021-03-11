package com.angelozero.gibao.app_refactor.service;

import com.angelozero.gibao.app_refactor.dao.InfoPost;
import com.angelozero.gibao.app_refactor.repository.InfoPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InfoPostService {

    InfoPostRepository infoPostRepository;

    public List<InfoPost> findaAll() {
        return infoPostRepository.findAll();
    }

    public InfoPost findyById(Long id) {
        return Optional.of(infoPostRepository.findById(id)).map(Optional::get).orElse(null);
    }

    public InfoPost save(InfoPost infoPost) {
        infoPost.setData(LocalDate.now());
        return infoPostRepository.save(infoPost);
    }

    public void delete(long id) {
        infoPostRepository.deleteById(id);
    }
}
