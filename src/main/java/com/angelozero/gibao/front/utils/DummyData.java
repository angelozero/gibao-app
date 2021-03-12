package com.angelozero.gibao.front.utils;

import com.angelozero.gibao.app.gateway.postgres.model.PostDataModel;
import com.angelozero.gibao.app.gateway.repository.PostDataRepository;
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
    PostDataRepository postDataRepository;

    //@PostConstruct
    public void saveInfoPosts() {

        List<PostDataModel> infoPostList = Arrays.asList(
                PostDataModel.builder().date(LocalDate.now()).author("Angelo").description(UUID.randomUUID().toString()).title("Title 1").build(),
                PostDataModel.builder().date(LocalDate.now()).author("Jake").description(UUID.randomUUID().toString()).title("Title 2").build(),
                PostDataModel.builder().date(LocalDate.now()).author("Flog").description(UUID.randomUUID().toString()).title("Title 3").build()
        );

        postDataRepository.saveAll(infoPostList);
    }
}
