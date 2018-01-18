package org.ccts.balancingact.model;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class ModelMapperUtils {
    public static final ModelMapper getInstance() {
        return new ModelMapper();
    }

    public static final <T> List<T> mapList(final List<?> source, Class<T> destClass) {
        final ModelMapper mapper = getInstance();

        return source.stream().map(item -> mapper.map(item, destClass)).collect(Collectors.toList());
    }
}
