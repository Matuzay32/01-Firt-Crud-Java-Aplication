package com.api.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.crud.models.UserModel;

/**
 * IUserRepository
 */
@Repository()
public interface IUserRepository extends JpaRepository<UserModel, Long> {

}
