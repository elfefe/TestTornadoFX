package com.elfefe.tornadofx

import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import tornadofx.*
import javafx.scene.layout.BorderPane
import javafx.scene.text.TextAlignment
import java.awt.Color
import java.awt.Label
import javax.swing.GroupLayout

class LeftSideBar : View() {
    override val root = vbox {
        add(TopView::class.java)
        add(BottomView::class.java)

        style {
            backgroundColor += c("#eeeeee", 1.0)
        }
    }
}

class TopView : View() {
    override val root = vbox {
        alignment = Pos.CENTER
        label {
            text = "User"
            alignment = Pos.CENTER
        }

        style {
            backgroundColor += c("#f3a1c4", 1.0)
        }
    }
}

class BottomView : View() {
    override val root = vbox(100.0) {
        alignment = Pos.CENTER
        label {
            text = "Descrition"
            alignment = Pos.CENTER
        }
        style {
            backgroundColor += c("#b3e1d4", 1.0)
        }
    }
}