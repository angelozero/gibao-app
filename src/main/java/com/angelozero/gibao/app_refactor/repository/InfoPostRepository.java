package com.angelozero.gibao.app_refactor.repository;

import com.angelozero.gibao.app_refactor.dao.InfoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoPostRepository extends JpaRepository<InfoPost, Long> {
}
