package ua.com.alevel.util;

import org.apache.commons.lang3.ObjectUtils;
import ua.com.alevel.dto.AbstractFilterDto;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.Objects;

public class FilterDtoUtil {

    private FilterDtoUtil() {
    }

    public static <FILTER_DTO extends AbstractFilterDto> boolean isExistFields(FILTER_DTO dto) {
        if (dto == null) {
            return false;
        }
        try {
            return Arrays.stream(Introspector.getBeanInfo(dto.getClass(), Object.class)
                            .getPropertyDescriptors())
                    .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                    .anyMatch(pd -> {
                        try {
                            Object value = pd.getReadMethod().invoke(dto);
                            if (ObjectUtils.isNotEmpty(value)) {
                                return true;
                            }
                        } catch (Exception e) {
                            return false;
                        }
                        return false;
                    });
        } catch (IntrospectionException e) {
            return false;
        }
    }
}
