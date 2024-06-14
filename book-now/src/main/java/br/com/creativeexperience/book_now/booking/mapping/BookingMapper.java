package br.com.creativeexperience.book_now.booking.mapping;

import br.com.creativeexperience.book_now.booking.dto.BookingRequest;
import br.com.creativeexperience.book_now.booking.dto.BookingResponse;
import br.com.creativeexperience.book_now.booking.entities.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Booking toBooking(BookingRequest request);

    BookingResponse toBookingResponse(Booking booking);

}
