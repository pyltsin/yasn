package com.getjavajob.project.pyltsin.project.dao.datajpa;

import com.getjavajob.project.pyltsin.project.common.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface CrudAccountRepository extends JpaRepository<Account, Integer> {
    Account getAccountByLogin(String login);

    List<Account> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String firstNamePart,
                                                                         String lastNamePart,
                                                                         Pageable pageRequest);

    List<Account> findByFirstNameContainsOrLastNameContainsOrLoginContainsAllIgnoreCase(String firstNamePart,
                                                                                        String secondNamePart,
                                                                                        String lastNamePart,
                                                                                        Pageable pageRequest);
}

