package ua.com.alevel.mapper.finance;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.finance.AccountingOfPaymentProfileDto;
import ua.com.alevel.dto.table.finance.AccountingOfPaymentTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.user.TutorMapper;
import ua.com.alevel.persistence.entity.finance.AccountingOfPayment;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                TutorMapper.class
        }
)
public interface AccountingOfPaymentMapper extends CommonMapper<AccountingOfPayment, AccountingOfPaymentTableDto, AccountingOfPaymentProfileDto> {

    @Override
    AccountingOfPaymentTableDto toTableDto(AccountingOfPayment entity);

    @Override
    @Mapping(target = "tutor", qualifiedByName = "forAccountingOfPaymentProfileDto")
    AccountingOfPaymentProfileDto toProfileDto(AccountingOfPayment entity);

    @Override
    AccountingOfPayment toEntity(AccountingOfPaymentProfileDto profileDto);

    @Named("forTutorProfileDto")
    @Mapping(target = "tutor", ignore = true)
    AccountingOfPaymentProfileDto accountingOfPaymentToAccountingOfPaymentProfileDtoForTutorProfileDto(AccountingOfPayment entity);
}
