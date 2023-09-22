package com.pragma.userservice.infraestructure.configuration;

import com.pragma.userservice.domain.api.IRoleServicePort;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.spi.*;
import com.pragma.userservice.domain.usecase.RoleUseCase;
import com.pragma.userservice.domain.usecase.UserUseCase;
import com.pragma.userservice.infraestructure.out.feignclient.IRestaurantFeignClient;
import com.pragma.userservice.infraestructure.out.feignclient.adapter.RestaurantFeignClientAdapter;
import com.pragma.userservice.infraestructure.out.jpa.adapter.RoleMysqlAdapter;
import com.pragma.userservice.infraestructure.out.jpa.adapter.UserMysqlAdapter;
import com.pragma.userservice.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.userservice.infraestructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.userservice.infraestructure.out.jpa.repository.IRoleRepository;
import com.pragma.userservice.infraestructure.out.jpa.repository.IUserRepository;
import com.pragma.userservice.infraestructure.out.passwordencryption.PasswordEncryptionAdapter;
import com.pragma.userservice.infraestructure.out.securitycontext.SecurityContextAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public ISecurityContextPort securityContextPort(){
        return new SecurityContextAdapter();
    }
    @Bean
    public IPasswordEncryptionPort passwordEncryptionPort(PasswordEncoder passwordEncoder){
        return new PasswordEncryptionAdapter(passwordEncoder);
    }
    @Bean
    public IRestaurantFeignClientPort restaurantEmployeeFeignClientPort(IRestaurantFeignClient restaurantFeignClient){
        return new RestaurantFeignClientAdapter(restaurantFeignClient);
    }



    @Bean
    public IRolePersistencePort rolePersistencePort(IRoleRepository roleRepository, IRoleEntityMapper roleEntityMapper){
        return new RoleMysqlAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserPersistencePort userPersistencePort(IUserRepository userRepository, IUserEntityMapper userEntityMapper){
        return new UserMysqlAdapter(userRepository, userEntityMapper);
    }



    @Bean
    public IRoleServicePort roleServicePort(IRolePersistencePort rolePersistencePort){
        return new RoleUseCase(rolePersistencePort);
    }

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IPasswordEncryptionPort passwordEncryptionPort, ISecurityContextPort securityContextPort, IRestaurantFeignClientPort restaurantFeignClientPort){
        return new UserUseCase(userPersistencePort, rolePersistencePort, passwordEncryptionPort, securityContextPort, restaurantFeignClientPort);
    }

}
