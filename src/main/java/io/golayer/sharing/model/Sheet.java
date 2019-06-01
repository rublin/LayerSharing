package io.golayer.sharing.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Sheet {
    HR_REPORT("HRReport"),
    ACTUALS("Actuals"),
    ASSUMPTIONS("Assumptions"),
    DASHBOARD("Dashboard");

    private final String name;

    public static Sheet fromRequest(String sheet) {
        return Arrays.stream(Sheet.values())
                .filter(s -> s.name.equalsIgnoreCase(sheet))
                .findFirst().orElseThrow(() -> new RuntimeException("Wrong sheet name"));
    }
}
