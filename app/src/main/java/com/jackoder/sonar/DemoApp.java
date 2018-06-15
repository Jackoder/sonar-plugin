package com.jackoder.sonar;

import android.app.Application;

import com.facebook.soloader.SoLoader;
import com.facebook.sonar.android.AndroidSonarClient;
import com.facebook.sonar.android.utils.SonarUtils;
import com.facebook.sonar.core.SonarClient;
import com.facebook.sonar.plugins.inspector.DescriptorMapping;
import com.facebook.sonar.plugins.inspector.InspectorSonarPlugin;
import com.facebook.sonar.plugins.network.NetworkSonarPlugin;

import java.io.IOException;

/**
 * @author Jackoder
 * @version 2018/6/15
 */
public class DemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            SoLoader.init(this, 0);
            if (BuildConfig.DEBUG && SonarUtils.shouldEnableSonar(this)) {
                final SonarClient client = AndroidSonarClient.getInstance(this);
                final DescriptorMapping descriptorMapping = DescriptorMapping.withDefaults();
                client.addPlugin(new InspectorSonarPlugin(this, descriptorMapping));
                client.addPlugin(new NetworkSonarPlugin());
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}