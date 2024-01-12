plugins {
	application
	id("com.github.johnrengelman.shadow") apply false
}

dependencies {
	implementation(project(":core"))

	lombok()
}



application {
	mainClass.set("io.ruin.central.Server")
}