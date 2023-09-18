package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.request.UserRequest;
import com.pragma.userservice.domain.model.UserModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-18T15:58:29-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class IUserDtoMapperImpl implements IUserDtoMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_0650712384 = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );

    @Override
    public UserModel mapToUserModel(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        if ( userRequest.getBirthdate() != null ) {
            userModel.setBirthdate( LocalDate.parse( userRequest.getBirthdate(), dateTimeFormatter_dd_MM_yyyy_0650712384 ) );
        }
        userModel.setName( userRequest.getName() );
        userModel.setLastname( userRequest.getLastname() );
        userModel.setDocumentNumber( userRequest.getDocumentNumber() );
        userModel.setCellphone( userRequest.getCellphone() );
        userModel.setEmail( userRequest.getEmail() );
        userModel.setPassword( userRequest.getPassword() );

        return userModel;
    }
}