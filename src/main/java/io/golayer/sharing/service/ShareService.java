package io.golayer.sharing.service;

import io.golayer.sharing.model.Share;

import java.util.List;

public interface ShareService {
    Share save(Share share);

    List<Share> save(List<Share> shares);

    List<Share> findByEmail(String email);
}
