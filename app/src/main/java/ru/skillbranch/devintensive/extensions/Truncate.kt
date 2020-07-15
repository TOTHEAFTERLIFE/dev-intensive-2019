package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16) : String {
    val string = this.trim()
    return if (string.length <= length) string
    else string.substring(0, length).trim() + "..."
}
fun String.stripHtml(): String {
    return this.replace(Regex("\\<.*?>"),"").replace(Regex(" {2,}"), " ")
}
/*
Реализуй extension позволяющий очистить строку от html тегов и html escape последовательностей ("& < > ' ""), а так же удалить пустые символы (пробелы) между словами если их больше 1. Необходимо вернуть модифицированную строку
Пример:
"<p class="title">Образовательное IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch
"<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch

 */