
import korlibs.korge.*
import korlibs.korge.scene.*
import korlibs.image.color.*
import korlibs.math.geom.*

import chessBoy.*

suspend fun main() = Korge(windowSize = Size(config.GameConstants.SCREEN_WIDTH, config.GameConstants.SCREEN_HEIGHT), backgroundColor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer()

	sceneContainer.changeTo { GameWindow() }
}


