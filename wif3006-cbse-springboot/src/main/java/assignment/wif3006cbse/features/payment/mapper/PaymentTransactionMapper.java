package assignment.wif3006cbse.features.payment.mapper;

import assignment.wif3006cbse.config.MapStructConfig;
import assignment.wif3006cbse.features.payment.domain.entity.PaymentTransaction;
import assignment.wif3006cbse.features.payment.dto.CreatePaymentTransactionModel;
import assignment.wif3006cbse.features.payment.dto.PaymentTransactionModel;
import assignment.wif3006cbse.features.payment.dto.UpdatePaymentTransactionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface PaymentTransactionMapper {

    PaymentTransactionModel toModel(PaymentTransaction paymentTransaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    PaymentTransaction toEntity(CreatePaymentTransactionModel createPaymentTransactionModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    void updateEntityFromUpdateModel(@MappingTarget PaymentTransaction paymentTransaction,
            UpdatePaymentTransactionModel updatePaymentTransactionModel);
}
