package com.angelozero.gibao.app.gateway.mongoDB;

import com.angelozero.gibao.app.domain.PostData;
import com.angelozero.gibao.app.gateway.PostDataGateway;
import com.angelozero.gibao.app.gateway.repository.PostDataRepository;

import java.util.List;

public class PostDataGatewayMongoDBImpl implements PostDataGateway {

    private PostDataRepository postDataRepository;

    @Override
    public void save(PostData postData) {
        //TODO mapper PostData <> PostDataModel
        postDataRepository.save(null);
    }

    @Override
    public void delete(PostData postData) {

    }

    @Override
    public PostData findById(Long id) {
        return null;
    }

    @Override
    public List<PostData> findAllPostsData() {
        return null;
    }
}
