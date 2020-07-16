package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val parts : List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)?.ifEmpty { null }
        val lastName = parts?.getOrNull(1)?.ifEmpty { null }
        return firstName to lastName
    }

   fun transliteration(payload: String, divider:String=" "): String {
       val transliteratedLetter = hashMapOf(
           //LowerCase
           "а" to "a",
           "б" to "b",
           "в" to "v",
           "г" to "g",
           "д" to "d",
           "е" to "e",
           "ё" to "e",
           "ж" to "zh",
           "з" to "z",
           "и" to "i",
           "й" to "i",
           "к" to "k",
           "л" to "l",
           "м" to "m",
           "н" to "n",
           "о" to "o",
           "п" to "p",
           "р" to "r",
           "с" to "s",
           "т" to "t",
           "у" to "u",
           "ф" to "f",
           "х" to "h",
           "ц" to "c",
           "ч" to "ch",
           "ш" to "sh",
           "щ" to "sh",
           "ъ" to "",
           "ы" to "i",
           "ь" to "",
           "э" to "e",
           "ю" to "yu",
           "я" to "ya",
           //UpperCase
           "А" to "A",
           "Б" to "B",
           "В" to "V",
           "Г" to "G",
           "Д" to "D",
           "Е" to "E",
           "Ё" to "E",
           "Ж" to "Zh",
           "З" to "Z",
           "И" to "I",
           "Л" to "I",
           "К" to "K",
           "Л" to "L",
           "М" to "M",
           "Н" to "N",
           "О" to "O",
           "П" to "P",
           "Р" to "R",
           "С" to "S",
           "Т" to "T",
           "У" to "U",
           "Ф" to "F",
           "Х" to "H",
           "Ц" to "C",
           "Ч" to "Ch",
           "Ш" to "Sh",
           "Щ" to "Sh",
           "Ъ" to "",
           "Ы" to "I",
           "Ь" to "",
           "Э" to "E",
           "Ю" to "Yu",
           "Я" to "Ya"
       )
       var fullNickname = ""
       payload.forEach {
           fullNickname += when {
               it.isWhitespace() -> divider
               transliteratedLetter.containsKey(it.toString()) -> transliteratedLetter[it.toString()]
               else -> it
           }
       }
       return fullNickname
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val initialFirstName = firstName?.trim()?.first()?.toUpperCase() ?: ""
        val initialLastName = lastName?.trim()?.first()?.toUpperCase() ?: ""
        return initialFirstName.toString()+initialLastName.toString()
    }
}
//updated