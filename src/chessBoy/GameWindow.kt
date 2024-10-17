package chessBoy

import korlibs.event.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.korge.scene.*
import korlibs.korge.tween.*
import korlibs.korge.view.*
import korlibs.math.geom.*
import korlibs.math.interpolation.*
import korlibs.time.*
import kotlinx.coroutines.*
import kotlin.time.*

class GameWindow : Scene() {
    override suspend fun SContainer.sceneMain() {
        val game = Game(views,this)
        game.sprites.initialize() // Has to be called from here since suspended

        //val inputHandler = InputHandler(views)
        // Main game loop
        // 1 Process Input
        // 2 Update game state
        // 3 Render

        addUpdater { timeSpan: Duration ->
            val dt = timeSpan.seconds

            //inputHandler.processInput()

            game.update(dt)

            if (views.input.keys.justPressed(Key.SPACE)) {
                println("Spacebar was pressed!")
            }
        }
    }
}
