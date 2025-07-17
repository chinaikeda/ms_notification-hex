package com.ikeda.notificationhex.adapters.outbounds.persistences;

import com.ikeda.notificationhex.adapters.exceptions.NotFoundException;
import com.ikeda.notificationhex.adapters.outbounds.entities.NotificationEntity;
import com.ikeda.notificationhex.core.domain.NotificationDomain;
import com.ikeda.notificationhex.core.domain.PageInfo;
import com.ikeda.notificationhex.core.domain.enums.NotificationStatus;
import com.ikeda.notificationhex.core.ports.NotificationPersistencePort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class NotificationPersistencePortImpl implements NotificationPersistencePort {

    private final  NotificationJpaRepository notificationJpaRepository;

    private final ModelMapper modelMapper;

    public NotificationPersistencePortImpl(NotificationJpaRepository notificationJpaRepository, ModelMapper modelMapper) {
        this.notificationJpaRepository = notificationJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public NotificationDomain save(NotificationDomain notificationDomain) {
        var notificationEntity = notificationJpaRepository.save(modelMapper.map(notificationDomain, NotificationEntity.class));
        return modelMapper.map(notificationEntity, NotificationDomain.class);
    }

    @Override
    public List<NotificationDomain> findAllByUserNotificationStatus(UUID userId, NotificationStatus notificationStatus, PageInfo pageInfo) {
        var pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        return notificationJpaRepository.findAllByUserIdAndNotificationStatus(userId, notificationStatus, pageable)
                .stream()
                .map(entity -> modelMapper.map(entity, NotificationDomain.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotificationDomain> findByNotificationIdAndUserId(UUID notificationId, UUID userId) {
        Optional<NotificationEntity> notificationDomainOptional = notificationJpaRepository.findByNotificationIdAndUserId(notificationId, userId);
        if (notificationDomainOptional.isEmpty()){
            throw new NotFoundException("Error: Notification for this user not found.");
        }
        return Optional.of(modelMapper.map(notificationDomainOptional.get(), NotificationDomain.class));
    }

    @Override
    public NotificationDomain update(NotificationStatus notificationStatus, NotificationDomain notificationDomain) {
        notificationDomain.setNotificationStatus(notificationStatus);
        var notificationEntity = notificationJpaRepository.save(modelMapper.map(notificationDomain, NotificationEntity.class));

        return modelMapper.map(notificationEntity, NotificationDomain.class);
    }
}