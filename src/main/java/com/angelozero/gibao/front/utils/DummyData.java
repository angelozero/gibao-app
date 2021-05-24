package com.angelozero.gibao.front.utils;

import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import com.angelozero.gibao.app.gateway.db.repository.DataPostJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class DummyData {

    @Autowired
    DataPostJPARepository dataPostJPARepository;

//    @PostConstruct
    public void saveInfoPosts() {

        List<DataPostModel> infoPostList = Arrays.asList(
                DataPostModel.builder().date(LocalDate.now()).author("Angelo").description(UUID.randomUUID().toString()).title("Title 1").build(),
                DataPostModel.builder().date(LocalDate.now()).author("Bats").description(UUID.randomUUID().toString()).title("Title 2").build(),
                DataPostModel.builder().date(LocalDate.now()).author("Flog").description(UUID.randomUUID().toString()).title("Title 3").build()
        );

        dataPostJPARepository.saveAll(infoPostList);
    }
}
