package com.upgrad.SweetHome.Dao;

import com.upgrad.SweetHome.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This layer will talk to the databases
 */
public interface BookingInfoDao extends JpaRepository<BookingInfoEntity,Integer> {

}
