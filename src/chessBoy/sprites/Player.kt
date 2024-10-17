package chessBoy.sprites

import korlibs.event.*
import korlibs.image.format.*
import korlibs.io.file.std.*
import korlibs.korge.view.*
import korlibs.math.geom.*

class Player(private val views: Views, private val container: SContainer) {
    suspend fun initialize() {
        spriteImage = container.image(resourcesVfs["rainbow_circle.png"].readBitmap()) { // Load the sprite image
            anchor(0, 0)
            position(position.x, position.y)
            scale(0.5, 0.5)
        }
    }

    fun update(deltaTime: Double) {
        var move = Vector2(0, 0)

        // Check for input and modify the move vector
        if (views.input.keys.pressing(Key.D)) {
            move += Vector2(1, 0) // Move right
        }
        if (views.input.keys.pressing(Key.A)) {
            move += Vector2(-1, 0) // Move left
        }
        if (views.input.keys.pressing(Key.W)) {
            move += Vector2(0, -1) // Move up
        }
        if (views.input.keys.pressing(Key.S)) {
            move += Vector2(0, 1) // Move down
        }

        // Apply acceleration
        if (move.length > 0) {
            // Normalize to get the direction and scale by acceleration
            move = move.normalized * acceleration * deltaTime
            velocity += move // Increase velocity based on input
        } else {
            // Apply deceleration when no keys are pressed
            if (velocity.length > 0) {
                // Decelerate towards zero
                val decelerationVector = velocity.normalized * deceleration * deltaTime
                if (velocity.length <= decelerationVector.length) {
                    velocity = Vector2(0,0) // Stop if deceleration exceeds velocity
                } else {
                    velocity -= decelerationVector // Reduce velocity
                }
            }
        }

        // Clamp the velocity to the max velocity
        if (velocity.length > speed.length) {
            velocity = velocity.normalized * speed.length
        }

        // verify new position is on-screen
        // TODO move to collision handler
        var newPosition = position + velocity * deltaTime
        if (newPosition.x < 0) {
            newPosition = Vector2(0, newPosition.y)
            velocity = Vector2(0, velocity.y)
        }
        if (newPosition.y < 0) {
            newPosition = Vector2(newPosition.x, 0)
            velocity = Vector2(velocity.x, 0)
        }
        if (newPosition.x > config.GameConstants.SCREEN_WIDTH - spriteImage.scaledWidth) {
            newPosition = Vector2((config.GameConstants.SCREEN_WIDTH - spriteImage.scaledWidth).toInt(), newPosition.y)
            velocity = Vector2(0, velocity.y)
        }
        if (newPosition.y > config.GameConstants.SCREEN_HEIGHT - spriteImage.scaledHeight) {
            newPosition = Vector2(newPosition.x, (config.GameConstants.SCREEN_HEIGHT - spriteImage.scaledHeight).toInt())
            velocity = Vector2(velocity.x, 0)
        }


        // Update the sprite's position
        position = newPosition
        spriteImage.position(position.x, position.y)
    }

    // Private vars
    private lateinit var spriteImage: Image
    private val speed = Vector2(SPEED_X, SPEED_Y)
    private var position = Vector2(0, 0)
    private val acceleration = ACCELERATION
    private val deceleration = DECELERATION
    private var velocity = Vector2(0, 0)

    // Constants (move to settings file)
    companion object {
        const val SPEED_X = 2000
        const val SPEED_Y = 2000
        const val ACCELERATION = 2000
        const val DECELERATION = 800
        const val JUMP_STRENGTH = 300.0
    }
}
