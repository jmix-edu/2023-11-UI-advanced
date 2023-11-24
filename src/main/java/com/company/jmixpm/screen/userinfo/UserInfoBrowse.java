package com.company.jmixpm.screen.userinfo;

import com.company.jmixpm.app.PostService;
import com.google.common.collect.ImmutableMap;
import io.jmix.core.LoadContext;
import io.jmix.ui.component.Button;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.navigation.UrlIdSerializer;
import io.jmix.ui.navigation.UrlParamsChangedEvent;
import io.jmix.ui.navigation.UrlRouting;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@UiController("UserInfo.browse")
@UiDescriptor("user-info-browse.xml")
@Route("user-info")
public class UserInfoBrowse extends Screen {

    @Autowired
    private UrlRouting urlRouting;
    @Autowired
    private PostService postService;
    private Long userId;

    public void setUserId(Long id){
        this.userId = id;
    }
    @Install(to = "userInfoDl", target = Target.DATA_LOADER)
    private UserInfo userInfoDlLoadDelegate(final LoadContext<UserInfo> loadContext) {
        return postService.fetchUserInfo(userId);
    }

    @Subscribe("closeBtn")
    public void onCloseBtnClick(final Button.ClickEvent event) {
        closeWithDefaultAction();
    }

    @Subscribe
    public void onAfterShow(final AfterShowEvent event) {
        String serializedId = UrlIdSerializer.serializeId(userId);
        urlRouting.replaceState(this, ImmutableMap.of("id", serializedId));
    }

    @Subscribe
    public void onUrlParamsChanged(final UrlParamsChangedEvent event) {
        String serializedId = event.getParams().get("id");
        userId = (Long) UrlIdSerializer.deserializeId(Long.class, serializedId);
    }



}