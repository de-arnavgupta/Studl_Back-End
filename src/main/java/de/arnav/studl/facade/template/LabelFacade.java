package de.arnav.studl.facade.template;

import de.arnav.studl.dto.label.LabelCreateDto;
import de.arnav.studl.dto.label.LabelResponseDto;
import de.arnav.studl.dto.label.LabelUpdateDto;
import java.util.List;

public interface LabelFacade {
    LabelResponseDto createLabel(LabelCreateDto dto);
    LabelResponseDto getLabelById(Long id);
    LabelResponseDto updateLabel(Long id, LabelUpdateDto dto);
    void deleteLabel(Long id);
    List<LabelResponseDto> getAllLabels();
    List<LabelResponseDto> searchLabelsByName(String keyword);
}
