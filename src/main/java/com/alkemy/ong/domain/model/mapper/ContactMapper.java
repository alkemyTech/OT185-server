package com.alkemy.ong.domain.model.mapper;

import com.alkemy.ong.domain.model.entity.ContactEntity;
import com.alkemy.ong.domain.model.request.ContactRequestDTO;
import com.alkemy.ong.domain.model.response.ContactResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

            ContactEntity toEntity(ContactRequestDTO contactRequestDto);

            ContactResponseDTO toDto(ContactEntity contactEntity);
}
