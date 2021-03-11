package com.angelozero.gibao.app.usecase;

import com.angelozero.gibao.app.gateway.PostDataGateway;
import org.springframework.stereotype.Component;

@Component
public class SavePost {

    PostDataGateway postDataGateway;

    public void execute() {
        postDataGateway.save(null);
    }
}
