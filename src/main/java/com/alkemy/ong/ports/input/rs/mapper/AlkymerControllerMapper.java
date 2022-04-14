package com.alkemy.ong.ports.input.rs.mapper;

import com.alkemy.ong.domain.model.Alkymer;
import com.alkemy.ong.ports.input.rs.request.CreateAlkymerRequest;
import com.alkemy.ong.ports.input.rs.request.UpdateAlkymerRequest;
import com.alkemy.ong.ports.input.rs.response.AlkymerResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface AlkymerControllerMapper extends CommonMapper {

    @IterableMapping(qualifiedByName = "alkymerToAlkymerResponse")
    List<AlkymerResponse> alkymerListToAlkymerResponseList(List<Alkymer> alkymers);

    @Named("alkymerToAlkymerResponse")
    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "localDateTimeToDate")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "localDateTimeToDate")
    AlkymerResponse alkymerToAlkymerResponse(Alkymer alkymer);

    Alkymer createAlkymerRequestToAlkymer(CreateAlkymerRequest create);

    Alkymer updateAlkymerRequestToAlkymer(UpdateAlkymerRequest create);
}
