package com.angelozero.gibao.app.gateway.postgres;

import com.angelozero.gibao.app.domain.PostData;
import com.angelozero.gibao.app.gateway.PostDataGateway;
import com.angelozero.gibao.app.gateway.postgres.mapper.PostDataModelMapper;
import com.angelozero.gibao.app.gateway.postgres.model.PostDataModel;
import com.angelozero.gibao.app.gateway.repository.PostDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PostDataGatewayPostgresImpl implements PostDataGateway {

    private PostDataRepository postDataRepository;

    @Override
    public void save(PostData postData) {
        postDataRepository.save(PostDataModelMapper.toPostDataModel(postData));
    }

    @Override
    public void delete(Long id) {
        postDataRepository.deleteById(id);
    }

    @Override
    public PostData findById(Long id) {
        return PostDataModelMapper.toPostData(
                Optional.of(postDataRepository.findById(id))
                        .map(Optional::get)
                        .orElse(PostDataModel.builder().build()));
    }

    @Override
    public List<PostData> findAll() {
        return PostDataModelMapper.toPostDataList(postDataRepository.findAll());
    }
}
