package com.example.laba4.repo;

import com.example.laba4.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account,Integer> {
}
