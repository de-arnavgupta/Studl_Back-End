package de.arnav.studl.adapter;

public interface DtoAdapter <Entity, ResponseDto, CreateDto, UpdateDto> {

    ResponseDto toResponseDto(Entity entity);
    Entity fromCreateDto(CreateDto createDto);

}
