package ua.com.alevel.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UuidValidator implements ConstraintValidator<ValidUuid, Object> {

    @Override
    public boolean isValid(Object maybeUuid, ConstraintValidatorContext context) {
        try {
            UUID.fromString(String.valueOf(maybeUuid));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
