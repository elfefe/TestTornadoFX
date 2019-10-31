package com.elfefe.tornadofx

import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.Tab
import org.intellij.lang.annotations.JdkConstants
import tornadofx.*

class RightContentPanel : View() {
    private val tabMaps: TabMaps by inject()
    private val tabCom: TabCom by inject()

    override val root = tabpane {
        tab("maps") {
            content = tabMaps.root
        }
        tab("com") {
            content = tabCom.root
        }

        style {
            backgroundColor += c("#bbbbbb", 1.0)
        }
    }
}

class TabMaps : View() {
    override val root = borderpane() {
        center {
            button("Button 1")
        }
    }
}

class TabCom : View() {
    override val root = borderpane {
        center {
            label {
                text = "Communication"
                alignment = Pos.CENTER
            }
        }
    }
}