package com.angelozero.gibao.integration.config;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

public class NoKeepAliveTransformer extends ResponseDefinitionTransformer {
    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource fileSource, Parameters parameters) {
        return ResponseDefinitionBuilder.like(responseDefinition).withHeader("Connection", new String[]{"close"}).build();
    }

    @Override
    public String getName() {
        return "keep-alive-disabler";
    }
}
