package de.vkoop.redirectchk.gui

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
                .web(false)
                .run();
        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T = applicationContext.getBean(type.java)
            override fun <T : Any> getInstance(type: KClass<T>, name: String): T = applicationContext.getBean(type.java, name)
        }
    }
}
