package chessBoy

import chessBoy.sprites.*

import korlibs.event.*
import korlibs.math.geom.*
import korlibs.korge.view.*

class Game(v: Views, container: SContainer) {
    // Public
    var score = 0
    var sprites: Sprites

    fun update(deltaTime: Double) {
        sprites.update(deltaTime)

        // Other game state updates, such as collision detection, AI updates, etc.
    }

    // Optional: You could add more game-specific methods, such as:
    fun reset() {
        score = 0
    }

    // Private
    private var viewsReference:Views = v

    // Cons
    init {
        sprites = Sprites(v, container)
    }
}
