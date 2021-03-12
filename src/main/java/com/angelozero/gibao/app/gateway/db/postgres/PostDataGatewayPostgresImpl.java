package com.angelozero.gibao.app.gateway.db.postgres;

import com.angelozero.gibao.app.domain.PostData;
import com.angelozero.gibao.app.gateway.PostDataGateway;
import com.angelozero.gibao.app.gateway.db.postgres.mapper.PostDataModelMapper;
import com.angelozero.gibao.app.gateway.db.postgres.model.PostDataModel;
import com.angelozero.gibao.app.gateway.repository.PostDataJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PostDataGatewayPostgresImpl implements PostDataGateway {

    private final PostDataJPARepository postDataJPARepository;

    @Override
    public void save(PostData postData) {

        postDataJPARepository.save(PostDataModelMapper.toPostDataModel(postData));
    }

    @Override
    public void delete(Long id) {
        postDataJPARepository.deleteById(id);
    }

    @Override
    public PostData findById(Long id) {
        return PostDataModelMapper.toPostData(
                Optional.of(postDataJPARepository.findById(id))
                        .map(Optional::get)
                        .orElse(PostDataModel.builder().build()));
    }

    @Override
    public List<PostData> findAll() {
        return PostDataModelMapper.toPostDataList(postDataJPARepository.findAll());
    }
}
