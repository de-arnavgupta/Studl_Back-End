package de.arnav.studl.adapter;

public interface DtoAdapter<Entity, ResponseDTO, CreateDTO, UpdateDTO> {

    ResponseDTO toResponseDto(Entity entity);

    Entity fromCreateDto(CreateDTO createDto);

    Entity updateEntityFromUpdateDto(UpdateDTO updateDto, Entity entity);
}
