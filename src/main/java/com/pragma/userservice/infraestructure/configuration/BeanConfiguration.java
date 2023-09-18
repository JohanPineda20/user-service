package com.pragma.userservice.infraestructure.configuration;

import com.pragma.userservice.domain.api.IRoleServicePort;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.spi.IPasswordEncryptionPort;
import com.pragma.userservice.domain.spi.IRolePersistencePort;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.domain.usecase.RoleUseCase;
import com.pragma.userservice.domain.usecase.UserUseCase;
import com.pragma.userservice.infraestructure.out.jpa.adapter.RoleMysqlAdapter;
import com.pragma.userservice.infraestructure.out.jpa.adapter.UserMysqlAdapter;
import com.pragma.userservice.infraestructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.userservice.infraestructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.userservice.infraestructure.out.jpa.repository.IRoleRepository;
import com.pragma.userservice.infraestructure.out.jpa.repository.IUserRepository;
import com.pragma.userservice.infraestructure.out.passwordencryption.PasswordEncryptionAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public IRolePersistencePort rolePersistencePort(IRoleRepository roleRepository, IRoleEntityMapper roleEntityMapper){
        return new RoleMysqlAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserPersistencePort userPersistencePort(IUserRepository userRepository, IUserEntityMapper userEntityMapper){
        return new UserMysqlAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IPasswordEncryptionPort passwordEncryptionPort(PasswordEncoder passwordEncoder){
        return new PasswordEncryptionAdapter(passwordEncoder);
    }
    @Bean
    public IRoleServicePort roleServicePort(IRolePersistencePort rolePersistencePort){
        return new RoleUseCase(rolePersistencePort);
    }

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IPasswordEncryptionPort passwordEncryptionPort){
        return new UserUseCase(userPersistencePort, rolePersistencePort, passwordEncryptionPort);
    }

}
