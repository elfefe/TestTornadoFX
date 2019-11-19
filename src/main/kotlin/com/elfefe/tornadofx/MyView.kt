package com.elfefe.tornadofx

import javafx.scene.Parent
import tornadofx.*

class MyView : View() {
    private val lsb: LeftSideBar by inject()
    private val rcp: RightContentPanel by inject()

    override val root = borderpane {
        left = lsb.root
        center = rcp.root

        rcp.tabBar.lsbState.addListener { _, _, state ->
            lsb.state.set(state)
        }

        minWidth = 800.0
        minHeight = 600.0
        style {
            backgroundColor += c("#ffffff", 1.0)
        }
    }
}