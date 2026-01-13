package assignment.wif3006cbse.features.payment.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.payment.domain.entity.PaymentMethod;
import assignment.wif3006cbse.features.payment.dto.CreatePaymentMethodModel;
import assignment.wif3006cbse.features.payment.dto.PaymentMethodModel;
import assignment.wif3006cbse.features.payment.dto.UpdatePaymentMethodModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface PaymentMethodMapper {

    PaymentMethodModel toModel(PaymentMethod paymentMethod);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    PaymentMethod toEntity(CreatePaymentMethodModel createPaymentMethodModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget PaymentMethod paymentMethod,
            UpdatePaymentMethodModel updatePaymentMethodModel);
}
