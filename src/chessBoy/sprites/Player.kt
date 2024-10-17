package chessBoy.sprites

import korlibs.event.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.korge.view.*
import korlibs.math.geom.*

class Player(private val views: Views, private val container: SContainer) {

    suspend fun initialize() {
        spriteImage = container.image(resourcesVfs["korge.png"].readBitmap()) { // Load the sprite image
            anchor(0.5, 0.5) // Center the sprite's anchor point
            position(position.x, position.y) // Starting position
        }
    }

    fun update(deltaTime: Double) {
        var move = Vector2(0, 0)
        if (views.input.keys.pressing(Key.RIGHT)) {
            move += Vector2(speed.x, 0)
        }
        if (views.input.keys.pressing(Key.LEFT)) {
            move += Vector2(-speed.x, 0)
        }
        if (views.input.keys.pressing(Key.UP)) {
            move += Vector2(0, -speed.y)
        }
        if (views.input.keys.pressing(Key.DOWN)) {
            move += Vector2(0, speed.y)
        }

        isMoving = (move.x > 0 || move.x < 0 || move.y > 0 || move.y < 0)

        if (isMoving) {
            position += move * deltaTime
            spriteImage.position(position.x, position.y)
            println("X: ${position.y}, Y: ${position.y}")
        }
    }

    // Private vars
    private lateinit var spriteImage: Image
    private val speed = Vector2(SPEED_X, SPEED_Y)
    private var position = Vector2(0, 0)
    private var isMoving = false

    // Constants
    companion object {
        const val SPEED_X = 200
        const val SPEED_Y = 200
        const val MAX_VELOCITY = 200.0
        const val MAX_VELOCITY_Y = 200.0
        const val MAX_VELOCITY_X = 200.0
        const val JUMP_STRENGTH = 300.0
    }
}
