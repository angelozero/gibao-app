package com.angelozero.gibao.service;

import com.angelozero.gibao.dao.InfoPost;

import java.util.List;

public interface InfoPostService {

    List<InfoPost> findaAll();

    InfoPost findyById(Long id);

    InfoPost save(InfoPost infoPost);
}
