package io.golayer.sharing.controller;

import io.golayer.sharing.dto.CreateSharingRequestDto;
import io.golayer.sharing.dto.ShareResponseDto;
import io.golayer.sharing.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SharingRestController {

    private final ShareService shareService;

    @PostMapping("/shares")
    public ResponseEntity createSharing(@RequestBody @Valid CreateSharingRequestDto request) {
        log.debug("Received createSharing request {}", request);
        List<ShareResponseDto> save = shareService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping("/shares")
    public ResponseEntity getAllUnprocessedSharing() {
        return ResponseEntity.ok(shareService.getAll());
    }
}
