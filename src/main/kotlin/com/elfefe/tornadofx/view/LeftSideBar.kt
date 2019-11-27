package com.elfefe.tornadofx.view

import javafx.beans.property.SimpleBooleanProperty
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.image.Image
import javafx.scene.layout.Background
import tornadofx.*
import java.io.FileInputStream

class LeftSideBar : View() {
    val state = SimpleBooleanProperty()
    override val root = vbox {
        val topView = TopView()
        val bottomView = BottomView()

        heightProperty().addListener { _, _, newValue ->
            topView.root.prefHeight = newValue.toDouble() * 0.3
            bottomView.root.prefHeight = newValue.toDouble() * 0.7
        }

        add(topView)
        add(bottomView)

        minWidthProperty().bind(primaryStage.widthProperty().multiply(0.2))
        state.addListener { _, _, state ->

        }

        style {
        }
    }
}

class TopView : View() {
    override val root = vbox {
        alignment = Pos.CENTER
        isFillWidth = true
        minWidthProperty().bind(primaryStage.widthProperty().multiply(0.2))
        imageview {
            val input = FileInputStream("src/main/resources/drawable/logo.jpg")
            image = Image(input, 100.0, 100.0, false, true)
        }

        textfield {
            text = "User"
            alignment = Pos.CENTER
            background = Background.EMPTY
        }

        style {
            backgroundColor += c("#f3a1c4", 1.0)
        }
    }
}

class BottomView : View() {
    override val root = vbox {
        isFillWidth = true
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