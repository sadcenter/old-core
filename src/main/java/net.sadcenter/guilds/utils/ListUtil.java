package net.sadcenter.guilds.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sadcenter on 26.12.2020
 * @project LastCoree
 */

public final class ListUtil {

    public static Set<String> replace(Collection<String> text, String replaceText, String replace) {
        return text.stream().map(s -> StringUtils.replace(s, replaceText, replace)).collect(Collectors.toSet());
    }

}
