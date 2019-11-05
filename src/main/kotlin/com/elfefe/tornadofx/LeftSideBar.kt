package com.elfefe.tornadofx

import javafx.geometry.Pos
import javafx.geometry.VPos
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
    override val root = gridpane {
        add(TopView::class.java)
        add(BottomView::class.java)

        constraintsForRow(1).percentHeight = 40.0
        constraintsForRow(2).percentHeight = 60.0

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

        gridpaneConstraints {
            fillHeight = true
            columnIndex = 1
            rowIndex = 1
            vAlignment = VPos.CENTER
        }

        style {
            backgroundColor += c("#f3a1c4", 1.0)
        }
    }
}

class BottomView : View() {
    override val root = vbox(100.0) {
        label {
            text = "Descrition"
            alignment = Pos.CENTER
        }

        gridpaneConstraints {
            fillHeight = true
            columnIndex = 1
            rowIndex = 2
            vAlignment = VPos.CENTER
        }

        style {
            backgroundColor += c("#b3e1d4", 1.0)
        }
    }
}