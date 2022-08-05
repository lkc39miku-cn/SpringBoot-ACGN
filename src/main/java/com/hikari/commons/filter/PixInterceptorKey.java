package com.hikari.commons.filter;

import java.util.List;
import java.util.Set;

/**
 * PixFilterKey
 *
 * @author lkc39miku_cn
 */
public class PixInterceptorKey {
    public static final Set<String> IS_OFF = Set.copyOf(List.of("/spring_cloud.pix_login"
    , "/spring_cloud.pix_mapping"
    , "/spring_cloud.pix_picture_collection"
    , "/spring_cloud.pix_picture"
    , "/spring_cloud.pix_picture_detailed"
    , "/spring_cloud.pix_tag"
    , "/spring_cloud.pix_user_collect"
    , "/spring_cloud.pix_user"
    , "/spring_cloud.pix_user_detailed"
    , "/spring_cloud.pix_user_detailed_show"
    , "/spring_cloud.pix_user_featured"
    , "/spring_cloud.pix_user_order"
    , "/spring_cloud.pix_user_praise"
    , "/spring_cloud.pix_user_primary"));
    public static final Set<String> IS_OK = Set.copyOf(List.of("/spring_cloud.pix_login/login"));
}
