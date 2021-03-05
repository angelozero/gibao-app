package com.angelozero.gibao.repository;

import com.angelozero.gibao.dao.InfoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoPostRepository extends JpaRepository<InfoPost, Long> {
}
