package de.arnav.studl.adapter;

import de.arnav.studl.model.Organization;

public interface DtoAdapter <Entity, ResponseDto, CreateDto, UpdateDto, SummaryDto> {

    Entity fromCreateDto(CreateDto createDto);
    Entity fromUpdateDto(UpdateDto updateDto, Entity entity);
    ResponseDto toResponseDto(Entity entity);
    SummaryDto toSummaryDto(Entity entity);
}
