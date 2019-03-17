package de.vkoop.redirectchk.gui

import javafx.application.Application
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.ComponentScan
import tornadofx.*
import kotlin.reflect.KClass

@ComponentScan(basePackages = ["de.vkoop.redirectchk"])
@SpringBootApplication()
class MyApp: App(MainView::class, AppStyle::class){
    override fun init() {
        super.init()
        val applicationContext = SpringApplicationBuilder(MyApp::class.java)
                .web(WebApplicationType.NONE)
                .run()
        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T = applicationContext.getBean(type.java)
            override fun <T : Any> getInstance(type: KClass<T>, name: String): T = applicationContext.getBean(type.java, name)
        }
    }
}

fun main() {
    Application.launch(MyApp::class.java)
}
