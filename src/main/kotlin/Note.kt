class Note (override val name: String, val noteText: String): File {
    override fun toString(): String {
        return "$name\n\n$noteText"
    }
}