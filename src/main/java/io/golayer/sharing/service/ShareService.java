package io.golayer.sharing.service;

import io.golayer.sharing.dto.CreateSharingRequestDto;
import io.golayer.sharing.model.Share;

import java.util.List;

public interface ShareService {
    List<Share> save(CreateSharingRequestDto request);

    List<Share> save(List<Share> shares);

    List<Share> findByEmail(String email);
}
