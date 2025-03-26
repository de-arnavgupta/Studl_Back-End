package de.arnav.studl.adapter;

public interface DtoAdapter <Entity, ResponseDto, CreateDto, SummaryDto> {

    ResponseDto toResponseDto(Entity entity);
    SummaryDto toSummaryDto(Entity entity);
    Entity fromCreateDto(CreateDto createDto);

}
