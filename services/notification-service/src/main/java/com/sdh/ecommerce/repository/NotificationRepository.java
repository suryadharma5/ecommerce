package com.sdh.ecommerce.repository;

import com.sdh.ecommerce.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
