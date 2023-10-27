package com.CMPE220.iot_health_monitoring.repository;

import com.CMPE220.iot_health_monitoring.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
}
