
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.image.color.*
import korlibs.math.geom.*

import chessBoy.*

suspend fun main() = Korge(windowSize = Size(512, 512), backgroundColor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer()

	sceneContainer.changeTo { GameWindow() }
}


