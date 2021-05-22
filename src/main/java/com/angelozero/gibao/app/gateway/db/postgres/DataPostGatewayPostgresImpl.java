package com.angelozero.gibao.app.gateway.db.postgres;

import com.angelozero.gibao.app.domain.DataPost;
import com.angelozero.gibao.app.gateway.db.DataPostGateway;
import com.angelozero.gibao.app.gateway.db.postgres.mapper.DataPostModelMapper;
import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import com.angelozero.gibao.app.gateway.db.repository.DataPostJPARepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class DataPostGatewayPostgresImpl implements DataPostGateway {

    private final DataPostJPARepository dataPostJPARepository;

    @Override
    public DataPost save(DataPost dataPost) {
        return DataPostModelMapper.toPostData(
                dataPostJPARepository.save(DataPostModelMapper.toPostDataModel(dataPost))
        );
    }

    @Override
    public void deleteById(Long id) {
        dataPostJPARepository.deleteById(id);
    }

    @Override
    public DataPost findById(Long id) {
        return DataPostModelMapper.toPostData(
                Optional.of(dataPostJPARepository.findById(id))
                        .map(Optional::get)
                        .orElse(DataPostModel.builder().build()));
    }

    @Override
    public List<DataPost> findAll() {
        return DataPostModelMapper.toPostDataList(dataPostJPARepository.findAll());
    }
}
