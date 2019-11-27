package com.elfefe.tornadofx.view

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.effect.DropShadow
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import tornadofx.*

class RightContentPanel : View() {
    val tabMaps: TabMaps by inject()
    val tabCom: TabCom by inject()
    val tabBar: TabBar by inject()

    private var tab = SimpleIntegerProperty(0)

    override val root = vbox {
        tabBar.tab.addListener(ChangeListener { _: ObservableValue<out Number>?, _: Number, newValue: Number ->
            if (newValue == 0)
                children[1].replaceWith(tabMaps.root)
            else
                children[1].replaceWith(tabCom.root)
        })

        add(tabBar)
        add(tabMaps)

        minWidthProperty().bind(primaryStage.widthProperty().multiply(0.7))
    }
}

class TabBar : View() {
    private val leftIcon = svgicon("M15.41 16.59L10.83 12l4.58-4.59L14 6l-6 6 6 6 1.41-1.41z")

    val tab = SimpleIntegerProperty(0)
    val lsbState = SimpleBooleanProperty(true)

    override val root = hbox(10) {
        val parent = this
        button {
            graphic = leftIcon
            onMouseClicked = EventHandler {
                lsbState.set(!lsbState.get())
            }
            minWidthProperty().bind(parent.widthProperty() * 0.2)
        }
        label {
            text = TAB_MAPS
            textAlignment = TextAlignment.CENTER
            onMouseClicked = EventHandler {
                tab.set(0)
            }
            minWidthProperty().bind(parent.widthProperty() * 0.2)
        }
        label {
            text = TAB_COM
            textAlignment = TextAlignment.CENTER
            onMouseClicked = EventHandler {
                tab.set(1)
            }
            minWidthProperty().bind(parent.widthProperty() * 0.2)
        }
    }


    companion object {
        private const val TAB_MAPS = "TabMaps"
        private const val TAB_COM = "TabCom"
    }
}

class TabMaps : View() {
    private val margin = 20.0
    override val root = borderpane() {
        center {
            webview {
                engine.load("https://www.google.com/maps/@?api=1&map_action=map")
                engine.isJavaScriptEnabled = true
                effect = DropShadow(margin, Color.BLACK)
                paddingAll = margin
            }
        }
    }
}

class TabCom : View() {
    private val message: ObservableList<Message> = FXCollections.observableArrayList(
        Message(
            "Coucou",
            "user"
        )
    )
    override val root = borderpane {
        center {
            val tabCenter = this
            scrollpane {
                val viewMessageHolder = MessageHolder(message)
                viewMessageHolder.root.prefWidthProperty().bind(tabCenter.widthProperty())
                isFitToWidth = true
                add(viewMessageHolder)
            }
        }

        bottom {
            hbox {
                val inputMessage = TextField()
                val sendMessage = Button()
                sendMessage.setOnAction {
                    message.add(Message(inputMessage.text, "user"))
                }

                add(inputMessage)
                add(sendMessage)
            }
        }
    }
}

class MessageHolder(messages: ObservableList<Message>) : View() {
    override val root = vbox()

    init {
        with(root) {
            for (message in messages) {
                val messageView = Message(message.messageBox.text, "user")
                messageView.root.prefWidthProperty().bind(this.widthProperty())
                add(Message(message.messageBox.text, "user"))
            }
            messages.addListener(ListChangeListener {
                root.clear()
                if (it.list.size > 50)
                    it.list.remove(0, it.list.size - 50)
                for (message in it.list) {
                    add(Message(message.messageBox.text, "user"))
                }
            })
            style {
                backgroundColor += c(250, 250, 250, 1.0)
            }
        }
    }
}

class Message(private val value: String, private val user: String) : View() {
    val messageBox = Label()
    override val root =
        hbox {
            useMaxWidth = true
            alignment = Pos.CENTER_RIGHT
            minWidth = HBox.USE_COMPUTED_SIZE
            padding = Insets(10.0, 10.0, 10.0, 10.0)
            borderpane {
                center {
                    messageBox.text = value
                    messageBox.textFill = c(250, 250, 250, 1.0)
                    add(messageBox)
                }
                val shadow = DropShadow()
                shadow.color = c(0, 0, 0, 0.5)
                shadow.radius = 5.0
                effect = shadow
                padding = Insets(10.0, 10.0, 10.0, 10.0)
                style {
                    fill = c(150, 150, 150, 1.0)
                    backgroundColor += c(100, 100, 100, 0.6)
                    backgroundRadius += box(Dimension(8.0, Dimension.LinearUnits.px))
                    borderColor += box(c("#333333"))
                    borderStyle += BorderStrokeStyle.SOLID
                    borderWidth += box(Dimension(1.0, Dimension.LinearUnits.px))
                    borderRadius += box(Dimension(8.0, Dimension.LinearUnits.px))
                    alignment = Pos.CENTER_RIGHT
                }
            }
        }
}