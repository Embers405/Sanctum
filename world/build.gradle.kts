plugins {
	kotlin("jvm")
	application
}

apply<org.jire.gradle.kilim.KilimPlugin>()

application {
	mainClass.set("io.ruin.Server")

	val jvmArgs = mutableListOf("-Xmx8g", "-Xms8g")
	if (false) jvmArgs.add("-agentpath:C:\\JRebel\\lib\\jrebel64.dll")

	applicationDefaultJvmArgs = jvmArgs
}

repositories {
	mavenCentral()
}




dependencies {
	implementation(project(":core"))
	implementation(project(":update"))

	repositories {
		mavenCentral()
	}
	dependencies {
		implementation("net.dv8tion:JDA:5.0.0-alpha.12")
	}


	dependencies {
		implementation ("org.mindrot:jbcrypt:0.4")
	}


	implementation(kotlin("stdlib"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

	implementation("com.typesafe:config:1.4.1")
	implementation("org.jsoup:jsoup:1.14.2")
	implementation("io.undertow:undertow-core:2.2.10.Final")
	implementation("com.mashape.unirest:unirest-java:1.4.9")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.5")

	lombok()
}

tasks {
	compileKotlin {
		kotlinOptions {
			incremental = true
			jvmTarget = "11"
			suppressWarnings = true
		}
	}
	compileTestKotlin {
		enabled = false
		kotlinOptions {
			incremental = true
			jvmTarget = "11"
			suppressWarnings = true
		}
	}

	jar {
		duplicatesStrategy = DuplicatesStrategy.INCLUDE
	}
	processResources {
		duplicatesStrategy = DuplicatesStrategy.INCLUDE
	}
	run.invoke {
		dependsOn(named<org.jire.gradle.kilim.WeaveTask>("weave"))
	}
}