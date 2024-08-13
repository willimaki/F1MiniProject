package com.springtuto.Repositories;

import com.springtuto.POJO.Constructor;
import com.springtuto.POJO.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructorRepository extends JpaRepository<Constructor, Long> {
}

