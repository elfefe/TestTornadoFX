package com.elfefe.tornadofx

import javafx.scene.Parent
import tornadofx.*

class MyView: View() {
    override val root = borderpane {
        left<LeftSideBar>()
        center<RightContentPanel>()
        style {
            backgroundColor += c("#ffffff", 1.0)
        }
    }
}