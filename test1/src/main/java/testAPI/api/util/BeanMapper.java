package testAPI.api.util;

import org.modelmapper.ModelMapper;

public class BeanMapper {
    static ModelMapper mapper = new ModelMapper();//use spring autowiring if use spring

    public static <T, R> R map(T entity, Class<R> targetEntity) {
        return mapper.map(entity, targetEntity);
    }
}
