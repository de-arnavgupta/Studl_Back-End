package de.arnav.studl.controller;

import de.arnav.studl.dto.label.LabelCreateDto;
import de.arnav.studl.dto.label.LabelResponseDto;
import de.arnav.studl.dto.label.LabelUpdateDto;
import de.arnav.studl.facade.template.LabelFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
public class LabelController {

    private final LabelFacade labelFacade;

    public LabelController(LabelFacade labelFacade) {
        this.labelFacade = labelFacade;
    }

    @PostMapping
    public ResponseEntity<LabelResponseDto> createLabel(@RequestBody LabelCreateDto dto) {
        LabelResponseDto response = labelFacade.createLabel(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabelResponseDto> getLabelById(@PathVariable Long id) {
        LabelResponseDto response = labelFacade.getLabelById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabelResponseDto> updateLabel(@PathVariable Long id, @RequestBody LabelUpdateDto dto) {
        LabelResponseDto response = labelFacade.updateLabel(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long id) {
        labelFacade.deleteLabel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<LabelResponseDto>> getAllLabels() {
        List<LabelResponseDto> responses = labelFacade.getAllLabels();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<LabelResponseDto>> searchLabelsByName(@RequestParam("keyword") String keyword) {
        List<LabelResponseDto> responses = labelFacade.searchLabelsByName(keyword);
        return ResponseEntity.ok(responses);
    }
}
