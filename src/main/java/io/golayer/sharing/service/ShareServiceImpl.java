package io.golayer.sharing.service;

import com.google.common.collect.Lists;
import io.golayer.sharing.model.Share;
import io.golayer.sharing.repository.ShareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;

    @Override
    public Share save(Share share) {
        log.debug("Created new share {}", share.getSelection());
        return shareRepository.save(share);
    }

    @Override
    public List<Share> save(List<Share> shares) {
        log.info("New {} shares created", shares.size());
        return Lists.newArrayList(shareRepository.saveAll(shares));
    }

    @Override
    public List<Share> findByEmail(String email) {
        return null;
    }
}
