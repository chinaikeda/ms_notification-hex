package com.ikeda.notificationhex.core.ports;

import com.ikeda.notificationhex.core.domain.NotificationDomain;
import com.ikeda.notificationhex.core.domain.PageInfo;
import com.ikeda.notificationhex.core.domain.enums.NotificationStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationPersistencePort {

    NotificationDomain save(NotificationDomain notificationDomain);
    List<NotificationDomain> findAllByUserNotificationStatus(UUID userId, NotificationStatus notificationStatus, PageInfo pageInfo);
    Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId);

    NotificationDomain update(NotificationStatus notificationStatus, NotificationDomain notificationDomain);
}
