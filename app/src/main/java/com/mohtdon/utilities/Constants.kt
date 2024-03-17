package com.mohtdon.utilities

import com.mohtdon.domain.models.reciters.SuraEntity
import com.mohtdon.player.AudioItem

const val RECITERS_BASE_URL = "https://www.mp3quran.net/api/v3/"
const val PRAYERS_BASE_URL="https://api.quran.com/api/v4/"
const val RADIO_BASE_URL = "https://www.mp3quran.net/api/"
//                             radio-v2/radio_ar.json

const val SHARED_PREFERENCES_NAME ="SHARED_PREFERENCES_NAME"
val AudioItemWithInitialValue =
    AudioItem(
        "",
        "",
        "",
        ""
    )




val suraMap = mapOf(
    "1" to SuraEntity("001", "الفاتحة", "مكية"),
    "2" to SuraEntity("002", "البقرة", "مدنية"),
    "3" to SuraEntity("003", "آل عمران", "مدنية"),
    "4" to SuraEntity("004", "النساء", "مدنية"),
    "5" to SuraEntity("005", "المائدة", "مدنية"),
    "6" to SuraEntity("006", "الأنعام", "مكية"),
    "7" to SuraEntity("007", "الأعراف", "مكية"),
    "8" to SuraEntity("008", "الأنفال", "مدنية"),
    "9" to SuraEntity("009", "التوبة", "مدنية"),
    "10" to SuraEntity("010", "يونس", "مكية"),
    "11" to SuraEntity("011", "هود", "مكية"),
    "12" to SuraEntity("012", "يوسف", "مكية"),
    "13" to SuraEntity("013", "الرعد", "مدنية"),
    "14" to SuraEntity("014", "إبراهيم", "مكية"),
    "15" to SuraEntity("015", "الحجر", "مكية"),
    "16" to SuraEntity("016", "النحل", "مكية"),
    "17" to SuraEntity("017", "الإسراء", "مكية"),
    "18" to SuraEntity("018", "الكهف", "مكية"),
    "19" to SuraEntity("019", "مريم", "مكية"),
    "20" to SuraEntity("020", "طه", "مكية"),
    "21" to SuraEntity("021", "الأنبياء", "مكية"),
    "22" to SuraEntity("022", "الحج", "مدنية"),
    "23" to SuraEntity("023", "المؤمنون", "مكية"),
    "24" to SuraEntity("024", "النور", "مدنية"),
    "25" to SuraEntity("025", "الفرقان", "مكية"),
    "26" to SuraEntity("026", "الشعراء", "مكية"),
    "27" to SuraEntity("027", "النمل", "مكية"),
    "28" to SuraEntity("028", "القصص", "مكية"),
    "29" to SuraEntity("029", "العنكبوت", "مكية"),
    "30" to SuraEntity("030", "الروم", "مكية"),
    "31" to SuraEntity("031", "لقمان", "مكية"),
    "32" to SuraEntity("032", "السجدة", "مكية"),
    "33" to SuraEntity("033", "الأحزاب", "مدنية"),
    "34" to SuraEntity("034", "سبأ", "مكية"),
    "35" to SuraEntity("035", "فاطر", "مكية"),
    "36" to SuraEntity("036", "يس", "مكية"),
    "37" to SuraEntity("037", "الصافات", "مكية"),
    "38" to SuraEntity("038", "ص", "مكية"),
    "39" to SuraEntity("039", "الزمر", "مكية"),
    "40" to SuraEntity("040", "غافر", "مكية"),
    "41" to SuraEntity("041", "فصلت", "مكية"),
    "42" to SuraEntity("042", "الشورى", "مكية"),
    "43" to SuraEntity("043", "الزخرف", "مكية"),
    "44" to SuraEntity("044", "الدخان", "مكية"),
    "45" to SuraEntity("045", "الجاثية", "مكية"),
    "46" to SuraEntity("046", "الأحقاف", "مكية"),
    "47" to SuraEntity("047", "محمد", "مدنية"),
    "48" to SuraEntity("048", "الفتح", "مدنية"),
    "49" to SuraEntity("049", "الحجرات", "مدنية"),
    "50" to SuraEntity("050", "ق", "مكية"),
    "51" to SuraEntity("051", "الذاريات", "مكية"),
    "52" to SuraEntity("052", "الطور", "مكية"),
    "53" to SuraEntity("053", "النجم", "مكية"),
    "54" to SuraEntity("054", "القمر", "مكية"),
    "55" to SuraEntity("055", "الرحمن", "مدنية"),
    "56" to SuraEntity("056", "الواقعة", "مكية"),
    "57" to SuraEntity("057", "الحديد", "مدنية"),
    "58" to SuraEntity("058", "المجادلة", "مدنية"),
    "59" to SuraEntity("059", "الحشر", "مدنية"),
    "60" to SuraEntity("060", "الممتحنة", "مدنية"),
    "61" to SuraEntity("061", "الصف", "مدنية"),
    "62" to SuraEntity("062", "الجمعة", "مدنية"),
    "63" to SuraEntity("063", "المنافقون", "مدنية"),
    "64" to SuraEntity("064", "التغابن", "مدنية"),
    "65" to SuraEntity("065", "الطلاق", "مدنية"),
    "66" to SuraEntity("066", "التحريم", "مدنية"),
    "67" to SuraEntity("067", "الملك", "مكية"),
    "68" to SuraEntity("068", "القلم", "مكية"),
    "69" to SuraEntity("069", "الحاقة", "مكية"),
    "70" to SuraEntity("070", "المعارج", "مكية"),
    "71" to SuraEntity("071", "نوح", "مكية"),
    "72" to SuraEntity("072", "الجن", "مكية"),
    "73" to SuraEntity("073", "المزمل", "مكية"),
    "74" to SuraEntity("074", "المدثر", "مكية"),
    "75" to SuraEntity("075", "القيامة", "مكية"),
    "76" to SuraEntity("076", "الإنسان", "مدنية"),
    "77" to SuraEntity("077", "المرسلات", "مكية"),
    "78" to SuraEntity("078", "النبأ", "مكية"),
    "79" to SuraEntity("079", "النازعات", "مكية"),
    "80" to SuraEntity("080", "عبس", "مكية"),
    "81" to SuraEntity("081", "التكوير", "مكية"),
    "82" to SuraEntity("082", "الإنفطار", "مكية"),
    "83" to SuraEntity("083", "المطففين", "مكية"),
    "84" to SuraEntity("084", "الإنشقاق", "مكية"),
    "85" to SuraEntity("085", "البروج", "مكية"),
    "86" to SuraEntity("086", "الطارق", "مكية"),
    "87" to SuraEntity("087", "الأعلى", "مكية"),
    "88" to SuraEntity("088", "الغاشية", "مكية"),
    "89" to SuraEntity("089", "الفجر", "مكية"),
    "90" to SuraEntity("090", "البلد", "مكية"),
    "91" to SuraEntity("091", "الشمس", "مكية"),
    "92" to SuraEntity("092", "الليل", "مكية"),
    "93" to SuraEntity("093", "الضحى", "مكية"),
    "94" to SuraEntity("094", "الشرح", "مكية"),
    "95" to SuraEntity("095", "التين", "مكية"),
    "96" to SuraEntity("096", "العلق", "مكية"),
    "97" to SuraEntity("097", "القدر", "مكية"),
    "98" to SuraEntity("098", "البينة", "مدنية"),
    "99" to SuraEntity("099", "الزلزلة", "مدنية"),
    "100" to SuraEntity("100", "العاديات", "مكية"),
    "101" to SuraEntity("101", "القارعة", "مكية"),
    "102" to SuraEntity("102", "التكاثر", "مكية"),
    "103" to SuraEntity("103", "العصر", "مكية"),
    "104" to SuraEntity("104", "الهمزة", "مكية"),
    "105" to SuraEntity("105", "الفيل", "مكية"),
    "106" to SuraEntity("106", "قريش", "مكية"),
    "107" to SuraEntity("107", "الماعون", "مكية"),
    "108" to SuraEntity("108", "الكوثر", "مكية"),
    "109" to SuraEntity("109", "الكافرون", "مكية"),
    "110" to SuraEntity("110", "النصر", "مدنية"),
    "111" to SuraEntity("111", "المسد", "مكية"),
    "112" to SuraEntity("112", "الإخلاص", "مكية"),
    "113" to SuraEntity("113", "الفلق", "مكية"),
    "114" to SuraEntity("114", "الناس", "مكية")
)




