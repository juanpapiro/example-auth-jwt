package br.com.fiap.productmanager.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public enum RoleType {

    ROLE_ADMIN,
    ROLE_MERCHANT,
    ROLE_CLIENT;

    public static Map<String, List<String>> map() {
        Map<String, List<String>> rolesMap = new HashMap<>();
        IntStream.range(0, RoleType.values().length-1).forEach(i -> {
            rolesMap.put(RoleType.values()[i].toString(), List.of(RoleType.values()[i+1].toString()));
        });
        return rolesMap;
    }

}
