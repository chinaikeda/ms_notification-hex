package com.ikeda.notificationhex.adapters.dtos;

import java.util.UUID;

public record NotificationRecordComandDto (String title,
                                           String message,
                                           UUID userId) {
}
