package com.ikeda.notificationhex.adapters.configs;

import com.ikeda.notificationhex.MsNotificationHexApplication;
import com.ikeda.notificationhex.core.ports.NotificationPersistencePort;
import com.ikeda.notificationhex.core.services.NotificationServicePortImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = MsNotificationHexApplication.class)
public class BeanConfiguration {

    @Bean
    NotificationServicePortImpl notificationServicePortImpl(NotificationPersistencePort persistence){
        return new NotificationServicePortImpl(persistence);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
