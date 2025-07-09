package com.ikeda.notificationhex.core.services;

import com.ikeda.notificationhex.core.domain.NotificationDomain;
import com.ikeda.notificationhex.core.domain.PageInfo;
import com.ikeda.notificationhex.core.domain.enums.NotificationStatus;
import com.ikeda.notificationhex.core.ports.NotificationPersistencePort;
import com.ikeda.notificationhex.core.ports.NotificationServicePort;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NotificationServicePortImpl implements NotificationServicePort {

    final NotificationPersistencePort notificationPersistencePort;

    public NotificationServicePortImpl(NotificationPersistencePort notificationPersistencePort) {
        this.notificationPersistencePort = notificationPersistencePort;
    }

    @Override
    public NotificationDomain saveNotification(NotificationDomain notificationDomain) {
        notificationDomain.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        notificationDomain.setNotificationStatus(NotificationStatus.CREATED);

        return notificationPersistencePort.save(notificationDomain);
    }

    @Override
    public List<NotificationDomain> findAllNotificationsByUser(UUID userId, PageInfo pageInfo) {
        return notificationPersistencePort.findAllByUserNotificationStatus(userId, NotificationStatus.CREATED, pageInfo);
    }

    @Override
    public Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId) {
        return notificationPersistencePort.findByNotificationIdAndUserId(notificationId, userId);
    }

    @Override
    public NotificationDomain updateNotification(NotificationStatus notificationStatus, NotificationDomain notificationDomain) {
        return notificationPersistencePort.update(notificationStatus, notificationDomain);
    }
}
