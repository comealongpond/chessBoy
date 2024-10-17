package chessBoy.sprites

import korlibs.korge.view.*

// Keep track of all sprites and handle updating/rendering
class Sprites(private val views: Views, private val container: SContainer) {
    private val player: Player = Player(views, container) // Instantiate player sprite

    suspend fun initialize() {
        player.initialize() // Initialize the player sprite
    }

    fun update(deltaTime: Double) {
        player.update(deltaTime) // Update the player sprite
    }

}
