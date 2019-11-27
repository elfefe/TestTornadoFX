package com.elfefe.tornadofx

import com.elfefe.tornadofx.view.MyView
import javafx.scene.Scene
import javafx.stage.Stage
import tornadofx.App
import tornadofx.UIComponent

class MyApp: App(MyView::class){
    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 800.0, 600.0)
    override fun start(stage: Stage) {
        with(stage) {
            minWidth = 800.0
            minHeight = 600.0
            super.start(this)
        }
    }
}