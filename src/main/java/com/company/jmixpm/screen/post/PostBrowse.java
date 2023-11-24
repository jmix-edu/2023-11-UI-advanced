package com.company.jmixpm.screen.post;

import com.company.jmixpm.app.PostService;
import com.company.jmixpm.entity.UserInfo;
import com.company.jmixpm.screen.userinfo.UserInfoBrowse;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

@UiController("Post.browse")
@UiDescriptor("post-browse.xml")
@LookupComponent("postsTable")
@Route("posts")
public class PostBrowse extends Screen {
    @Autowired
    private Table<Post> postsTable;
    @Autowired
    private PostService postService;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private CollectionLoader<Post> postsDl;

    @Install(to = "postsDl", target = Target.DATA_LOADER)
    private List<Post> postsDlLoadDelegate(LoadContext<Post> loadContext) {
        return postService.fetchPosts(
                loadContext.getQuery().getFirstResult(),
                loadContext.getQuery().getMaxResults()
        );

    }

//    @Subscribe("postsTable.viewUserInfo")
//    public void onPostsTableViewUserInfo(final Action.ActionPerformedEvent event) {
//        Post selected = postsTable.getSingleSelected();
//        if (selected == null || selected.getUserId() == null) {
//            return;
//        }
//
//        UserInfoBrowse userInfoBrowse = screenBuilders.screen(this)
//                .withScreenClass(UserInfoBrowse.class)
//                .build();
//        userInfoBrowse.setUserId(selected.getUserId());
//
//        userInfoBrowse.show();
//    }


    @Install(to = "pagination", subject = "totalCountDelegate")
    private Integer paginationTotalCountDelegate() {
        return postService.postsTotalCount();
    }

    @Install(to = "userInfoScreenFacet", subject = "screenConfigurer")
    private void userInfoScreenFacetScreenConfigurer(final UserInfoBrowse userInfoBrowse) {
        Post selected = postsTable.getSingleSelected();
        if (selected == null || selected.getUserId() == null) {
            return;
        }
        userInfoBrowse.setUserId(selected.getUserId());
    }


}