package com.elfefe.tornadofx

import javafx.beans.Observable
import javafx.collections.FXCollections
import javafx.collections.ObservableArray
import javafx.collections.ObservableList
import javafx.event.EventType
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.*
import javafx.scene.effect.DropShadow
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.layout.HBox
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import org.intellij.lang.annotations.JdkConstants
import javafx.scene.web.WebEngine
import javafx.util.Callback
import tornadofx.*
import javax.swing.border.Border

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
            webview {
                val engine: WebEngine = engine
                engine.load("https://www.google.com/maps/@?api=1&map_action=map")

                val shadow = DropShadow()
                shadow.radius = 20.0
                shadow.color = Color.BLACK

                effect = shadow
                paddingAll = 20.0


                style {
                    borderColor += box(c("#000000"))
                    borderStyle += BorderStrokeStyle.SOLID
                    borderWidth += box(Dimension(10.0, Dimension.LinearUnits.px))
                }
            }
        }
    }
}

class TabCom : View() {
    private  val message: ObservableList<Message> = FXCollections.observableArrayList(Message("Coucou"))
    override val root = borderpane {
        center {
            add(MessageHolder(message))
        }

        bottom {
            hbox {
                val inputMessage = TextField()
                val sendMessage = Button()
                sendMessage.setOnAction {
                    message.add(Message(inputMessage.text))
                }

                add(inputMessage)
                add(sendMessage)
            }
        }
    }
}

class MessageHolder(messages: List<Message>): View(){
    override val root = vbox()
    init {
        with(root) {
            useMaxWidth = false
            for (message in messages) {
                add(Message(message.messageBox.text))
                style {
                    backgroundColor += c(250, 250, 250, 1.0)
                }
            }
        }
    }
}

class Message(private val value: String): View() {
    val messageBox = Label()
    override val root = borderpane {
        center {
            messageBox.text = value
            add(messageBox)
        }
        setMaxSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE)
        padding = Insets(10.0,10.0,10.0,10.0)
        style {
            borderColor += box(c("#333333"))
            borderStyle += BorderStrokeStyle.SOLID
            borderWidth += box(Dimension(1.0, Dimension.LinearUnits.px))
        }
    }
}