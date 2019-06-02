package io.golayer.sharing.service;

import io.golayer.sharing.dto.CreateSharingRequestDto;
import io.golayer.sharing.dto.ShareResponseDto;
import io.golayer.sharing.model.Share;
import io.golayer.sharing.model.Sheet;
import io.golayer.sharing.model.User;
import io.golayer.sharing.repository.ShareRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final UserService userService;

    @Override
    public List<ShareResponseDto> save(CreateSharingRequestDto request) {
        Iterable<Share> savedShares = shareRepository.saveAll(convert(request));
        //TODO here we should send an event to another micro service to generate a new document
        return convert(savedShares);
    }

    @Override
    public List<ShareResponseDto> getAll() {
        return convert(shareRepository.findAll());
    }

    private List<Share> convert(CreateSharingRequestDto request) {
        List<User> users = request.getEmails().stream()
                .map(email -> userService.findOrCreate(email.getEmail()))
                .collect(toList());
        List<Share> shares = request.getSelections().stream()
                .map(selection -> createShare(selection.getSelection(), users))
                .collect(toList());
        return shares;
    }

    private List<ShareResponseDto> convert(Iterable<Share> shares) {
        return StreamSupport.stream(shares.spliterator(), false)
                .map(this::createShareResponse)
                .collect(toList());
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

    private ShareResponseDto createShareResponse(Share share) {
        List<String> emails = share.getUsers().stream()
                .map(User::getEmail)
                .collect(toList());
        return ShareResponseDto.builder()
                .id(share.getId())
                .selection(share.getSelection())
                .sheet(share.getSheet().name())
                .emails(emails)
                .build();
    }
}
