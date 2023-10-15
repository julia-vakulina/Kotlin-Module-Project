import java.util.Scanner
val scanner = Scanner(System.`in`)

fun main(args: Array<String>) {
    val archiveList = mutableListOf<Archive>()
    val menu = Menu()
    var menuStatus = Status.ARCHIVE
    var selArchiveIndex = -1
    var selNoteIndex = -1
    println("Выберите пункт меню")
    while (true) {
        when (menuStatus) {
            Status.ARCHIVE -> menu.workMenu(archiveList, "архив")
            Status.NOTE -> menu.workMenu(archiveList[selArchiveIndex].notesList, "заметку")
            Status.VIEW -> {
                if (selArchiveIndex >= 0 && selNoteIndex >= 0) {
                    println(archiveList[selArchiveIndex].notesList[selNoteIndex])
                }
                scanner.nextLine()
                menuStatus = Status.NOTE
                continue
            }
            Status.CREATE -> {
                when {
                    selArchiveIndex < 0 -> {
                        println("Введите название архива")
                        var name: String = scanner.nextLine()
                        name = checkNull(name)
                        archiveList.add(Archive(name, mutableListOf()))
                        menuStatus = Status.ARCHIVE
                        continue
                    }
                    selArchiveIndex >= 0 -> {
                        println("Введите название заметки")
                        var name: String = scanner.nextLine()
                        name = checkNull(name)
                        archiveList.add(Archive(name, mutableListOf()))
                        println("Введите текст заметки")
                        var text = scanner.nextLine()
                        text = checkNull(text)
                        archiveList[selArchiveIndex].notesList.add(Note(name, text))
                        menuStatus = Status.NOTE
                        continue
                    }
                }
            }
        }


        if (scanner.hasNextInt()) {
            val selIndex = scanner.nextLine().toInt()
            when {
                selIndex == 0 -> menuStatus = Status.CREATE
                selIndex > 0 && selIndex < menu.menuItems.lastIndex -> {
                    when (menuStatus) {
                        Status.ARCHIVE -> {
                            selArchiveIndex = selIndex - 1
                            menuStatus = Status.NOTE
                        }
                        Status.NOTE -> {
                            selNoteIndex = selIndex - 1
                            menuStatus = Status.VIEW
                        }
                        else -> {}
                    }
                }
                selIndex == menu.menuItems.lastIndex -> {
                    when (menuStatus) {
                        Status.NOTE -> menuStatus = Status.ARCHIVE
                        else -> return
                    }
                }
                else -> {
                    println("Ошибочка! Кажется, это неверное значение - его нет среди нужных. Выберите пункт меню!")
                }
            }
        }
        else {
            println("Ошибочка! Попробуем еще раз? Введите цифру!")
            val err = scanner.nextLine()
        }
    }

}
fun checkNull(name:String): String {
    var newName: String = name
    while (newName == "") {
        println("Ошибочка! Введите повторно!")
        newName = scanner.nextLine()
    }
    return newName
}