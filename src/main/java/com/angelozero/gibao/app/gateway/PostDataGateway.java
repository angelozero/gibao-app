package com.angelozero.gibao.app.gateway;

import com.angelozero.gibao.app.domain.PostData;

import java.util.List;

public interface PostDataGateway {

    void save(PostData postData);

    void delete(Long id);

    PostData findById(Long id);

    List<PostData> findAll();
}
