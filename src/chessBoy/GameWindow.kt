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

class GameWindow : Scene() {
    override suspend fun SContainer.sceneMain() {
        val minDegrees = (-16).degrees
        val maxDegrees = (+16).degrees

        // Load the image
        val image = image(resourcesVfs["s-l300.jpg"].readBitmap()) {
            rotation = maxDegrees
            anchor(.5, .5)
            scale(1.8)
            position(256, 256)
        }

        // Coroutine-based tweening for smooth animation
        launch {
            while (true) {
                // Tween to the minimum angle
                image.tween(image::rotation[minDegrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
                // Tween to the maximum angle
                image.tween(image::rotation[maxDegrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
            }
        }

        val game = Game(views)

        // Input handling and game logic
        addUpdater { timeSpan: TimeSpan ->
            val dt = timeSpan.seconds

            // inputHandler.process() // Make sure inputHandler is defined and processes input
            game.update(dt)          // Update your game state (replace with actual game logic)

            // You can check for specific inputs here:
            if (views.input.keys.justPressed(Key.SPACE)) {
                // Handle spacebar press, for example
                println("Spacebar was pressed!")
            }
        }
    }
}
