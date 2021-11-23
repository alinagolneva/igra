package com.metanit;

import javafx.application.Platform;

public class MouseException extends Exception {
    public MouseException()
    {
        Platform.exit();
    }
}
