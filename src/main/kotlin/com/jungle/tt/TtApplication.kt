package com.jungle.tt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class TtApplication

fun main(args: Array<String>) {
    runApplication<TtApplication>(*args)
}
