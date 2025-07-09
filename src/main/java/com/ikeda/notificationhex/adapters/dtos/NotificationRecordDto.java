package com.ikeda.notificationhex.adapters.dtos;

import com.ikeda.notificationhex.core.domain.enums.NotificationStatus;
import jakarta.validation.constraints.NotNull;

public record NotificationRecordDto (@NotNull NotificationStatus notificationStatus) {
}
