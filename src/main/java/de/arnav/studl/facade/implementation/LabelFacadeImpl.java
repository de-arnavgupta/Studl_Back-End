package de.arnav.studl.facade.implementation;

import de.arnav.studl.dto.label.LabelCreateDto;
import de.arnav.studl.dto.label.LabelResponseDto;
import de.arnav.studl.dto.label.LabelUpdateDto;
import de.arnav.studl.facade.template.LabelFacade;
import de.arnav.studl.service.template.LabelService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class LabelFacadeImpl implements LabelFacade {

    private final LabelService labelService;

    public LabelFacadeImpl(LabelService labelService) {
        this.labelService = labelService;
    }

    @Override
    public LabelResponseDto createLabel(LabelCreateDto dto) {
        return labelService.createLabel(dto);
    }

    @Override
    public LabelResponseDto getLabelById(Long id) {
        return labelService.getLabelById(id);
    }

    @Override
    public LabelResponseDto updateLabel(Long id, LabelUpdateDto dto) {
        return labelService.updateLabel(id, dto);
    }

    @Override
    public void deleteLabel(Long id) {
        labelService.deleteLabel(id);
    }

    @Override
    public List<LabelResponseDto> getAllLabels() {
        return labelService.getAllLabels();
    }

    @Override
    public List<LabelResponseDto> searchLabelsByName(String keyword) {
        return labelService.searchLabelsByName(keyword);
    }
}
