class Menu {
    private val formatter = "%d. %s"
    var menuItems = mutableListOf<MenuItem>()
    fun workMenu(list: List<File>, type: String) {
        menuItems.clear()
        menuItems.add(MenuItem("Создать $type"))
        menuItems.addAll(list.map { MenuItem(it.name) })
        menuItems.add(MenuItem("Выход"))

        menuItems.forEachIndexed { index, menuItem ->
            println(String.format(formatter, index, menuItem.title))
        }
    }
}