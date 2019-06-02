package io.golayer.sharing.service;

import io.golayer.sharing.dto.CreateSharingRequestDto;
import io.golayer.sharing.dto.ShareResponseDto;

import java.util.List;

public interface ShareService {
    List<ShareResponseDto> save(CreateSharingRequestDto request);

    List<ShareResponseDto> getAll();
}
