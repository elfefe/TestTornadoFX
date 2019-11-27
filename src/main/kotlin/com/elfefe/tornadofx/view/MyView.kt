package com.elfefe.tornadofx.view

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

        style {
            backgroundColor += c("#ffffff", 1.0)
        }
    }
}