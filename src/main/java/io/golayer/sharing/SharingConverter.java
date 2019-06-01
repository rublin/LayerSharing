package io.golayer.sharing;

import io.golayer.sharing.dto.CreateSharingRequestDto;
import io.golayer.sharing.model.Share;
import io.golayer.sharing.model.Sheet;
import io.golayer.sharing.model.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SharingConverter {
    public static List<Share> convert(CreateSharingRequestDto request) {
        List<User> users = request.getEmails().stream()
                .map(email -> new User(email.getEmail()))
                .collect(toList());
        List<Share> shares = request.getSelections().stream()
                .map(selection -> createShare(selection.getSelection(), users))
                .collect(toList());
        return shares;
    }

    private static Share createShare(String selection, List<User> users) {
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
