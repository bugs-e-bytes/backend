package br.com.creativeexperience.book_now.accommodation.mapping;

import br.com.creativeexperience.book_now.accommodation.dto.AccommodationRequest;
import br.com.creativeexperience.book_now.accommodation.dto.AccommodationResponse;
import br.com.creativeexperience.book_now.accommodation.entities.Accommodation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccommodationMapper {

    AccommodationMapper INSTANCE = Mappers.getMapper(AccommodationMapper.class);

    @Mapping(target = "id", ignore = true)
    Accommodation toAccommodation(AccommodationRequest accommodationRequest);

    AccommodationResponse toAccommodationResponse(Accommodation accommodation);

    List<AccommodationResponse> toAccommodationResponseList(List<Accommodation> accommodationList);

}
