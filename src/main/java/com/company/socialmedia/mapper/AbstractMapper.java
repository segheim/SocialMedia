package com.company.socialmedia.mapper;

import java.util.List;
public interface AbstractMapper<Class, ClassDto> {

    ClassDto toDto(Class object);

    Class fromDto(ClassDto dto);

    List<ClassDto> toDtoList(List<Class> objects);

    List<Class> fromDtoList(List<ClassDto> dtos);
}
