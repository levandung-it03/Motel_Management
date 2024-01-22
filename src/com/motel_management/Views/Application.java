package com.motel_management.Views;

import com.motel_management.Views.Graphics.Frame_Login;
import com.motel_management.Views.Graphics.Frame_MainApplication.Frame_MainApplication;

public class Application {
    public Application() { super(); }

    public static void createApplication() {
//        Frame_Login loginFrame = new Frame_Login();
        Frame_MainApplication mainApp = new Frame_MainApplication("Root", "Tp. Ho Chi Minh");
    }
}