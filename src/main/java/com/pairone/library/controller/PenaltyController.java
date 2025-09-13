package com.pairone.library.controller;

import com.pairone.library.dto.penalty.*;
import com.pairone.library.service.PenaltyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/penalty")
public class PenaltyController {
    private final PenaltyServiceImpl penaltyServiceImpl;

    public PenaltyController(PenaltyServiceImpl penaltyServiceImpl) {
        this.penaltyServiceImpl = penaltyServiceImpl;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PagePenaltyRes> getPenalties(@RequestParam int page,
                                             @RequestParam int size) {

        return penaltyServiceImpl.getPenalties(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PenaltyCreateRes createPenalty(@RequestBody PenaltyCreateReq penalty) {
        return penaltyServiceImpl.createPenalty(penalty);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeletePenaltyRes deletePenalty(@PathVariable int id) {
        return penaltyServiceImpl.deleteId(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PenaltyUpdateRes updatePenalty(@RequestBody PenaltyCreateReq penalty) {
        return penaltyServiceImpl.updatePenalty(penalty);
    }

}
