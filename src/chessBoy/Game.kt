package chessBoy

import korlibs.event.*
import korlibs.math.geom.*
import korlibs.korge.view.*

class Game(v: Views) {
    // Public
    var playerPosition = Vector2(100, 100)
    var score = 0

    // Update the game state, logic, physics, etc.
    fun update(deltaTime: Double) {
        // Handle input and game logic, e.g., move the player
        if (viewsReference.input.keys.justPressed(Key.RIGHT)) {
            playerPosition += Vector2(1, 0) * deltaTime // Move right
        }

        // Other game state updates, such as collision detection, AI updates, etc.
    }

    // Optional: You could add more game-specific methods, such as:
    fun reset() {
        // Reset the game state if needed
        playerPosition = Vector2(100, 100)
        score = 0
    }

    // Private
    private var viewsReference:Views = v
}
