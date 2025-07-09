package com.ikeda.notificationhex.core.ports;

import com.ikeda.notificationhex.core.domain.NotificationDomain;
import com.ikeda.notificationhex.core.domain.PageInfo;
import com.ikeda.notificationhex.core.domain.enums.NotificationStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationServicePort {

    NotificationDomain saveNotification(NotificationDomain notificationDomain);
    List<NotificationDomain> findAllNotificationsByUser(UUID userId, PageInfo pageInfo);
    Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId);
    NotificationDomain updateNotification(NotificationStatus notificationStatus, NotificationDomain notificationDomain);
}
