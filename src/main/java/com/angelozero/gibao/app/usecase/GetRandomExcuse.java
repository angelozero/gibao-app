package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.domain.DataPost;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class GetRandomExcuse {

    private final FindDataPost findDataPost;

    public List<DataPost> execute() {

        List<DataPost> allPosts = findDataPost.execute();

        return Collections.singletonList(allPosts.get(new Random().nextInt(allPosts.size())));
    }
}
