package com.company.jmixpm.screen.themesettings;

import io.jmix.ui.app.themesettings.ThemeSettingsScreen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("themeSettingsScreen")
@UiDescriptor("ext-theme-settings-screen.xml")
public class ExtThemeSettingsScreen extends ThemeSettingsScreen {

    @Subscribe
    public void onInit(final InitEvent event) {
        messageBundle.setMessageGroup("io.jmix.ui.app.themesettings");
        super.onInit(event);
    }


}