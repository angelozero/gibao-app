package com.angelozero.gibao.app.gateway.db.repository;

import com.angelozero.gibao.app.gateway.db.postgres.model.DataPostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPostJPARepository extends JpaRepository<DataPostModel, Long> {


}
