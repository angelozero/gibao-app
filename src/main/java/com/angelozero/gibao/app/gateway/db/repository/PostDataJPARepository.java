package com.angelozero.gibao.app.gateway.db.repository;

import com.angelozero.gibao.app.gateway.db.postgres.model.PostDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDataJPARepository extends JpaRepository<PostDataModel, Long> {


}
