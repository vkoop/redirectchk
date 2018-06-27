package de.vkoop.redirectchk

import de.vkoop.redirectchk.services.RedirectCheck
import de.vkoop.redirectchk.services.input.ExcelReader
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.stereotype.Component
import java.io.FileInputStream
import javax.inject.Inject

@SpringBootApplication
class ConsoleApp

fun main(args: Array<String>) {
    SpringApplicationBuilder(ConsoleApp::class.java)
            .web(WebApplicationType.NONE)
            .bannerMode(Banner.Mode.OFF)
            .build()
            .run(*args)
}

@Component
class CmdRunner : CommandLineRunner {

    @Inject
    lateinit var reader : ExcelReader

    @Inject
    lateinit var checker : RedirectCheck

    override fun run(vararg args: String?) {
        if(args.isEmpty()) {
            println("Missing argument")
            return;
        }

        val fileName = args.get(0)!!
        val fileInputStream = FileInputStream(fileName)

        val checks = reader.read(fileInputStream)

        checks.map ( checker::check )
                .forEach { println(it)}
    }
}