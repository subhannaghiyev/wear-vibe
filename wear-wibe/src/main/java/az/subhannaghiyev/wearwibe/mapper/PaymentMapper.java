package az.subhannaghiyev.wearwibe.mapper;

import az.subhannaghiyev.wearwibe.dto.PaymentResponseDto;
import az.subhannaghiyev.wearwibe.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class, OrderMapper.class})
public interface PaymentMapper {

    PaymentResponseDto toDto(Payment payment);
    Payment toEntity(PaymentResponseDto paymentDto);

}
