package com.angelozero.gibao.app.gateway.repository;

import com.angelozero.gibao.app.gateway.postgres.model.PostDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDataRepository extends JpaRepository<PostDataModel, Long> {


}
