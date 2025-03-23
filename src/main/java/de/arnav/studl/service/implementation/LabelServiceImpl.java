package de.arnav.studl.service.implementation;

import de.arnav.studl.adapter.label.LabelAdapter;
import de.arnav.studl.dto.label.LabelCreateDto;
import de.arnav.studl.dto.label.LabelResponseDto;
import de.arnav.studl.dto.label.LabelUpdateDto;
import de.arnav.studl.model.Label;
import de.arnav.studl.repository.LabelRepository;
import de.arnav.studl.service.template.LabelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;
    private final LabelAdapter labelAdapter;

    public LabelServiceImpl(LabelRepository labelRepository, LabelAdapter labelAdapter) {
        this.labelRepository = labelRepository;
        this.labelAdapter = labelAdapter;
    }

    @Override
    public LabelResponseDto createLabel(LabelCreateDto dto) {
        Label label = labelAdapter.fromCreateDto(dto);
        Label savedLabel = labelRepository.save(label);
        return labelAdapter.toResponseDto(savedLabel);
    }

    @Override
    public LabelResponseDto getLabelById(Long id) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Label not found"));
        return labelAdapter.toResponseDto(label);
    }

    @Override
    public LabelResponseDto updateLabel(Long id, LabelUpdateDto dto) {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Label not found"));
        label = labelAdapter.updateEntityFromUpdateDto(dto, label);
        Label updatedLabel = labelRepository.save(label);
        return labelAdapter.toResponseDto(updatedLabel);
    }

    @Override
    public void deleteLabel(Long id) {
        labelRepository.deleteById(id);
    }

    @Override
    public List<LabelResponseDto> getAllLabels() {
        return labelRepository.findAll().stream()
                .map(labelAdapter::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LabelResponseDto> searchLabelsByName(String keyword) {
        return labelRepository.findByNameContaining(keyword).stream()
                .map(labelAdapter::toResponseDto)
                .collect(Collectors.toList());
    }
}