const val baseUrl = "http://mp3quran.net/api/"
const val hadithBaseUrl = "https://www.hadithapi.com/api/"
const val REQUEST_PERMISSION_CODE = 1
const val LONGITUDE = "longitude"
const val LATITUDE = "latitude"

const val FAJR = "fajr"
const val DHUHR = "dhuhr"
const val ASR = "asr"
const val MAGHRIB = "maghrib"
const val ISHA = "isha"

const val IS_FAJR_PERFORMED = "IS_FAJR_PERFORMED"
const val IS_DHUHR_PERFORMED = "IS_DHUHR_PERFORMED"
const val IS_ASR_PERFORMED = "IS_ASR_PERFORMED"
const val IS_MAGHRIB_PERFORMED = "IS_MAGHRIB_PERFORMED"
const val IS_ISHA_PERFORMED = "IS_ISHA_PERFORMED"

const val TOTAL_QURAN_PAGES_NUMBER = 604


//const val PRAYER_TIMES_ARRAY = "salah_name"
const val SALAH_NAME = "salah_name"


 val HadithBookNames = arrayListOf("sahih-bukhari" , "sahih-muslim" , "al-tirmidhi" , "abu-dawood",
    "ibn-e-majah", "sunan-nasai", "mishkat", "musnad-ahmad", "al-silsila-sahiha")




