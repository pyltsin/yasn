package com.getjavajob.project.pyltsin.project.dao.datajpa;

import com.getjavajob.project.pyltsin.project.common.Send;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudSendRepository extends JpaRepository<Send, Integer> {
}
