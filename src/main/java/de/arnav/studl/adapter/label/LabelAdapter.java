package de.arnav.studl.adapter.label;

import de.arnav.studl.adapter.DtoAdapter;
import de.arnav.studl.dto.label.LabelCreateDto;
import de.arnav.studl.dto.label.LabelResponseDto;
import de.arnav.studl.dto.label.LabelUpdateDto;
import de.arnav.studl.model.Label;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class LabelAdapter implements DtoAdapter<Label, LabelResponseDto, LabelCreateDto, LabelUpdateDto> {

    @Override
    public LabelResponseDto toResponseDto(Label label) {
        LabelResponseDto dto = new LabelResponseDto();
        dto.setLabelId(label.getLabelId());
        dto.setName(label.getName());
        dto.setDescription(label.getDescription());
        dto.setCreatedAt(label.getCreatedAt());
        dto.setUpdatedAt(label.getUpdatedAt());
        return dto;
    }

    @Override
    public Label fromCreateDto(LabelCreateDto createDto) {
        Label label = new Label();
        label.setName(createDto.getName());
        label.setDescription(createDto.getDescription());
        label.setCreatedAt(LocalDateTime.now());
        label.setUpdatedAt(LocalDateTime.now());
        return label;
    }

    @Override
    public Label updateEntityFromUpdateDto(LabelUpdateDto updateDto, Label label) {
        if(updateDto.getName() != null) {
            label.setName(updateDto.getName());
        }
        if(updateDto.getDescription() != null) {
            label.setDescription(updateDto.getDescription());
        }
        label.setUpdatedAt(LocalDateTime.now());
        return label;
    }
}
