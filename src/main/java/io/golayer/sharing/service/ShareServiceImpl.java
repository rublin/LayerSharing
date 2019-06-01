package io.golayer.sharing.service;

import com.google.common.collect.Lists;
import io.golayer.sharing.dto.CreateSharingRequestDto;
import io.golayer.sharing.model.Share;
import io.golayer.sharing.model.Sheet;
import io.golayer.sharing.model.User;
import io.golayer.sharing.repository.ShareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final UserService userService;

    @Override
    public List<Share> save(CreateSharingRequestDto request) {
        return Lists.newArrayList(shareRepository.saveAll(convert(request)));
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

    public List<Share> convert(CreateSharingRequestDto request) {
        List<User> users = request.getEmails().stream()
                .map(email -> userService.findOrCreate(email.getEmail()))
                .collect(toList());
        List<Share> shares = request.getSelections().stream()
                .map(selection -> createShare(selection.getSelection(), users))
                .collect(toList());
        return shares;
    }

    private Share createShare(String selection, List<User> users) {
        selection = selection.replaceAll("'", "");
        String[] split = selection.split("!");
        Sheet sheet = Sheet.fromRequest(split[0]);
        String shareSelection = null;
        if (split.length == 2) {
            shareSelection = split[1];
        }
        Share share = new Share();
        share.setSheet(sheet);
        share.setUsers(users);
        share.setSelection(shareSelection);

        return share;
    }
}
