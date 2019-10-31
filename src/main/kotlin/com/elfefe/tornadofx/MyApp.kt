package com.elfefe.tornadofx

import javafx.scene.Scene
import javafx.stage.Stage
import tornadofx.App
import tornadofx.UIComponent
import java.awt.Window

class MyApp: App(MyView::class){
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 800.0, 600.0)
}