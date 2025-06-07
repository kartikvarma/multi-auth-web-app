package dev.kartikboreda.multiauthwebapp.config

import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.resolve.DirectoryCodeResolver
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Path

@Configuration
class TemplateConfig {

    @Value("\${gg.jte.templateLocation:src/main/jte}")
    private lateinit var templateLocation: String

    @Bean
    fun codeResolver() = DirectoryCodeResolver(Path.of(templateLocation))

    @Bean
    fun templateEngine(): TemplateEngine = TemplateEngine.create(codeResolver(), ContentType.Html)
}
